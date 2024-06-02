package com.example.fitnesapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        fragmentManager = getSupportFragmentManager();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.homepage:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.profile:
                        selectedFragment = new ProfileFragment();
                        break;
                    case R.id.setting:
                        selectedFragment = new OptionFragment();
                        break;
                    case R.id.exercise:
                        selectedFragment = new ExerciseFragment();
                        break;
                    case R.id.titleTextView:
                        selectedFragment = new WorkoutFragment();
                        break;
                }
                if (selectedFragment != null) {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.homepage);
        }
    }
}
