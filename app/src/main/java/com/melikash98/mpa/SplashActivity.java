package com.melikash98.mpa;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.os.Build;
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
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Lifecycle;

import com.melikash98.mpa.Class.SignatureImageView;

public class SplashActivity extends AppCompatActivity {

    private static final long LOADING_DURATION_MS = 9 * 1000L;
    private static final long DOT_STEP_MS = 1000L;
    private static final long DOT_FADE_MS = 700L;

    private ValueAnimator[] dotAnimators;
    private int[] dotColors;
    private int activeColor;
    private int inactiveColor;

    private View[] dots;
    private int currentDot = 0;

    private boolean navigationStarted = false;
    private boolean loadingFinished = false;

    private ImageView[] star;
    private ImageView logoImg;
    private Animation fade;
    private SignatureImageView textImg;
    private TextView[] text;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable dotsRunnable = new Runnable() {
        @Override
        public void run() {
            if (isFinishing() || isDestroyed() || navigationStarted) return;

            currentDot = (currentDot + 1) % dots.length;
            selectDot(currentDot);

            handler.postDelayed(this, DOT_STEP_MS);
        }
    };

    private final Runnable finishLoadingRunnable = () -> {
        loadingFinished = true;
        goToIntro();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        findID();
        selectDot(0);
        handler.postDelayed(dotsRunnable, DOT_STEP_MS);
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
        handler.postDelayed(finishLoadingRunnable, LOADING_DURATION_MS);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (loadingFinished) {
            goToIntro();
        }
    }

    private void goToIntro() {
        if (navigationStarted || isFinishing() || isDestroyed()) return;
        if (!getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            return;
        }

        navigationStarted = true;
        handler.removeCallbacksAndMessages(null);

        Intent intent = new Intent(this, EntryActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                this,
                R.anim.activity_enter_from_bottom,
                R.anim.activity_exit_fade
        );
        startActivity(intent, options.toBundle());
        finish();
    }

    private void selectDot(int index) {
        for (int i = 0; i < dots.length; i++) {
            animateDotColor(i, i == index ? activeColor : inactiveColor);
        }
    }

    private void animateDotColor(int i, int toColor) {
        final View dot = dots[i];
        int fromColor = dotColors[i];
        if (fromColor == toColor) return;

        if (dotAnimators[i] != null) dotAnimators[i].cancel();

        ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
        animator.setDuration(DOT_FADE_MS);
        animator.addUpdateListener(a -> {
            int color = (int) a.getAnimatedValue();
            dotColors[i] = color;
            dot.setBackgroundTintList(ColorStateList.valueOf(color));
        });
        dotAnimators[i] = animator;
        animator.start();
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
        dots = new View[]{
                findViewById(R.id.dot1),
                findViewById(R.id.dot2),
                findViewById(R.id.dot3)
        };
        activeColor = ContextCompat.getColor(this, R.color.gold);
        inactiveColor = 0xFF2A3350;
        dotAnimators = new ValueAnimator[dots.length];
        dotColors = new int[dots.length];
        for (int i = 0; i < dots.length; i++) {
            dotColors[i] = inactiveColor;
            dots[i].setBackgroundTintList(ColorStateList.valueOf(inactiveColor));
        }
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
        if (dotAnimators != null) {
            for (ValueAnimator a : dotAnimators) {
                if (a != null) a.cancel();
            }
        }

        super.onDestroy();
    }
}