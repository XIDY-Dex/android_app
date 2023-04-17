package com.example.worldskills;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainPage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    AnalyzeFragment analyzeFragment = new AnalyzeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    SupportFragment supportFragment = new SupportFragment();
    ResultsFragment resultsFragment = new ResultsFragment();
    BottomNavigationView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        menu = findViewById(R.id.bottomNavigationView);
        menu.setOnNavigationItemSelectedListener(this);
        menu.setSelectedItemId(R.id.analyzesBar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.analyzesBar:
                analyzeFragment.updateNews();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentView, analyzeFragment).commit();
                return true;
            case R.id.profileBar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentView, profileFragment).commit();
                return true;
            case R.id.supportBar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentView, supportFragment).commit();
            case R.id.resultBar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentView, resultsFragment).commit();
        }
        return false;
    }
}