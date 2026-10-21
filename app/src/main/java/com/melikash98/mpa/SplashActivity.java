package com.melikash98.mpa;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        img=findViewById(R.id.logoImg);
        img.setAlpha(0f);
        img.setScaleX(0.7f);
        img.setScaleY(0.7f);
        img.setTranslationY(40f);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(
                img, View.ALPHA, 0f, 1f
        );
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(
                img, View.SCALE_X, 0.7f, 1f
        );

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(
                img, View.SCALE_Y, 0.7f, 1f
        );
        ObjectAnimator moveUp = ObjectAnimator.ofFloat(
                img, View.TRANSLATION_Y, 40f, 0f
        );
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                fadeIn, scaleX, scaleY, moveUp
        );

        animatorSet.setDuration(900);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
    }
}