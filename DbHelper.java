package com.example.worldskills;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="app_db.sqlite3";
    public static final String PATIENT_PROFILES_TABLE="profiles";
    public static final int SCHEMA_VERSION = 1;
    public static final String PROFILE_ID_COLUMN = "_id";
    public static final String PROFILE_NAME_COLUMN = "name";
    public static final String PROFILE_SURNAME_COLUMN = "surname";
    public static final String PROFILE_MIDDLENAME_COLUMN = "middlename";
    public static final String PROFILE_SEX_COLUMN = "sex";
    public static final String PROFILE_DATE_COLUMN = "date";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + PATIENT_PROFILES_TABLE + " (" + PROFILE_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " + PROFILE_NAME_COLUMN + " TEXT, " + PROFILE_SURNAME_COLUMN + " TEXT, " + PROFILE_MIDDLENAME_COLUMN + " TEXT, " + PROFILE_DATE_COLUMN + " TEXT, " + PROFILE_SEX_COLUMN + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PATIENT_PROFILES_TABLE);
        onCreate(sqLiteDatabase);
    }
}
