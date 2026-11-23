package com.melikash98.mpa.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.melikash98.mpa.Model.NavigationCardModel;
import com.melikash98.mpa.R;

import java.util.List;

public class NavigationCardAdapter extends RecyclerView.Adapter<NavigationCardAdapter.ViewHolder> {
    private Context context;
    private List<NavigationCardModel> model;
    private final OnNavigationClickListener listener;

    public interface OnNavigationClickListener {
        void onNavigationClick(String id);
    }

    public NavigationCardAdapter(OnNavigationClickListener listener, List<NavigationCardModel> model, Context context) {
        this.listener = listener;
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public NavigationCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_navigation_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationCardAdapter.ViewHolder holder, int position) {
        NavigationCardModel cardModel = model.get(position);
        holder.name.setText(cardModel.getName());
        holder.icon.setImageResource(cardModel.getImg());

        int iconColor = ContextCompat.getColor(
                context,
                cardModel.getImgColor()
        );

        ImageViewCompat.setImageTintList(
                holder.icon,
                ColorStateList.valueOf(iconColor)
        );
        int bgColor = ContextCompat.getColor(
                context,
                cardModel.getImgBgColor()
        );

        ViewCompat.setBackgroundTintList(
                holder.bgIcon,
                ColorStateList.valueOf(bgColor)
        );
        holder.layout.setOnClickListener(v -> {

            int currentPosition = holder.getBindingAdapterPosition();

            if (currentPosition != RecyclerView.NO_POSITION
                    && listener != null) {

                NavigationCardModel selectedCard =
                        model.get(currentPosition);

                listener.onNavigationClick(selectedCard.getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout layout;
        private ImageView icon, bgIcon;
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cadr_list);
            icon = itemView.findViewById(R.id.icon_nav_card);
            bgIcon = itemView.findViewById(R.id.bg_icon_nav_card);
            name = itemView.findViewById(R.id.name_nav_list_card);
        }
    }
}
