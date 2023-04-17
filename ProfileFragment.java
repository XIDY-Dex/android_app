package com.example.worldskills;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class ProfileFragment extends Fragment implements TextWatcher, View.OnClickListener {

    TextInputEditText nameInput, surnameInput, middlenameInput, dateInput, sexInput;
    MaterialButton proceedButton;
    String name, surname, middlename, date, sex;
    DbHelper dbHelper;
    SQLiteDatabase db;
    PatientProfile patient = new PatientProfile();
    Boolean profileCreated;
    SharedPreferences settings;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameInput = view.findViewById(R.id.nameInputEditText);
        surnameInput = view.findViewById(R.id.surnameInputEditText);
        middlenameInput = view.findViewById(R.id.middlenameInputEditText);
        dateInput = view.findViewById(R.id.datenputEditText);
        sexInput = view.findViewById(R.id.sexInputEditText);
        proceedButton = view.findViewById(R.id.proceedButton);

        nameInput.addTextChangedListener(this);
        surnameInput.addTextChangedListener(this);
        middlenameInput.addTextChangedListener(this);
        dateInput.addTextChangedListener(this);
        sexInput.addTextChangedListener(this);

        dbHelper = new DbHelper(getContext());
        db = dbHelper.getWritableDatabase();
        proceedButton.setOnClickListener(this);
        Cursor query = db.rawQuery("SELECT * FROM " + DbHelper.PATIENT_PROFILES_TABLE, null);
        if(query.moveToNext()) {
            nameInput.setText(query.getString(1));
            surnameInput.setText(query.getString(2));
            middlenameInput.setText(query.getString(3));
            dateInput.setText(query.getString(4));
            sexInput.setText(query.getString(5));

            patient.setName(query.getString(1));
            patient.setID(query.getInt(0));
            patient.setSurname(query.getString(2));
            patient.setMiddlename(query.getString(3));
            patient.setDate(query.getString(4));
            patient.setSex(query.getString(5));

            proceedButton.setEnabled(true);
            proceedButton.setBackgroundColor(getResources().getColor(R.color.activeButton));
            proceedButton.setText("Сохранить");
            profileCreated = false;


        }
        else {
            profileCreated = true;
        }
        settings = getActivity().getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        name = nameInput.getText().toString();
        surname = surnameInput.getText().toString();
        middlename = middlenameInput.getText().toString();
        date = dateInput.getText().toString();
        sex = sexInput.getText().toString();
        patient.setName(name);
        patient.setSurname(surname);
        patient.setSex(sex);
        patient.setDate(date);
        patient.setMiddlename(middlename);
        if(name.length() > 0 && surname.length() > 0 && middlename.length() > 0 && date.length() > 0 && sex.length() > 0) {
            proceedButton.setEnabled(true);
            proceedButton.setBackgroundColor(getResources().getColor(R.color.activeButton));
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {
        Thread t;
        ContentValues cv = new ContentValues();
        if (profileCreated) {
            cv.put(DbHelper.PROFILE_NAME_COLUMN, patient.Name);
            cv.put(DbHelper.PROFILE_DATE_COLUMN, patient.Date);
            cv.put(DbHelper.PROFILE_MIDDLENAME_COLUMN, patient.Middlename);
            cv.put(DbHelper.PROFILE_SURNAME_COLUMN, patient.Surname);
            cv.put(DbHelper.PROFILE_SEX_COLUMN, patient.Sex);
            db.insert(DbHelper.PATIENT_PROFILES_TABLE, null, cv);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("PROFILE_CREATED", true);
            editor.apply();
            t = new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    registerProfileTask.registerProfile(patient, settings.getString("AUTH_TOKEN", ""));
                }
            };
        } else {
            cv.put(DbHelper.PROFILE_NAME_COLUMN, name);
            cv.put(DbHelper.PROFILE_DATE_COLUMN, date);
            cv.put(DbHelper.PROFILE_MIDDLENAME_COLUMN, middlename);
            cv.put(DbHelper.PROFILE_SURNAME_COLUMN, surname);
            cv.put(DbHelper.PROFILE_SEX_COLUMN, sex);
            Log.d("PATIENT_ID", String.valueOf(patient.ID));
            int result = db.update(DbHelper.PATIENT_PROFILES_TABLE, cv, DbHelper.PROFILE_ID_COLUMN + "=?", new String[]{String.valueOf(patient.ID)});
            Log.d("DB_RESULT", String.valueOf(result));
            t = new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    registerProfileTask.updateProfile(patient, settings.getString("AUTH_TOKEN", ""));
                }
            };
        }
        t.start();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }
}