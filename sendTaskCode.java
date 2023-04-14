package com.example.worldskills;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class sendTaskCode {
    private static final String TAG = "SendCodeTask";

    public String sendCode(String email) {
        String result = "";
        try {
            URL url = new URL("http://example.com/sendCode");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("email", email);

            int responseCode = connection.getResponseCode();
            Log.d(TAG, "Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            result = response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void handleResult(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.has("message")) {
                String message = jsonObject.getString("message");
                // Действия в случае успеха
            } else if (jsonObject.has("errors")) {
                JSONArray errorsArray = jsonObject.getJSONArray("errors");
                // Действия в случае ошибки
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
