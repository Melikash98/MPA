package com.melikash98.mpa.Model;

import androidx.annotation.DrawableRes;

public class OnboardingPage {
    @DrawableRes
    public final int onboardingImage;
    public final String onboardingTitle;
    public final String onboardingAccent;
    public final String onboardingBody;

    public OnboardingPage(int onboardingImage, String onboardingTitle, String onboardingAccent, String onboardingBody) {
        this.onboardingImage = onboardingImage;
        this.onboardingTitle = onboardingTitle;
        this.onboardingAccent = onboardingAccent;
        this.onboardingBody = onboardingBody;
    }
}
