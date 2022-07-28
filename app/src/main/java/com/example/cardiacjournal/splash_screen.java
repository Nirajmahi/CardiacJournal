package com.example.cardiacjournal;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    Animation leftanim,rightanim;
        ImageView logo,logotext;
        private static int SPLASH_SCREEN = 3500;

    /**
     * this is for the creation of splash screen
     * @param savedInstanceState
     */
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_splash_screen);
            leftanim= AnimationUtils.loadAnimation(this
                    ,R.anim.left_right_animation);
            rightanim= AnimationUtils.loadAnimation(this ,R.anim.right_left_animation);
            logo=findViewById(R.id.logo);
            logotext=findViewById(R.id.logotext);
            logo.setAnimation(rightanim);
            logotext.setAnimation(leftanim);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent =new Intent(splash_screen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },SPLASH_SCREEN);

        }}


