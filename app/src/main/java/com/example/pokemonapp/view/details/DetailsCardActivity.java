package com.example.pokemonapp.view.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokemonapp.R;
import com.example.pokemonapp.view.main.MainActivity;
import com.squareup.picasso.Picasso;

public class DetailsCardActivity extends AppCompatActivity {
    private TextView nameCard;
    private ImageView cardView;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaills_card);
        configureView();
    }

    private void configureView() {
        nameCard = findViewById(R.id.nameCard);
        cardView = findViewById(R.id.imageCard);
        returnButton = findViewById(R.id.toReturn);
        String name = getIntent().getStringExtra("name");
        nameCard.setText(name);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsCardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        getImage();
    }

    private void getImage(){
        String url = getIntent().getStringExtra("url");
        //Image image=new Image(url, cardView);
        //image.getImageView();
        Picasso.get().load(url).into(cardView);
    }


}
