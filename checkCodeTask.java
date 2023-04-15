package com.example.worldskills;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class checkCodeTask {
    public static final String TAG="checkCodeTask";

    public static String Authorize(String email, String code) {
        StringBuffer response = null;
        try {
            URL url = new URL("https://medic.madskill.ru/api/signin");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("email", email);
            connection.setRequestProperty("code", code);

            int responseCode = connection.getResponseCode();
            Log.d(TAG, "Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    public static String handleResult(String result) {
        String result2 = "";
        try {
            JSONObject object = new JSONObject(result);
            if(object.has("token")) {
                result2 = object.getString("token");
            }
            else {
                result2 = "Error";
            }
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        return result2;
    }
}
