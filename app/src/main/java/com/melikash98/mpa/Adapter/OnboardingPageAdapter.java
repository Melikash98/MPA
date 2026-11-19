package com.melikash98.mpa.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.melikash98.mpa.Model.OnboardingPage;
import com.melikash98.mpa.R;

import java.util.List;

public class OnboardingPageAdapter extends RecyclerView.Adapter<OnboardingPageAdapter.ViewHolder> {
    private final List<OnboardingPage> pages;

    public OnboardingPageAdapter(List<OnboardingPage> pages) {
        this.pages = pages;
    }

    @NonNull
    @Override
    public OnboardingPageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_onboarding_page, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingPageAdapter.ViewHolder holder, int position) {
        OnboardingPage onboardingPage = pages.get(position);
        holder.img.setImageResource(onboardingPage.onboardingImage);
        holder.tittle.setText(onboardingPage.onboardingTitle);
        holder.accent.setText(onboardingPage.onboardingAccent);
        holder.body.setText(onboardingPage.onboardingBody);

    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img;
        private final TextView tittle,accent,body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.entryImage);
            tittle=itemView.findViewById(R.id.entryTitle);
            accent = itemView.findViewById(R.id.entryAccent);
            body = itemView.findViewById(R.id.entryBody);
        }
    }
}
