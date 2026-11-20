package com.melikash98.mpa.Class;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.widget.AppCompatImageView;

public class SignatureImageView extends AppCompatImageView {

    private float revealProgress = 0f;

    private ObjectAnimator writingAnimator;

    public SignatureImageView(Context context) {
        super(context);
    }

    public SignatureImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SignatureImageView(Context context,
                              AttributeSet attrs,
                              int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRevealProgress(float progress) {
        revealProgress = progress;
        invalidate();
    }

    public float getRevealProgress() {
        return revealProgress;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int save = canvas.save();

        // Reveal signature from left to right
        canvas.clipRect(
                0,
                0,
                getWidth() * revealProgress,
                getHeight()
        );

        super.onDraw(canvas);

        canvas.restoreToCount(save);
    }

    public void startWritingAnimation() {

        setRevealProgress(0f);

        writingAnimator = ObjectAnimator.ofFloat(
                this,
                "revealProgress",
                0f,
                1f
        );

        writingAnimator.setDuration(3500);

        writingAnimator.setInterpolator(
                new LinearInterpolator()
        );

        writingAnimator.start();
    }
}
