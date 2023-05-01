package com.example.manageroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText area_update, rent_price_update, area_code_update;
    TextView room_id_update;
    Button update_btn;

    String id, area, rentPrice, areaCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        room_id_update = findViewById(R.id.room_id_update);
        area_update = findViewById(R.id.area_update);
        rent_price_update = findViewById(R.id.rent_price_update);
        area_code_update = findViewById(R.id.area_code_update);

        update_btn = findViewById(R.id.update_btn);

        getAndSetIntentData();

        update_btn.setOnClickListener(v -> {
            DBHelper dbHelper = new DBHelper(UpdateActivity.this);
            getDataUpdate();
            dbHelper.updateData(id, area, rentPrice, areaCode);
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

    private void getDataUpdate(){
        id = room_id_update.getText().toString().trim();
        area = area_update.getText().toString().trim();
        rentPrice = rent_price_update.getText().toString().trim();
        areaCode = area_code_update.getText().toString().trim();
    }
}