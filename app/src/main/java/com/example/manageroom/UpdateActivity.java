package com.example.manageroom;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText area_update, rent_price_update, area_code_update;
    TextView room_id_update;
    Button update_btn, delete_btn;

    String id, area, rentPrice, areaCode;

    DBHelper dbHelper = new DBHelper(UpdateActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        room_id_update = findViewById(R.id.room_id_update);
        area_update = findViewById(R.id.area_update);
        rent_price_update = findViewById(R.id.rent_price_update);
        area_code_update = findViewById(R.id.area_code_update);

        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);

        getAndSetIntentData();


        //Set actionbar title
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(id);
        }

        update_btn.setOnClickListener(v -> {
            getDataAfterUpdate();
            dbHelper.updateData(id, area, rentPrice, areaCode);
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    private void getAndSetIntentData() {
        if (getIntent().hasExtra("roomId") && getIntent().hasExtra("roomArea") && getIntent().hasExtra("roomRentPrice") && getIntent().hasExtra("roomAreaCode")) {
            id = getIntent().getStringExtra("roomId");
            area = getIntent().getStringExtra("roomArea");
            rentPrice = getIntent().getStringExtra("roomRentPrice");
            areaCode = getIntent().getStringExtra("roomAreaCode");

            //Set data vào EditText
            room_id_update.setText(id);
            area_update.setText(area);
            rent_price_update.setText(rentPrice);
            area_code_update.setText(areaCode);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void getDataAfterUpdate(){
        id = room_id_update.getText().toString().trim();
        area = area_update.getText().toString().trim();
        rentPrice = rent_price_update.getText().toString().trim();
        areaCode = area_code_update.getText().toString().trim();
    }

    private void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Room ID: " + id + " ?");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteData(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }
}