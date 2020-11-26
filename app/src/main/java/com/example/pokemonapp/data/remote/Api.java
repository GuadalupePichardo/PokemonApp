package com.example.pokemonapp.data.remote;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.pokemonapp.data.model.Cards;
import com.example.pokemonapp.data.model.Pokemon;
import com.example.pokemonapp.listener.TaskListener;
import com.facebook.stetho.okhttp3.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static final String TAG = "Api";

    private static Api INSTANCE;
    private Retrofit retrofit;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final Executor THREAD_POOL_EXECUTOR = Executors.newFixedThreadPool(CORE_POOL_SIZE);
    private WebService webService;
    private Context mContext;
    private String token;

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


    public static Api getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Api();
        }
        return INSTANCE;
    }

    private Api() {
        //http client
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addNetworkInterceptor(logging);
            client.addNetworkInterceptor(new StethoInterceptor());
        }
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.pokemontcg.io/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .callbackExecutor(THREAD_POOL_EXECUTOR)
                .build();

        webService = retrofit.create(WebService.class);
    }

    //Te permite guardar la sesion actual del usuario
    public void saveSession() {
        SharedPreferences settings = mContext.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", token);
        editor.apply();
    }

    //Carga informacion de la sesion
    public void loadSession() {
        SharedPreferences prefs = mContext.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        this.token = prefs.getString("token", null);
    }

    //api error handling
    private String getApiError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            Response response = exception.response();
            try {
                return response.errorBody().string();
            }
            catch (Exception e) {
                return "Ocurrio un error";
            }
        }
        else {
            return "Ocurrio un error";
        }
    }

    private Single<Cards> pokemonDetails() {
        return webService.getPokemonDetails()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getCardsDetails(final TaskListener<Cards> listener) {
        Single<Cards> observable = pokemonDetails(); //single es un observer
        //con un observer te subscriber
        observable.subscribe(new SingleObserver<Cards>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onSuccess(@NonNull Cards cards) {
                listener.onSuccess(cards);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                String msgError = getApiError(e);
                listener.onError(msgError); //le pasamos el error al activity
            }
        });
    }

}
