package com.example.sqlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.sqlapp.data.DatabaseHelper;
import com.example.sqlapp.databinding.ActivityItemDetailBinding;
import com.example.sqlapp.model.ItemModel;

public class ItemDetailActivity extends AppCompatActivity {
    ItemModel item;
    ActivityItemDetailBinding binding;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        dbHelper = new DatabaseHelper(this);
        item = (ItemModel) getIntent().getSerializableExtra("item");

        binding.itemisFoundText.setText(item.getIsLost().toUpperCase());
        binding.itemDescText.setText(item.getDescription());
        binding.itemDateText.setText(item.getDate());
        binding.itemLocationText.setText(item.getLocation());
        binding.itemNameText.setText(item.getName());
        binding.itemPhoneText.setText(item.getPhone());

        binding.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemID = String.valueOf(item.getId());

                dbHelper.deleteItem(itemID);

                Intent intent = new Intent(getApplicationContext(), showActivity.class);
                startActivity(intent);
            }
        });
    }
}