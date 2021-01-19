package com.example.courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserInfo extends AppCompatActivity {
   EditText txtname,txtpassword,txtphone;
   Button register;
   DatabaseReference reff;
   Users users;
    private String number;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        txtname = (EditText)findViewById(R.id.txtname);

        txtpassword = (EditText)findViewById(R.id.txtpassword);
         register = (Button)findViewById(R.id.register);
       number = getIntent().getStringExtra("number");
         users = new Users();
         reff  =FirebaseDatabase.getInstance().getReference().child("Users");
          register.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  users.setName(txtname.getText().toString().trim());
                  users.setPassword(txtpassword.getText().toString().trim());
                  users.setPhone(number);
                  reff.push().setValue(users);
                  Toast.makeText(UserInfo.this,"Successful",Toast.LENGTH_LONG);
                  startActivity(new Intent(UserInfo.this,Login.class));
              }
          });

    }


}