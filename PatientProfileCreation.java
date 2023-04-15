package com.example.worldskills;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class PatientProfileCreation extends ComponentActivity implements TextWatcher, AdapterView.OnItemSelectedListener, View.OnClickListener {
    TextInputEditText nameInput, surnameInput, middlenameInput, dateInput, sexInput;
    MaterialButton proceedButton;
    String name, surname, middlename, date, sex;
    Spinner sexChoice;
    DbHelper dbHelper;
    SQLiteDatabase db;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_creation);
        nameInput = findViewById(R.id.nameInputEditText);
        proceedButton = findViewById(R.id.proceedButton);
        surnameInput = findViewById(R.id.surnameInputEditText);
        middlenameInput = findViewById(R.id.middlenameInputEditText);
        dateInput = findViewById(R.id.datenputEditText);
        sexInput = findViewById(R.id.sexInputEditText);
        sexChoice = findViewById(R.id.sexChoice);
        sexChoice.bringToFront();
        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sexChoices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexChoice.setAdapter(adapter);
        sexChoice.setOnItemSelectedListener(this);
        settings = getSharedPreferences("SETTINGS", MODE_PRIVATE);



        nameInput.addTextChangedListener(this);
        surnameInput.addTextChangedListener(this);
        middlenameInput.addTextChangedListener(this);
        sexInput.addTextChangedListener(this);
        dateInput.addTextChangedListener(this);
        proceedButton.setOnClickListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        name = nameInput.getText().toString();
        surname = surnameInput.getText().toString();
        middlename = middlenameInput.getText().toString();
        date = dateInput.getText().toString();
        sex = sexInput.getText().toString();
        if(name.length() > 0 && surname.length() > 0 && middlename.length() > 0 && sex.length() > 0 && date.length() > 0) {
            proceedButton.setEnabled(true);
            proceedButton.setBackgroundColor(getResources().getColor(R.color.activeButton));
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {}

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sexInput.setText(adapterView.getItemAtPosition(i).toString());
        Log.d("SEX", sexInput.getText().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @Override
    public void onClick(View view) {
        PatientProfile patient = new PatientProfile(name, surname, middlename, date, sex);
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.PROFILE_ID_COLUMN, patient.ID);
        cv.put(DbHelper.PROFILE_NAME_COLUMN, patient.Name);
        cv.put(DbHelper.PROFILE_DATE_COLUMN, patient.Date);
        cv.put(DbHelper.PROFILE_MIDDLENAME_COLUMN, patient.Middlename);
        cv.put(DbHelper.PROFILE_SURNAME_COLUMN, patient.Surname);
        cv.put(DbHelper.PROFILE_SEX_COLUMN, patient.Sex);
        db.insert(DbHelper.PATIENT_PROFILES_TABLE, null, cv);
        db.close();
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("PROFILE_CREATED", true);
        editor.apply();
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
        finish();
    }
}