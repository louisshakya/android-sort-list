package com.louis.androidlistapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Validator {

    //Adds 2 integers (for dummy test)
    public int add(int x, int y) {
        return x + y;
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
