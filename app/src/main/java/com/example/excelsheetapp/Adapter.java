package com.example.excelsheetapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater layoutInflater;
    List<String> year,y,w,r,l,k;

    public Adapter(Context context, List<String> year,List<String>y,List<String>w,List<String>r,List<String>l,List<String>k){
        this.layoutInflater=LayoutInflater.from(context);
        this.year=year;
        this.y=y;
        this.w=w;
        this.r=r;
        this.l=l;
        this.k=k;



    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.custom_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    String years=year.get(position);
    String ys=y.get(position);
    String ws=w.get(position);
    String rs=r.get(position);
    String ls=l.get(position);
    String ks=k.get(position);

    holder.yr.setText(years);
    holder.num1.setText(ys);
    holder.num2.setText(ws);
    holder.num3.setText(rs);
    holder.num4.setText(ls);
    holder.num5.setText(ks);
    }

    @Override
    public int getItemCount() {
        return year.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView yr,num1,num2,num3,num4,num5;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            yr=itemView.findViewById(R.id.yrs);
            num1=itemView.findViewById(R.id.num1);
            num2=itemView.findViewById(R.id.num2);
            num3=itemView.findViewById(R.id.num3);
            num4=itemView.findViewById(R.id.num4);
            num5=itemView.findViewById(R.id.num5);


        }
    }
}
