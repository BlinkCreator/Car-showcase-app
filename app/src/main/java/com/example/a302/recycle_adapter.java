package com.example.a302;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycle_adapter extends RecyclerView.Adapter<recycle_adapter.MyViewHolder> {

    public ArrayList<Car> carList;
    private OnNoteListener mOnNoteListener;

    public recycle_adapter(ArrayList<Car> carList, OnNoteListener onNoteListener) {
        this.carList = carList;
        this.mOnNoteListener = onNoteListener;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { //we are able to override the clicking listener for each element in the recycler and return its position in the list
        private TextView nameText;
        private TextView yearText;
        private ImageView image;
        OnNoteListener onNoteListener;
        public MyViewHolder(final View view, OnNoteListener onNoteListener) {
            super(view);
            nameText = view.findViewById(R.id.ListItemName);
            yearText = view.findViewById(R.id.ListItemYear);
            image = view.findViewById(R.id.ListItemImage);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

    @NonNull
    @Override
    public recycle_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull recycle_adapter.MyViewHolder holder, int position) { //this is where each of the elements within the recycler are populated with the values from the cars array
        String name = carList.get(position).getName();
        holder.nameText.setText(name);

        String year = carList.get(position).getYear();
        holder.yearText.setText(year);

        int image = carList.get(position).getImage(0);
        holder.image.setBackgroundResource(image);
    }


    @Override
    public int getItemCount() {
        return carList.size();
    }
}
