package com.example.worldskills;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetNewsTask {

    public static String getObjects() {
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL("https://medic.madskill.ru/api/news");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    public static ArrayList<NewsModel> serializeObjects(String response) {
        ArrayList<NewsModel> news = new ArrayList<>();
        try {
            JSONArray objects = new JSONArray(response);
            for(int i = 0; i < objects.length(); i++) {
                JSONObject object = objects.getJSONObject(i);
                Drawable image;
                try {
                    URL url = new URL(object.getString("image"));
                    InputStream stream =  url.openStream();
                    image = Drawable.createFromStream(stream, "");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                NewsModel model = new NewsModel(object.getInt("id"), object.getString("name"), object.getString("description"), object.getInt("price"), image);
                news.add(model);
            }
        } catch (JSONException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return news;
    }
}
