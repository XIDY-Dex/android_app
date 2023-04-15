package com.example.worldskills;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Timer;
import java.util.stream.IntStream;

public class emailVerificationActivity extends ComponentActivity {
    TextView secondsLeft, labelText;
    MaterialButton resetButton;
    TextInputEditText firstInput, secondInput, thirdInput, fourthInput;
    String email;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        secondsLeft = findViewById(R.id.secondsLeft);
        resetButton = findViewById(R.id.resetButton);
        labelText = findViewById(R.id.labelText);
        resetButton.setVisibility(View.INVISIBLE);
        settings = getSharedPreferences("SETTINGS", MODE_PRIVATE);
        int[] numbers = {-1, -1, -1, -1};
        firstInput = findViewById(R.id.firstInput);
        secondInput = findViewById(R.id.secondInput);
        thirdInput = findViewById(R.id.thirdInput);
        fourthInput = findViewById(R.id.fourthInput);

        Bundle arguments = getIntent().getExtras();
        email = arguments.getString("EMAIL");


        firstInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int input = Integer.parseInt(charSequence.toString());
                    numbers[0] = input;
                }
                catch(NumberFormatException e) {
                    numbers[0] = -1;
                };
            }

            @Override
            public void afterTextChanged(Editable editable) {
                secondInput.setFocusable(true);
                secondInput.requestFocus();
                checkInput(numbers);
            }
        });

        secondInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int input = Integer.parseInt(charSequence.toString());
                    numbers[1] = input;
                }
                catch(NumberFormatException e) {
                    numbers[1] = -1;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                thirdInput.setFocusable(true);
                thirdInput.requestFocus();
                checkInput(numbers);
            }
        });

        thirdInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int input = Integer.parseInt(charSequence.toString());
                    numbers[2] = input;
                }
                catch(NumberFormatException e) {
                    numbers[2] = -1;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                fourthInput.setFocusable(true);
                fourthInput.requestFocus();
                checkInput(numbers);
            }
        });

        fourthInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int input = Integer.parseInt(charSequence.toString());
                    numbers[3] = input;
                }
                catch(NumberFormatException e) {
                    numbers[3] = -1;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInput(numbers);
            }
        });

        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l) {
                String seconds = getResources().getQuantityString(R.plurals.seconds, (int) l/1000, (int) l/1000);
                secondsLeft.setText("Отправить код повторно можно будет через " + seconds);
            }

            @Override
            public void onFinish() {
                secondsLeft.setVisibility(View.INVISIBLE);
                resetButton.setVisibility(View.VISIBLE);
            }
        }.start();



    }

    public void checkInput(int[] numbers) {
        String code = "";
        for(int d: numbers) {
            code += String.valueOf(d);
        }

        if(!IntStream.of(numbers).anyMatch(x -> x == -1)) {
            String finalCode = code;
            Thread t = new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    String token = checkCodeTask.handleResult(checkCodeTask.Authorize(email, finalCode));
                    if(token != "Error") {
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("AUTH_TOKEN", token);
                        editor.putBoolean("PIN_SET", false);
                        editor.putBoolean("EMAIL_CONFIRMED", true);
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), pinCodeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Произошла ошибка в процессе авторизации", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            };
            t.start();
        }
    }
}