package com.melikash98.mpa;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {
    private ImageView img, bgImg, logoText;
    private MediaPlayer effectPlayer;
    private AnimatorSet splashSequence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        img = findViewById(R.id.logoImg);
        bgImg = findViewById(R.id.bgImg);
        logoText = findViewById(R.id.logoText);
        effectPlayer = MediaPlayer.create(this, R.raw.mixkit_magic_sparkle_whoosh_2350);

        if (effectPlayer != null) {
            effectPlayer.setVolume(0.75f, 0.75f);
        }

        bgImg.setAlpha(0.82f);
        bgImg.setScaleX(1.04f);
        bgImg.setScaleY(1.04f);

        img.setAlpha(0f);
        img.setScaleX(0.6f);
        img.setScaleY(0.6f);
        img.setTranslationY(55f);

        logoText.setAlpha(0f);
        logoText.setScaleX(0.94f);
        logoText.setScaleY(0.94f);
        logoText.setTranslationY(25f);

        ObjectAnimator bgFade = ObjectAnimator.ofFloat(
                bgImg, View.ALPHA, 0.82f, 1f
        );

        ObjectAnimator bgScaleX = ObjectAnimator.ofFloat(
                bgImg, View.SCALE_X, 1.04f, 1.08f
        );

        ObjectAnimator bgScaleY = ObjectAnimator.ofFloat(
                bgImg, View.SCALE_Y, 1.04f, 1.08f
        );

        AnimatorSet bgAnimation = new AnimatorSet();
        bgAnimation.playTogether(bgFade, bgScaleX, bgScaleY);
        bgAnimation.setDuration(1400);
        bgAnimation.setInterpolator(
                new AccelerateDecelerateInterpolator()
        );

        ObjectAnimator imgFade = ObjectAnimator.ofFloat(
                img, View.ALPHA, 0f, 1f
        );

        ObjectAnimator imgScaleX = ObjectAnimator.ofFloat(
                img, View.SCALE_X, 0.6f, 1.04f, 1f
        );

        ObjectAnimator imgScaleY = ObjectAnimator.ofFloat(
                img, View.SCALE_Y, 0.6f, 1.04f, 1f
        );

        ObjectAnimator imgMove = ObjectAnimator.ofFloat(
                img, View.TRANSLATION_Y, 55f, 0f
        );

        imgFade.setInterpolator(new DecelerateInterpolator(2f));
        imgMove.setInterpolator(new DecelerateInterpolator(2f));
        imgScaleX.setInterpolator(new OvershootInterpolator(1.15f));
        imgScaleY.setInterpolator(new OvershootInterpolator(1.15f));

        AnimatorSet imgAnimation = new AnimatorSet();
        imgAnimation.playTogether(
                imgFade,
                imgScaleX,
                imgScaleY,
                imgMove
        );

        long imgDuration = 1000L;
        long textDuration = 850L;

        imgAnimation.setDuration(imgDuration);

        imgAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (effectPlayer != null) {
                    effectPlayer.start();
                }
            }
        });

        ObjectAnimator textFade = ObjectAnimator.ofFloat(
                logoText, View.ALPHA, 0f, 1f
        );

        ObjectAnimator textMove = ObjectAnimator.ofFloat(
                logoText, View.TRANSLATION_Y, 25f, 0f
        );

        ObjectAnimator textScaleX = ObjectAnimator.ofFloat(
                logoText, View.SCALE_X, 0.94f, 1f
        );

        ObjectAnimator textScaleY = ObjectAnimator.ofFloat(
                logoText, View.SCALE_Y, 0.94f, 1f
        );

        AnimatorSet textAnimation = new AnimatorSet();
        textAnimation.playTogether(
                textFade,
                textMove,
                textScaleX,
                textScaleY
        );

        textAnimation.setDuration(textDuration);
        textAnimation.setInterpolator(
                new DecelerateInterpolator(2f)
        );

        ValueAnimator pauseBeforeImg = ValueAnimator.ofFloat(0f, 1f);
        pauseBeforeImg.setDuration(350L);

        long soundDuration = effectPlayer != null
                ? effectPlayer.getDuration()
                : 0L;

        long pauseDuration = Math.max(
                0L,
                soundDuration - imgDuration - textDuration
        );

        ValueAnimator pauseBeforeText = ValueAnimator.ofFloat(0f, 1f);
        pauseBeforeText.setDuration(pauseDuration);

        splashSequence = new AnimatorSet();
        splashSequence.playSequentially(
                bgAnimation,
                pauseBeforeImg,
                imgAnimation,
                pauseBeforeText,
                textAnimation
        );

        splashSequence.start();
    }

    @Override
    protected void onDestroy() {
        if (splashSequence != null) {
            splashSequence.cancel();
        }

        if (effectPlayer != null) {
            if (effectPlayer.isPlaying()) {
                effectPlayer.stop();
            }

            effectPlayer.release();
            effectPlayer = null;
        }

        super.onDestroy();
    }
}