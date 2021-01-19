package com.example.courier;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomePage extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private Button finalize;
    private Button payment;
    private ImageView loginProfile;
    private ImageView overflow;
    private ImageView ordericon;
    private Button cash;
    String userphoneno;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;
    FragmentManager manager= getSupportFragmentManager();
    FragmentTransaction t1=manager.beginTransaction();
    final MyFragment myFragment=new MyFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        cash=(Button)findViewById(R.id.cash);
        Intent intent=getIntent();
        String menu= intent.getStringExtra("menu");
        String pickname=intent.getStringExtra("pickname");
        String pickphone=intent.getStringExtra("pickphone");
        String deliverusername=intent.getStringExtra("deliverusername");
        String deliverphoneno= intent.getStringExtra("deliverphoneno");
        String deliveraddress=intent.getStringExtra("deliveraddress");
        String pickupaddress=intent.getStringExtra("pickupaddress");
        String mapdeliveradddress=intent.getStringExtra("mapdeliveradddress");
        String mappickupaddress=intent.getStringExtra("mappickupaddress");
        String name=intent.getStringExtra("username");
        userphoneno=intent.getStringExtra("userphoneno");
        /*Intent intent1=new Intent(HomePage.this,ViewOrder.class);
        intent1.putExtra("userphoneno",userphoneno);
        setResult(RESULT_OK,intent1);
        finish();*/
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,Confirmation.class);
                startActivity(intent);
            }
        });
        finalize=(Button)findViewById(R.id.finalize);
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference().child("users");
        finalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar= Calendar.getInstance();
                simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date= simpleDateFormat.format(calendar.getTime());
                UserHelperClass helperClass= new UserHelperClass(menu,pickname,pickphone,deliverusername,deliverphoneno,pickupaddress,deliveraddress,mapdeliveradddress,mappickupaddress,name,userphoneno,Date);
                reference.child(userphoneno).child(menu).setValue(helperClass);
                Toast.makeText(getApplicationContext(),"Your order is placed successfully!!",Toast.LENGTH_SHORT).show();
            }
        });
        ordericon=(ImageView)findViewById(R.id.ordericon);
        getSupportActionBar().hide();
        FragmentTransaction t=manager.beginTransaction();
        final MyDialogFragment myDialogFragment= new MyDialogFragment();
        loginProfile=(ImageView)findViewById(R.id.loginProfile);
        overflow=(ImageView)findViewById(R.id.overflow);
        overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });
        loginProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users").child(userphoneno);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String user= snapshot.child(userphoneno).getValue(String.class);
                        String phone=snapshot.getValue().toString();*/

                Bundle b = new Bundle();
                b.putString("name",name);
                b.putString("phoneno",userphoneno);
                myDialogFragment.setArguments(b);
                t.add(myDialogFragment, "MyFragment");
                t.commit();
                  /*  }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }*/
                /* });
                 *//* Intent i= getIntent();
                String user= i.getStringExtra("username");
                String phone= i.getStringExtra("phone");*//*
            }*/
            }
        });
        payment=(Button)findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,UPIPayment.class);
                startActivity(intent);
            }
        });
        ordericon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrder();
            }
        });
    }
   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/

    private void showOrder() {
        /*rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String order= snapshot.child("order").getValue().toString();
                Bundle b1=new Bundle();
                b1.putString("order",order);
                myFragment.setArguments(b1);
                t1.add(myFragment,"Fragment");
                t1.commit();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        /*Intent intent=getIntent();
        String order=intent.getStringExtra("order");*/
        Intent intent=new Intent(HomePage.this,ViewOrder.class);
        intent.putExtra("userphoneno",userphoneno);
        startActivity(intent);
    }

    private void showMenu(View v) {
        PopupMenu popupMenu=new PopupMenu(HomePage.this,v);
        popupMenu.getMenuInflater().inflate(R.menu.iconmenu,popupMenu.getMenu());
        popupMenu.show();
    }
}