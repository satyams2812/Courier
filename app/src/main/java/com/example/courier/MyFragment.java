package com.example.courier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
public class MyFragment extends DialogFragment{
    private TextView ordertext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v= inflater.inflate(R.layout.my_fragment,container,false);
        ordertext=(TextView) v.findViewById(R.id.ordertext);
        Bundle b1= getArguments();
        String order=b1.getString("order");
        ordertext.setText(order);
        return  v;
    }
}