package com.melikash98.mpa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.melikash98.mpa.Model.NavigationCardModel;
import com.melikash98.mpa.R;

import java.util.List;

public class NavigationCardAdapter extends RecyclerView.Adapter<NavigationCardAdapter.ViewHolder> {
    private Context context;
    private List<NavigationCardModel> model;

    public NavigationCardAdapter(Context context, List<NavigationCardModel> model) {
        this.context = context;
        this.model = model;
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
