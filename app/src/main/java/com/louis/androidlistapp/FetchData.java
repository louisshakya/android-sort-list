package com.louis.androidlistapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FetchData {
    private static final String URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    private final Context context;

    private ArrayList<List> list;

    public FetchData(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(ArrayList<List> lists);
    }

    public void getList(VolleyResponseListener volleyResponseListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                list = new ArrayList<>();
                try {
                    //Creating JSON array
                    JSONArray mainArray = new JSONArray(response);
                    for (int i = 0; i < mainArray.length(); i++) {
                        //Creating JSON object
                        JSONObject object = mainArray.getJSONObject(i);

                        //Parsing data
                        String id = object.getString("id");
                        String listId = object.getString("listId");
                        String name = object.getString("name");
                        list.add(new List(id,listId,name));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("An error occurred while parsing data");
                }
                volleyResponseListener.onResponse(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("An error occurred while fetching data");
                volleyResponseListener.onError("An error occurred while fetching data");
            }
        });

        RequestSingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

}
