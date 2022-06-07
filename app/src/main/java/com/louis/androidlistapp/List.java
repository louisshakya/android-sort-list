package com.louis.androidlistapp;

public class List {
    private String name, listId, id;

    public List(String name, String listId, String id) {
        this.name = name;
        this.listId = listId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "List{" +
                "name='" + name + '\'' +
                ", listId='" + listId + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
