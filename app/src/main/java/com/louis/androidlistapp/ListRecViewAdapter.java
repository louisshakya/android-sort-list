package com.louis.androidlistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.louis.androidlistapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class ListRecViewAdapter extends RecyclerView.Adapter<ListRecViewAdapter.ViewHolder> {

    private ArrayList<List> lists = new ArrayList<>();

    public ListRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(lists.get(position).getName());
        holder.listId.setText(lists.get(position).getListId());
        holder.id.setText(lists.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setLists(ArrayList<List> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name, listId, id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameId);
            listId = itemView.findViewById(R.id.listIdId);
            id = itemView.findViewById(R.id.idID);
        }
    }
}
