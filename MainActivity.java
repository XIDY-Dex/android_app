package com.example.worldskills;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;

public class MainActivity extends ComponentActivity {
    SharedPreferences settings;
    DbHelper dbHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Handler handler = new Handler(Looper.getMainLooper());
        dbHelper = new DbHelper(this);
        db = dbHelper.getReadableDatabase();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                settings = getSharedPreferences("SETTINGS", MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                Boolean email_confirmed = settings.getBoolean("EMAIL_CONFIRMED", false);
                userCursor = db.rawQuery("SELECT * FROM " + dbHelper.PATIENT_PROFILES_TABLE, null);
                if(userCursor.moveToNext() != false) {
                    editor.putBoolean("PROFILE_CREATED", true);
                    editor.apply();
                }
                if (email_confirmed) {
                    Intent intentPin = new Intent(getApplicationContext(), pinCodeActivity.class);
                    startActivity(intentPin);
                    finish();
                } else {
                    int status = settings.getInt("STATUS", 0);
                    if (status == 0) {
                        intent = new Intent(getApplicationContext(), SecondActivity.class);
                    } else {
                        intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }
}