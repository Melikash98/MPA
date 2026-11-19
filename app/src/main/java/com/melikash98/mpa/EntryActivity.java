package com.melikash98.mpa;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.melikash98.mpa.Adapter.OnboardingPageAdapter;
import com.melikash98.mpa.Model.OnboardingPage;

import java.util.ArrayList;
import java.util.List;

public class EntryActivity extends AppCompatActivity {

    private List<OnboardingPage> onboardingPageList;
    private ViewPager2 view;
    private LinearLayout dots;
    private AppCompatButton btn,skip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        findId();
        loadingPages();
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipPage();
            }
        });

    }

    private void skipPage() {
        startActivity(new Intent(EntryActivity.this, HomeActivity.class));
        finish();
    }

    private void loadingPages() {
        onboardingPageList = new ArrayList<>();
        onboardingPageList.add(new OnboardingPage(R.drawable.img_entry_one,
                "Work that", "speaks for itself",
                "Browse real apps and systems built with Java, Spring Boot and Android. Tap any project to see the story behind it."));
        onboardingPageList.add(new OnboardingPage(R.drawable.img_entry_two,
                "Learn by", "building real things",
                "Step-by-step video tutorials and courses. Start free, upgrade when you want the full path."));
        onboardingPageList.add(new OnboardingPage(R.drawable.img_entry_three,
                "Hire, sign &", "pay in one place",
                "Request a project, review the proposal, sign the contract and pay securely, all from your phone. No account needed."));
        view.setAdapter(new OnboardingPageAdapter(onboardingPageList));
        setupDots(onboardingPageList.size());
        updateDots(0,0);

        view.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updateDots(position, positionOffset);
            }
            @Override
            public void onPageSelected(int position) {
                if (position == onboardingPageList.size() - 1) {
                    btn.setText("Get started");
                    skip.setVisibility(View.INVISIBLE);
                } else {
                    btn.setText("Continue");
                    skip.setVisibility(View.VISIBLE);
                }
            }
        });
        btn.setOnClickListener(v -> {
            if (view.getCurrentItem() < onboardingPageList.size() - 1) {
                view.setCurrentItem(view.getCurrentItem() + 1);
            } else {
                startActivity(new Intent(EntryActivity.this, HomeActivity.class));
                finish();
            }
        });
    }
    private void setupDots(int count) {
        dots.removeAllViews();
        float density = getResources().getDisplayMetrics().density;
        int size = (int) (8 * density);
        int margin = (int) (4 * density);

        for (int i = 0; i < count; i++) {
            View dot = new View(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(size, size);
            lp.setMargins(margin, 0, margin, 0);
            dot.setLayoutParams(lp);

            GradientDrawable shape = new GradientDrawable();
            shape.setCornerRadius(4 * density);
            shape.setColor(ContextCompat.getColor(this, R.color.bg_color));
            dot.setBackground(shape);

            dots.addView(dot);
        }
    }
    private void updateDots(int position, float offset) {
        float density = getResources().getDisplayMetrics().density;
        int smallWidth = (int) (8 * density);
        int bigWidth = (int) (28 * density);

        int grayColor = ContextCompat.getColor(this, com.melikash98.editify.R.color.gray);
        int goldColor = ContextCompat.getColor(this, R.color.gold_dark);
        ArgbEvaluator evaluator = new ArgbEvaluator();

        for (int i = 0; i < dots.getChildCount(); i++) {
            View dot = dots.getChildAt(i);


            float fraction = 0;
            if (i == position) {
                fraction = 1 - offset;
            } else if (i == position + 1) {
                fraction = offset;
            }

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) dot.getLayoutParams();
            params.width = (int) (smallWidth + (bigWidth - smallWidth) * fraction);
            dot.setLayoutParams(params);

            int color = (int) evaluator.evaluate(fraction, grayColor, goldColor);
            ((GradientDrawable) dot.getBackground()).setColor(color);
        }
    }

    private void findId() {
        view = findViewById(R.id.entryPager);
        dots=findViewById(R.id.dots);
        btn=findViewById(R.id.continueButton);
        skip=findViewById(R.id.skipButton);
    }
}