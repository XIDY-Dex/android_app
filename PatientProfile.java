package com.example.worldskills;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class PatientProfile implements Serializable {
    public static int ID = 0;
    protected String Name;
    protected String Surname;
    protected String Middlename;
    protected String Date;
    protected String Sex;

    public PatientProfile(String name, String surname, String middlename, String date, String sex) {
        this.Date = date;
        this.Surname = surname;
        this.Middlename = middlename;
        this.Sex = sex;
        this.Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMiddlename() {
        return Middlename;
    }

    public void setMiddlename(String middlename) {
        Middlename = middlename;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public static void incrementIdCounter() {
        ID++;
    }
}
