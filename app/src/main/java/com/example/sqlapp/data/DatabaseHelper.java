package com.example.sqlapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sqlapp.model.ItemModel;
import com.example.sqlapp.utils.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Create_User_Table = "CREATE TABLE " +Util.TABLE_NAME + "(" +
                Util.ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Util.NAME + " TEXT, " +
                Util.PHONE + " TEXT, " +
                Util.DESCRIPTION + " TEXT, " +
                Util.DATE + " TEXT, " +
                Util.LOCATION + " TEXT, " +
                Util.ISLOST + " TEXT)";
        db.execSQL(Create_User_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String Drop_Item_Table = "DROP TABLE IF EXISTS";
        db.execSQL(Drop_Item_Table, new String[]{Util.TABLE_NAME});
        onCreate(db);
    }

    public long insertItem(ItemModel item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NAME, item.getName());
        contentValues.put(Util.PHONE, item.getPhone());
        contentValues.put(Util.DESCRIPTION, item.getDescription());
        contentValues.put(Util.DATE, item.getDate());
        contentValues.put(Util.LOCATION, item.getLocation());
        contentValues.put(Util.ISLOST, item.getIsLost());

        long newRowID = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowID;
    }

    public ArrayList<ItemModel> getAllItems(){
        ArrayList<ItemModel> itemList = new ArrayList<ItemModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] toQuery = {Util.ITEM_ID, Util.NAME, Util.PHONE, Util.DESCRIPTION, Util.DATE, Util.LOCATION, Util.ISLOST};
        Cursor cursor = db.query(Util.TABLE_NAME, toQuery, null, null, null, null, null);

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Util.ITEM_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(Util.NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(Util.PHONE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(Util.DESCRIPTION));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(Util.DATE));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(Util.LOCATION));
            String isLost = cursor.getString(cursor.getColumnIndexOrThrow(Util.ISLOST));

            ItemModel item = new ItemModel(id, name, phone, description, date, location, isLost);
            itemList.add(item);
        }
        cursor.close();
        db.close();

        return itemList;
    }

    public void deleteItem(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, "item_id=?", new String[]{id} );
        db.close();
    }
}
