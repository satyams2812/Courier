package com.example.courier;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    private String details;
    private String username;
    private TextView text;
    private TextView text1;
    private String name;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
       View v= inflater.inflate(R.layout.dialog_fragment,container,false);
       text=(TextView) v.findViewById(R.id.text);
       text1=(TextView) v.findViewById(R.id.text1);
       Bundle b1= getArguments();
       String name=b1.getString("name");
       String phone=b1.getString("phoneno");
       text.setText(name);
       text1.setText(phone);
       return  v;
    }
}
