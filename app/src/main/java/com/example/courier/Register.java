package com.example.courier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class Register extends AppCompatActivity {
    private CountryCodePicker countryCodePicker;
    private EditText number;
    private Button next;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        countryCodePicker = findViewById(R.id.ccp);
        number = findViewById(R.id.editText_carrierNumber);
        next = findViewById(R.id.next);
        countryCodePicker.registerCarrierNumberEditText(number);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(number.getText().toString())){
                    Toast.makeText(Register.this, "Enter No ....", Toast.LENGTH_SHORT).show();
                }
                else if(number.getText().toString().replace(" ","").length()!=10){
                    Toast.makeText(Register.this, "Enter Correct No ...", Toast.LENGTH_SHORT).show();
                }
                else {

                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot data: dataSnapshot.getChildren()){
                                if (data.child("phone").exists() )
                                {
                                    Toast.makeText(Register.this, "This Phone Number Already Register", Toast.LENGTH_SHORT).show();
                                                         break;
                                }
                                else
                                    {
                                    Intent intent = new Intent(Register.this,VerificationActivity.class);
                                    intent.putExtra("number",countryCodePicker.getFullNumberWithPlus().replace(" ",""));
                                    startActivity(intent);
                                    }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }


                    });



                }
            }
        });



    }
}