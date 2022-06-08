package com.louis.androidlistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.louis.androidlistapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity{


    private ActivityMainBinding binding;
    private ArrayList<List> newList =new ArrayList<>();
    private boolean isSortByListIDPressed, isSortByNamePressed, isSortByBothPressed = false;

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
        setOnClickListeners();
        updateRecyclerView(newList);
        fetchData();

    }

    public void setOnClickListeners() {
        binding.listIdButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSortByNamePressed = false;
                isSortByBothPressed = false;
                binding.nameButtonId.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                binding.bothButtonId.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                ArrayList<List> tempList = new ArrayList<>(getNewList());
                if (isSortByListIDPressed) {
                    isSortByListIDPressed = false;
                    updateRecyclerView(tempList);
                    binding.listIdButtonId.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                } else {
                    isSortByListIDPressed = true;
                    sortByListId(tempList);
                    updateRecyclerView(tempList);
                    binding.listIdButtonId.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.purple_200));
                }
            }
        });
        binding.nameButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSortByListIDPressed = false;
                isSortByBothPressed = false;
                binding.listIdButtonId.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                binding.bothButtonId.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                ArrayList<List> tempList = new ArrayList<>(getNewList());
                if (isSortByNamePressed) {
                    isSortByNamePressed = false;
                    updateRecyclerView(tempList);
                    binding.nameButtonId.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                } else {
                    isSortByNamePressed = true;
                    sortByName(tempList);
                    updateRecyclerView(tempList);
                    binding.nameButtonId.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.purple_200));
                }
            }
        });
        binding.bothButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSortByNamePressed = false;
                isSortByListIDPressed = false;
                binding.nameButtonId.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                binding.listIdButtonId.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                ArrayList<List> tempList = new ArrayList<>(getNewList());
                if (isSortByBothPressed) {
                    isSortByBothPressed = false;
                    updateRecyclerView(tempList);
                    binding.bothButtonId.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                } else {
                    isSortByBothPressed = true;
                    ArrayList<List> sortedList = sortByBoth(tempList);
                    updateRecyclerView(sortedList);
                    binding.bothButtonId.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.purple_200));
                }
            }
        });
    }

    public void fetchData(){
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

    //Sorts the list by list id in ascending order
    public void sortByListId(ArrayList<List> list) {
        Collections.sort(list, new Comparator<List>() {
            @Override
            public int compare(List o1, List o2) {
                return o1.getListId().compareToIgnoreCase(o2.getListId());
            }
        });
    }

    //Sorts the list by name in ascending order
    public void sortByName(ArrayList<List> list) {
        Collections.sort(list, new Comparator<List>() {
            @Override
            public int compare(List o1, List o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

    //Sorts the list first by list id and then by name in ascending order
    public ArrayList<List> sortByBoth(ArrayList<List> list) {
        sortByListId(list);
        int listIDValue = Integer.parseInt(list.get(list.size() - 1).getListId());
        ArrayList<List> newSortedArray = new ArrayList<>();
        for (int i = 0; i <= listIDValue; i++) {
            ArrayList<List> tempArray = new ArrayList<>();
            for (List l: list) {
                if (Integer.parseInt(l.getListId()) == i) {
                    tempArray.add(l);
                }
            }
            sortByName(tempArray);
            newSortedArray.addAll(tempArray);
        }
        return newSortedArray;
    }
}