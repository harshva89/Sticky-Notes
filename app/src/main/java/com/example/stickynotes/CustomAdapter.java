package com.example.stickynotes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList title,body,id;
    DBManager dbManager;

    CustomAdapter(Context context, ArrayList title, ArrayList body,  ArrayList id){
        this.title=title;
        this.body=body;
        this.id=id;
        this.context=context;
        dbManager = new DBManager(context);
    }

    public void update(ArrayList<String> title, ArrayList<String> body, ArrayList<String> id) {
        this.title=title;
        this.body=body;
        this.id=id;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView title_text, body_text;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            title_text = itemView.findViewById(R.id.titleText);
            body_text = itemView.findViewById(R.id.bodyText);
        }
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
        holder.title_text.setText(String.valueOf(title.get(position)));
        holder.body_text.setText(String.valueOf(body.get(position)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewAndDelete.class);
                intent.putExtra("title",title.get(position).toString());
                intent.putExtra("body",body.get(position).toString());
                intent.putExtra("id",id.get(position).toString());
                v.getContext().startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return title.size();
    }
}
