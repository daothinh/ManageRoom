package com.example.manageroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addBtn;

    DBHelper dbHelper;
    ArrayList<String> roomId, roomRentPrice, roomArea, roomAreaCode;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewRoom);
        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

            dbHelper = new DBHelper(MainActivity.this);
            roomId = new ArrayList<>();
            roomArea = new ArrayList<>();
            roomRentPrice = new ArrayList<>();
            roomAreaCode = new ArrayList<>();

            storeDataInArray();
            customAdapter = new CustomAdapter(MainActivity.this, roomId, roomArea, roomRentPrice, roomAreaCode);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeDataInArray(){
        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else{
            while (cursor.moveToNext()){
                roomId.add(cursor.getString(0));
                roomArea.add(cursor.getString(1));
                roomRentPrice.add(cursor.getString(2));
                roomAreaCode.add(cursor.getString(5));
            }
        }
    }
}