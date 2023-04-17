package com.example.worldskills;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class getCatalogTask {
    static String response = "";
    static ArrayList<CatalogItem> catalog = new ArrayList<>();

    public static String getCatalogItems() {
        try {
            URL url = new URL("https://medic.madskill.ru/api/catalog");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null) {
                response = inputLine;
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
    public static ArrayList<CatalogItem> serializeItems(String response) {
        try {
            JSONArray objects = new JSONArray(response);
            for(int i = 0; i < objects.length(); i++) {
                JSONObject object = objects.getJSONObject(i);
                CatalogItem item = new CatalogItem(object.getInt("id"), object.getString("name"), object.getString("description"), Integer.parseInt(object.getString("price")), object.getString("category"), object.getString("time_result"), object.getString("preparation"), object.getString("bio"));
                catalog.add(item);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return catalog;
    }
}
