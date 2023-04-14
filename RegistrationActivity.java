package com.example.worldskills;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationActivity extends ComponentActivity {
    TextInputEditText emailInput;
    MaterialButton sendButton;
    public static final int REQUEST_VIEW=1;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        emailInput = findViewById(R.id.textInputEditText);
        sendButton = findViewById(R.id.sendButton);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_VIEW && resultCode == RESULT_OK) {
            settings = getSharedPreferences("SETTINGS", MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("PIN_CODE_ADDED", false);
            Intent intent = new Intent(getApplicationContext(), pinCodeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void OnClick(View view) {
        switch(view.getId()) {
            case R.id.sendButton:
                Intent intent = new Intent(getApplicationContext(), emailVerificationActivity.class);
                startActivityForResult(intent, REQUEST_VIEW);
        }
    }
}