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
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private View[] dots;
    private ColorStateList activeColor;
    private ColorStateList inactiveColor;
    private int current = 0;
    private int ticks = 0;
    private static final int STEP_DELAY = 900;
    private static final int TOTAL_TICKS = 10;

    private final Runnable loader = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < dots.length; i++) {
                dots[i].setBackgroundTintList(i == current ? activeColor : inactiveColor);
            }
            current = (current + 1) % dots.length;
            ticks++;

            if (ticks >= TOTAL_TICKS) {
                startActivity(new Intent(SplashActivity.this, EntryActivity.class));
                finish();
            } else {
                handler.postDelayed(this, STEP_DELAY);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        dots = new View[]{
                findViewById(R.id.dot1),
                findViewById(R.id.dot2),
                findViewById(R.id.dot3)
        };
        activeColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gold));
        inactiveColor = ColorStateList.valueOf(0xFF2A3350);

        handler.post(loader);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(loader);
    }

}