package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    Animation animationImg, animationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        animationImg = AnimationUtils.loadAnimation(this, R.anim.img_animation);
        animationText = AnimationUtils.loadAnimation(this,R.anim.text_animation);

        imageView.setAnimation(animationImg);
        textView.setAnimation(animationText);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                startActivity (new Intent(Splash_Screen.this, MainActivity.class));
                finish();

            }
        }.start();

    }
}