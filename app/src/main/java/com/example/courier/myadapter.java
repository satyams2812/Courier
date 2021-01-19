package com.example.courier;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<UserHelperClass,myadapter.myviewholder> {
    public myadapter(@NonNull FirebaseRecyclerOptions<UserHelperClass> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull UserHelperClass model) {
        holder.t1.setText(model.getMenu());
        holder.t2.setText(model.getPickupaddress());
        holder.t3.setText(model.getDeliveradddress());
        holder.t4.setText(model.getPickname());
        holder.t5.setText(model.getPickphone());
        holder.t6.setText(model.getDeliverusername());
        holder.t7.setText(model.getDeliverphoneno());
        holder.t8.setText(model.getMapdeliveradddress());
        holder.t9.setText(model.getMappickupaddress());
        holder.t10.setText(model.getDate());
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return  new myviewholder(view);
    }
    class myviewholder extends RecyclerView.ViewHolder{
        TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.menutext);
            t2=(TextView)itemView.findViewById(R.id.pickuptext);
            t3=(TextView)itemView.findViewById(R.id.delivertext);
            t4=(TextView)itemView.findViewById(R.id.pickupuser);
            t5=(TextView)itemView.findViewById(R.id.pickno);
            t6=(TextView)itemView.findViewById(R.id.deliveruser);
            t7=(TextView)itemView.findViewById(R.id.deliverno);
            t8=(TextView)itemView.findViewById(R.id.delivermaptext);
            t9=(TextView)itemView.findViewById(R.id.pickupmaptext);
            t10=(TextView)itemView.findViewById(R.id.date);
        }
    }
}
