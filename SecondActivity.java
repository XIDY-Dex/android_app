package com.example.worldskills;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SecondActivity extends AppCompatActivity {
    ViewPager2 pager;
    TabLayout indicator;
    TextView textButton;
    SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        pager = findViewById(R.id.pager);
        indicator = findViewById(R.id.indicator);
        textButton = findViewById(R.id.skipLabel);

        PageFragmentAdapter pagerAdapter = new PageFragmentAdapter(getSupportFragmentManager(), this.getLifecycle());
        pager.setAdapter(pagerAdapter);
        new TabLayoutMediator(indicator, pager, (tab, position) -> {}).attach();
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position == 2) {
                    textButton.setText("Завершить");
                }
                else {
                    textButton.setText("Пропустить");
                }
            }
        });
    }

    public void onClick(View v) {
        settings = getSharedPreferences("SETTINGS", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("STATUS", 1);
        editor.apply();
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }
}