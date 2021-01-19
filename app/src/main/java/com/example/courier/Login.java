package com.example.courier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText phonenumber;
    EditText password;
    Button btn_login;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phonenumber = (EditText) findViewById(R.id.phonenumber);
        password = (EditText)findViewById(R.id.password);
        btn_login = (Button)findViewById(R.id.btn_login);

         btn_login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if(TextUtils.isEmpty(phonenumber.getText().toString()) ){
                     Toast.makeText(Login.this, "Enter No ....", Toast.LENGTH_SHORT).show();
                 }
                 else if(phonenumber.getText().toString().replace("+91","").length()!=10){
                     Toast.makeText(Login.this, "Enter Correct No ...", Toast.LENGTH_SHORT).show();
                 }else if( TextUtils.isEmpty((password.getText())))
                 {
                     Toast.makeText(Login.this, "Provide a password", Toast.LENGTH_SHORT).show();
                 }
                 else {

                     Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phone").equalTo(phonenumber.getText().toString().trim());
                     query.addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(DataSnapshot dataSnapshot) {
                             if (dataSnapshot.exists()) {
                                 // dataSnapshot is the "issue" node with all children with id 0

                                 for (DataSnapshot user : dataSnapshot.getChildren()) {
                                     // do something with the individual "issues"

                                     Users usersBean = user.getValue(Users.class);

                                     if (usersBean.getPassword().equals(password.getText())) {
                                         Intent intent = new Intent(Login.this, HomePage.class);
                                         startActivity(intent);
                                     } else {
                                         Toast.makeText(Login.this, "Password is wrong", Toast.LENGTH_LONG).show();
                                     }
                                 }
                             } else {
                                 Toast.makeText(Login.this, "User not found", Toast.LENGTH_LONG).show();
                             }
                         }

                         @Override
                         public void onCancelled(DatabaseError databaseError) {

                         }
                     });



//                     mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//                         @Override
//                         public void onDataChange(DataSnapshot dataSnapshot) {
//                             for(DataSnapshot data: dataSnapshot.getChildren()){
//                                 if (data.child("phone").exists() && data.child("password").exists() ) {
//                                     Intent intent = new Intent(Login.this,HomePage.class);
//                                     startActivity(intent);
//                                     Toast.makeText(Login.this, "Successful", Toast.LENGTH_SHORT).show();
//
//                                 } else {
//                                     break;
//
//                                 }
//                             }
//                         }
//
//                         @Override
//                         public void onCancelled(@NonNull DatabaseError error) {
//
//                         }
//
//
//                     });

                 }
             }
         });

    }




    public void fun_register(View view) {

        startActivity(new Intent(Login.this,Register.class));

    }
}