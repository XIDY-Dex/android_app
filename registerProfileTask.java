package com.example.worldskills;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class registerProfileTask {
    public static void registerProfile(PatientProfile patient, String token) {
        try {
            URL Url = new URL("https://medic.madskill.ru/api/createProfile");
            HttpURLConnection connection = (HttpURLConnection) Url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestMethod("POST");

            JSONObject object = new JSONObject();
            object.put("id", patient.getID());
            object.put("firstname", patient.getName());
            object.put("lastname", patient.getSurname());
            object.put("middlename", patient.getMiddlename());
            object.put("bith", patient.getDate());
            object.put("pol", patient.getSex());
            object.put("image", null);
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(object.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.close();

            Log.d("PROFILE_RESPONSE_CODE", String.valueOf(connection.getResponseCode()));

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateProfile(PatientProfile patient, String token) {
        try {
            URL url = new URL("https://medic.madskill.ru/api/updateProfile");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestMethod("PUT");
            JSONObject object = new JSONObject();
            object.put("id", patient.getID());
            object.put("firstname", patient.getName());
            object.put("lastname", patient.getSurname());
            object.put("middlename", patient.getMiddlename());
            object.put("bith", patient.getDate());
            object.put("pol", patient.getSex());
            object.put("image", null);
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(object.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.close();

            Log.d("PROFILE_RESPONSE_CODE", String.valueOf(connection.getResponseCode()));

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
