package com.example.worldskills;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationActivity extends ComponentActivity {
    TextInputEditText emailInput;
    MaterialButton sendButton;
    SharedPreferences settings;
    public Boolean RESULT=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        emailInput = findViewById(R.id.textInputEditText);
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            RESULT = sendTaskCode.handleResult(sendTaskCode.sendCode(email));
                        }
                        catch(Exception e) {
                            Log.d("REQUEST", "REQUEST FAILED");
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                if(RESULT) {
                    Intent intent = new Intent(getApplicationContext(), emailVerificationActivity.class);
                    intent.putExtra("EMAIL", emailInput.getText().toString());
                    startActivity(intent);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Произошла ошибка при авторизации", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String email = emailInput.getText().toString();
                if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    sendButton.setEnabled(true);
                    sendButton.setBackgroundColor(getResources().getColor(R.color.activeButton));
                }
                else {
                    sendButton.setEnabled(false);
                    sendButton.setBackgroundColor(getResources().getColor(R.color.inactiveButton));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
}