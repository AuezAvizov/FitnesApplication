package com.example.fitnesapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fitnesapplication.Yoga.Category;

public class ExerciseDetailActivity extends AppCompatActivity {

    private TextView categoryName;
    private TextView poseName;
    private TextView description;
    private ImageView exerciseImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        categoryName = findViewById(R.id.categoryName);
        poseName = findViewById(R.id.poseName);
        description = findViewById(R.id.description);
        exerciseImage = findViewById(R.id.exerciseImage);

        Category exercise = (Category) getIntent().getSerializableExtra("exercise");

        if (exercise != null) {
            categoryName.setText(exercise.getCategoryName());
            poseName.setText(exercise.getEnglishName());
            description.setText(exercise.getPoseDescription());
            Glide.with(this)
                    .load(exercise.getUrlPng())
                    .into(exerciseImage);
        }
    }
}