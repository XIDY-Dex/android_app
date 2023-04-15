package com.example.worldskills;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class pinCodeActivity extends ComponentActivity {
    ImageView firstDot, secondDot, thirdDot, fourthDot;
    ImageView[] inputDots;
    TextView subLabelText, labelText;
    static String PIN = "";
    SharedPreferences settings;
    Boolean PinSet, ProfileCreated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_code);
        firstDot = findViewById(R.id.firstInputDot);
        secondDot = findViewById(R.id.secondInputDot);
        thirdDot = findViewById(R.id.thirdInputDot);
        fourthDot = findViewById(R.id.fourthInputDot);
        inputDots = new ImageView[]{firstDot, secondDot, thirdDot, fourthDot};
        settings = getSharedPreferences("SETTINGS", MODE_PRIVATE);
        PinSet = settings.getBoolean("PIN_SET", false);
        ProfileCreated = settings.getBoolean("PROFILE_CREATED", false);
        subLabelText = findViewById(R.id.subLabelText);
        labelText = findViewById(R.id.labelText);
        if(PinSet) {
            labelText.setText("Введите пароль");
            subLabelText.setText("Для входа в профиль");
        }
    }

    public void onClick(View v) {
        if(PIN.length() >= 0 && PIN.length() < 4) {
            switch (v.getId()) {
                case R.id.oneButton:
                    PIN += "1";
                    break;
                case R.id.twoButton:
                    PIN += "2";
                    break;
                case R.id.threeButton:
                    PIN += "3";
                    break;
                case R.id.fourButton:
                    PIN += "4";
                    break;
                case R.id.fiveButton:
                    PIN += "5";
                    break;
                case R.id.sixButton:
                    PIN += "6";
                    break;
                case R.id.sevenButton:
                    PIN += "7";
                    break;
                case R.id.eightButton:
                    PIN += "8";
                    break;
                case R.id.nineButton:
                    PIN += "9";
                    break;
                case R.id.zeroButton:
                    PIN += "0";
                    break;
            }
        }
        if(v.getId() == R.id.clearButton && PIN.length() > 0) {
            PIN = removeLastChar(PIN);
            inputDots[PIN.length()].setImageDrawable(getDrawable(R.drawable.default_dot));
        }
        for(int i = 0; i < PIN.length(); i++) {
            inputDots[i].setImageDrawable(getDrawable(R.drawable.selected_dot));
        }
        if(PIN.length() == 4 && PinSet) {
            String pin_og = settings.getString("PIN_CODE", "");
            if(PIN.equals(pin_og) && ProfileCreated) {
                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);
                finish();
            }
            else if(!PIN.equals(pin_og)){
                subLabelText.setTextColor(getResources().getColor(R.color.red));
                subLabelText.setText("Неверный пароль");
            }
            else if(PIN.equals(pin_og) && !ProfileCreated) {
                Intent intent = new Intent(getApplicationContext(), PatientProfileCreation.class);
                startActivity(intent);
                finish();
            }
        }
        else if(PIN.length() == 4 && !PinSet) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("PIN_CODE", PIN);
            editor.putBoolean("PIN_SET", true);
            editor.apply();
            if(ProfileCreated) {
                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);
                finish();
            }
            else {
                Intent intent = new Intent(getApplicationContext(), PatientProfileCreation.class);
                startActivity(intent);
                finish();
            }
        }
    }

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }

}