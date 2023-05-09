package com.example.sqlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sqlapp.data.DatabaseHelper;
import com.example.sqlapp.databinding.ActivityCreateBinding;
import com.example.sqlapp.model.ItemModel;

public class createActivity extends AppCompatActivity {
    ActivityCreateBinding binding;
    DatabaseHelper db;
    String name, phone, description, date, location, radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        db = new DatabaseHelper(this);

        binding.lostFoundRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.lostRadioButton){
                    radio = "lost";
                }
                if(checkedId == R.id.foundRadioButton){
                    radio = "found";
                }
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputs();

                if(!validateInput()){
                    return;
                }

                updateDataBase();
            }
        });
    }

    private void updateDataBase(){
        ItemModel item = new ItemModel(name, phone, description, date, location, radio);
        long result = db.insertItem(item);
        if(result > 0){
            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void setInputs(){
        name = binding.nameEditText.getText().toString();
        phone = binding.phoneEditText.getText().toString();
        description = binding.descriptioneditText.getText().toString();
        date = binding.dateEditText.getText().toString();
        location = binding.locationEditText.getText().toString();
    }
    private Boolean validateInput(){
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please enter you phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(description)){
            Toast.makeText(this, "Please describe the item", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(date)){
            Toast.makeText(this, "Please enter the date you lost/found the item", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(location)){
            Toast.makeText(this, "Please enter the location you lost/found the item", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(radio)){
            Toast.makeText(this, "Please click if item is lost or found", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}