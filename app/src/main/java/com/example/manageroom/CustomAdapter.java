package com.example.manageroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList roomId, roomRentPrice, roomArea, roomAreaCode;

    CustomAdapter(Context context, ArrayList roomID, ArrayList roomArea,
                  ArrayList roomRentPrice, ArrayList roomAreaCode){
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
    }

    @Override
    public int getItemCount() {
        return roomId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView roomId, roomArea, roomRentPrice, roomAreaCode;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roomId = itemView.findViewById(R.id.room_id);
            roomArea = itemView.findViewById(R.id.room_area);
            roomRentPrice = itemView.findViewById(R.id.room_rent_price);
            roomAreaCode = itemView.findViewById(R.id.room_area_code);
        }
    }
}
