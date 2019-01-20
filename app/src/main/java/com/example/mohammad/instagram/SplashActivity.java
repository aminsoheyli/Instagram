package com.example.mohammad.instagram;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    private Animation slideUp;
    private ImageView instagramLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        instagramLogo = findViewById(R.id.instagram_logo);
        slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        instagramLogo.startAnimation(slideUp);


        SQLiteDatabase db = openOrCreateDatabase("project", MODE_PRIVATE, null);
        db.execSQL("create table if not exists comment (comment_id text, comment_text text, post_id text, user_id text, comment_parent text);");
        db.execSQL("create table if not exists post (post_id text, user_id text, post_date text, image blob);");
        db.execSQL("create table if not exists save(post_id text, user_id text);");
        db.execSQL("create table if not exists likes(post_id text, user_id text);");
        db.execSQL("create table if not exists user(user_id text, user_password text, follower_id text);");


        CountDownTimer count = new CountDownTimer(4000, 4000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent;
                if (isLoggedIn()) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    // Display the com.example.mohammad.instagram.HomeFragment for last logged in user
                    // else Display the LoginActivity
                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();




    }

    private boolean isLoggedIn() {
        // If the user has been logged in, at last state of the app
        // don't display the LoginActivity, instead display the com.example.mohammad.instagram.HomeFragment
        // of the last logged in user.
        if (false)
            return true;
        else
            return false;
    }
}
