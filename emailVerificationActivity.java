package com.example.worldskills;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Timer;
import java.util.stream.IntStream;

public class emailVerificationActivity extends ComponentActivity {
    TextView secondsLeft;
    MaterialButton resetButton;
    TextInputEditText firstInput, secondInput, thirdInput, fourthInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        secondsLeft = findViewById(R.id.secondsLeft);
        resetButton = findViewById(R.id.resetButton);
        resetButton.setVisibility(View.INVISIBLE);
        int[] numbers = {-1, -1, -1, -1};
        firstInput = findViewById(R.id.firstInput);
        secondInput = findViewById(R.id.secondInput);
        thirdInput = findViewById(R.id.thirdInput);
        fourthInput = findViewById(R.id.fourthInput);


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
        if(!IntStream.of(numbers).anyMatch(x -> x == -1)) {
            Log.d("INPUT", "PASSWORD");
        }
    }
}