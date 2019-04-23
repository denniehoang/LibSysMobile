package com.example.libsysmobile.login_pages;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.libsysmobile.MainActivity;
import com.example.libsysmobile.R;

public class SplashPage extends AppCompatActivity {

    private ImageView splashLogo;
    private static int splashTimeOut=5000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);
        splashLogo = (ImageView)findViewById(R.id.splash_logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent timer = new Intent(SplashPage.this, MainActivity.class);
                startActivity(timer);
                finish();
            }
        },splashTimeOut);

        Animation splashAnimation = AnimationUtils.loadAnimation(this,R.anim.splash_animation);
        splashLogo.startAnimation(splashAnimation);
    }
}
