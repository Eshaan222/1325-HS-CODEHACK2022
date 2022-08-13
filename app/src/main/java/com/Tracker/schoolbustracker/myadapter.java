package com.Tracker.schoolbustracker;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    ArrayList<model> datalist;

    public myadapter(ArrayList<model> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.t1.setText(datalist.get(position).getMessage());
            if(datalist.get(position).getDir().equals("right")) {
                holder.constraint.setBackgroundColor(Color.parseColor("#e3e3e3"));
                holder.linearLayout.setGravity(Gravity.LEFT);
                holder.t2.setText("Driver");
            }else{
                holder.constraint.setBackgroundColor(Color.parseColor("#5FF367"));
                holder.linearLayout.setGravity(Gravity.RIGHT);
                holder.t2.setText("You");
            }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView t1,t2;
        ConstraintLayout constraint;
        LinearLayout linearLayout,layout2;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.t1);
            t2=itemView.findViewById(R.id.t2);
            constraint = itemView.findViewById(R.id.chat_const);
            linearLayout = itemView.findViewById(R.id.linear_layout_chat);
        }
    }

}
