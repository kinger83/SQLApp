package com.example.sqlapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sqlapp.data.DatabaseHelper;
import com.example.sqlapp.databinding.ActivityShowBinding;
import com.example.sqlapp.model.ItemModel;

import java.util.ArrayList;

public class showActivity extends AppCompatActivity {
    DatabaseHelper db;
    ActivityShowBinding binding;
    ArrayList<ItemModel> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowBinding.inflate(getLayoutInflater());
        db = new DatabaseHelper(this);
        View view = binding.getRoot();
        setContentView(view);

        itemList = db.getAllItems();
       Log.d("ITEMS:", String.valueOf(itemList.size()));
        ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), itemList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        RecyclerView rv = view.findViewById(R.id.itemRecyclerView);
        rv.setAdapter(itemAdapter);
        rv.setLayoutManager(linearLayoutManager);

        binding.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), createActivity.class);
                startActivity(intent);
            }
        });
    }
}