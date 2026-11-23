package com.melikash98.mpa;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.melikash98.mpa.Adapter.NavigationCardAdapter;
import com.melikash98.mpa.Model.NavigationCardModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private TextView goldText, projectMBtn;
    private AppCompatButton hireBtn, projectBtn;
    private RecyclerView navCard,projectRec;
    private List<NavigationCardModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findID();
        setupNavigationRecycler();
        hireBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HireMeActivity.class));
                finish();
            }
        });
        projectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProjectsLibActivity.class));
                finish();
            }
        });
        projectMBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProjectsLibActivity.class));
                finish();
            }
        });


    }

    private void setupNavigationRecycler() {
        navCard.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        models = new ArrayList<>();
        models.add(new NavigationCardModel("hire_me", R.string.hire_me, R.drawable.work, R.color.gold, R.color.gold_circle));
        models.add(new NavigationCardModel("project", R.string.project, R.drawable.project_icon, R.color.green_dot, R.color.teal_circle));
        models.add(new NavigationCardModel("service", R.string.service, R.drawable.service_icon, R.color.purple_200, R.color.purple_circle));
        models.add(new NavigationCardModel("courses", R.string.courses, R.drawable.courses_icon, R.color.outline_blue, R.color.blue_circle));
        navCard.setAdapter(new NavigationCardAdapter(this::openNavigationPage, models, this));
    }

    private void openNavigationPage(String id) {

        Intent intent;

        switch (id) {

            case "hire_me":
                intent = new Intent(this, HireMeActivity.class);
                break;

            case "project":
                intent = new Intent(this, ProjectsLibActivity.class);
                break;

            case "service":
                openServiceFragment();
                return;

            case "courses":
                intent = new Intent(this, CourseslIBActivity.class);
                break;

            default:
                return;
        }

        startActivity(intent);
    }

    private void openServiceFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(
                        android.R.id.content,
                        new ServicesFragment(),
                        "SERVICE_FRAGMENT"
                )
                .addToBackStack("service")
                .commit();
    }

    private void findID() {
        hireBtn = findViewById(R.id.hire_btn);
        projectBtn = findViewById(R.id.work_btn);
        navCard = findViewById(R.id.navigation_card_rec);
        projectMBtn = findViewById(R.id.go_to_project_btn);
        projectRec = findViewById(R.id.new_project_rec);
    }
}