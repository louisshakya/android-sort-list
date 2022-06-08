package com.louis.androidlistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.louis.androidlistapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private ArrayList<List> newList =new ArrayList<>();

    public ArrayList<List> getNewList() {
        return newList;
    }

    public void setNewList(ArrayList<List> newList) {
        this.newList = newList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        updateRecyclerView(newList);
        FetchData data = new FetchData(this);
        data.getList(new FetchData.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, "An error occurred while fetching data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(ArrayList<List> lists) {
                removeEmptyNames(lists);
                setNewList(lists);
                updateRecyclerView(lists);
            }
        });
    }

    //Removes null values for names
    public void removeEmptyNames(ArrayList<List> name) {
        Iterator<List> itr = name.iterator();
        while (itr.hasNext()) {
            List l = itr.next();
            if (l.getName().equals("") || l.getName().equals("null")) {
                itr.remove();
            }
        }
    }

    //Updates RecyclerView to display the list
    public void updateRecyclerView(ArrayList<List> list) {
        ListRecViewAdapter adapter = new ListRecViewAdapter();
        adapter.setLists(list);
        binding.recyclerViewId.setAdapter(adapter);
        binding.recyclerViewId.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}