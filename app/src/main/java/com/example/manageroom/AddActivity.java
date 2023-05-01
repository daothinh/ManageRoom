package com.example.manageroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText areaInput, rentPriceInput, electricityBillInput, waterBillInput, areaCodeInput;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        areaInput = findViewById(R.id.area_input);
        rentPriceInput = findViewById(R.id.rent_price_input);
        electricityBillInput = findViewById(R.id.electricity_bill_input);
        waterBillInput = findViewById(R.id.water_bill_input);
        areaCodeInput = findViewById(R.id.area_code_input);
        addBtn = (Button) findViewById(R.id.add_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(AddActivity.this);

                dbHelper.addRoom(Integer.parseInt(areaInput.getText().toString().trim()),
                        Integer.parseInt(rentPriceInput.getText().toString().trim()),
                        Integer.parseInt(electricityBillInput.getText().toString().trim()),
                        Integer.parseInt(waterBillInput.getText().toString().trim()),
                        Integer.parseInt(areaCodeInput.getText().toString().trim()));
            }
        });
    }
}