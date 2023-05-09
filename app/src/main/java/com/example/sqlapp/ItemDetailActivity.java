package com.example.sqlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.sqlapp.databinding.ActivityItemDetailBinding;
import com.example.sqlapp.model.ItemModel;

public class ItemDetailActivity extends AppCompatActivity {
    ItemModel item;
    ActivityItemDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        item = (ItemModel) getIntent().getSerializableExtra("item");

        //TODO display all detils to screen

        //TODO create delete button
    }
}