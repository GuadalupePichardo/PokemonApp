package com.example.pokemonapp.view.details;

import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Image {
    private String url;
    private ImageView imageView;

    public Image(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
    }

    private Single getImage() {
        return Single.create(new SingleOnSubscribe() {
            @Override
            public void subscribe(SingleEmitter emitter) throws Exception {
                loadImage(url, imageView);
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getImageView() {
        Single observable = getImage(); //single es un observer
        //con un observer te subscriber
        observable.subscribe(new SingleObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", "Error "+e);
            }
        });
    }

    private void loadImage(String url, ImageView image) {
        Picasso.get()
                .load(url)
                .into(image);
    }
}
