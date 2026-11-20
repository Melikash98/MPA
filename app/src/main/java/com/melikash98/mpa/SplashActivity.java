package com.melikash98.mpa;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.melikash98.mpa.Class.SignatureImageView;

public class SplashActivity extends AppCompatActivity {
    private View[] dots;
    private ImageView[] star;
    private ImageView logoImg;
    private Animation fade;
    private SignatureImageView textImg;
    private TextView[] text;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        findID();
        logoImg.setVisibility(View.VISIBLE);
        logoImg.startAnimation(fade);
        fade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                startStarsAnimation();
                textImg.setVisibility(View.VISIBLE);
                textImg.startWritingAnimation();
                startTextAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }

            @Override
            public void onAnimationStart(Animation animation) {

            }
        });

    }

    private void startTextAnimation() {
        animateText(0);
    }

    private void animateText(int index) {

        if (index >= text.length) {
            return;
        }

        TextView currentText = text[index];

        currentText.setVisibility(View.VISIBLE);

        Animation textAnimation = AnimationUtils.loadAnimation(
                SplashActivity.this,
                R.anim.fade_up
        );

        textAnimation.setAnimationListener(
                new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        if (isFinishing() || isDestroyed()) {
                            return;
                        }

                        animateText(index + 1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

        currentText.startAnimation(textAnimation);
    }
    private void startStarsAnimation() {

        for (int i = 0; i < star.length; i++) {

            final ImageView stars = star[i];


            stars.setVisibility(View.INVISIBLE);


            handler.postDelayed(() -> {

                if (isFinishing() || isDestroyed()) {
                    return;
                }

                stars.setVisibility(View.VISIBLE);
                Animation appear = AnimationUtils.loadAnimation(
                        SplashActivity.this,
                        R.anim.star_appear
                );
                appear.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        if (isFinishing() || isDestroyed()) {
                            return;
                        }
                        Animation shine = AnimationUtils.loadAnimation(
                                SplashActivity.this,
                                R.anim.star_shine
                        );

                        stars.startAnimation(shine);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                stars.startAnimation(appear);

            }, i * 250L);
        }
    }

    private void findID() {
        logoImg = findViewById(R.id.logoImg);
        textImg = findViewById(R.id.logoText);
        text = new TextView[]{
                findViewById(R.id.text_one),
                findViewById(R.id.text_two)
        };
        star = new ImageView[]{
                findViewById(R.id.star_one),
                findViewById(R.id.star_two),
                findViewById(R.id.star_three),
                findViewById(R.id.star_foure),
                findViewById(R.id.star_five),
                findViewById(R.id.star_sex),
                findViewById(R.id.star_seven)
        };
        for (ImageView star : star) {
            star.setVisibility(View.INVISIBLE);
        }
        for (TextView currentText : text) {
            currentText.setVisibility(View.INVISIBLE);
        }
        textImg.setVisibility(View.INVISIBLE);

        //Animations
        fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
    }

    @Override
    protected void onDestroy() {

        handler.removeCallbacksAndMessages(null);

        if (star != null) {
            for (ImageView star : star) {
                star.clearAnimation();
            }
        }

        if (logoImg != null) {
            logoImg.clearAnimation();
        }

        super.onDestroy();
    }
}