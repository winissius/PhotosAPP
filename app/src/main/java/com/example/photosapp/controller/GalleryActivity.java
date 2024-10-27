package com.example.photosapp.controller;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.photosapp.R;

public class GalleryActivity extends AppCompatActivity {

    ImageView imageView;
    TextView indexTextView;
    Button previousButton, nextButton;

    int index = 0;
    String []images = new String[]{"white_cat","black_cat","orange_cat","grey_cat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gallery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageView = findViewById(R.id.imageView);
        indexTextView = findViewById(R.id.indexTextView);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                updateImageDetails();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                updateImageDetails();
            }
        });

        updateImageDetails();
    }


    void updateImageDetails(){
        if(index <= 0) {
            index = 0;
            previousButton.setVisibility(View.INVISIBLE);
        }else{
            previousButton.setVisibility(View.VISIBLE);
        }
        if(index >= (images.length-1)){
            index = images.length - 1;
            nextButton.setVisibility(View.INVISIBLE);
        }else{
            nextButton.setVisibility(View.VISIBLE);
        }
        imageView.setImageDrawable(getImageDrawable(images[index]));
        indexTextView.setText(""+(index+1)+"/"+images.length);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    Drawable getImageDrawable(String imageName){
        return getResources().getDrawable(
                getResources().getIdentifier(imageName, "drawable", getPackageName()),
                getTheme()
        );
    }
}