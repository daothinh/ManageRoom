package com.example.manageroom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList roomId, roomRentPrice, roomArea, roomAreaCode;

    Activity activity;

    CustomAdapter(Activity activity, Context context, ArrayList roomID, ArrayList roomArea,
                  ArrayList roomRentPrice, ArrayList roomAreaCode){

        this.activity = activity;
        this.context = context;
        this.roomId = roomID;
        this.roomArea = roomArea;
        this.roomRentPrice = roomRentPrice;
        this.roomAreaCode = roomAreaCode;
    }


    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        holder.roomId.setText(String.valueOf(roomId.get(position)));
        holder.roomArea.setText(String.valueOf(roomArea.get(position)));
        holder.roomRentPrice.setText(String.valueOf(roomRentPrice.get(position)));
        holder.roomAreaCode.setText(String.valueOf(roomAreaCode.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("roomId", String.valueOf(roomId.get(holder.getAdapterPosition()))); //gán dữ liệu key-value để truyền sang Activity khác
                intent.putExtra("roomArea", String.valueOf(roomArea.get(holder.getAdapterPosition()))); //gán dữ liệu key-value để truyền sang Activity khác
                intent.putExtra("roomRentPrice", String.valueOf(roomRentPrice.get(holder.getAdapterPosition()))); //gán dữ liệu key-value để truyền sang Activity khác
                intent.putExtra("roomAreaCode", String.valueOf(roomAreaCode.get(holder.getAdapterPosition()))); //gán dữ liệu key-value để truyền sang Activity khác
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView roomId, roomArea, roomRentPrice, roomAreaCode;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roomId = itemView.findViewById(R.id.room_id);
            roomArea = itemView.findViewById(R.id.room_area);
            roomRentPrice = itemView.findViewById(R.id.room_rent_price);
            roomAreaCode = itemView.findViewById(R.id.room_area_code);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
