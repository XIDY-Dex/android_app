package com.example.worldskills;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;

public class MainActivity extends ComponentActivity {
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                settings = getSharedPreferences("SETTINGS", MODE_PRIVATE);
                int status = settings.getInt("STATUS", 0);
                if(status == 0) {
                    intent = new Intent(getApplicationContext(), SecondActivity.class);
                }
                else {
                    intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}