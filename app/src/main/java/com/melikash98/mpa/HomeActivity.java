package com.melikash98.mpa;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.melikash98.morphnavbar.MorphNavBar;
import com.melikash98.morphnavbar.MorphNavTabItem;

public class HomeActivity extends AppCompatActivity {
    public MorphNavBar navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        findById();


        navBar.setTabs(
                new MorphNavTabItem.Model("Home", R.drawable.house),
                new MorphNavTabItem.Model("Resume", R.drawable.cv),
                new MorphNavTabItem.Model("Projects", R.drawable.diagram)
        );
        navBar.setSelectedIndex(1);

    }

    private void findById() {
        navBar = findViewById(R.id.morphNavBar);
    }
}