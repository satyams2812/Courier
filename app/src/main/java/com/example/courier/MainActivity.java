package com.example.courier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private ImageView addlist;
    private ImageView addphoto;
    private RadioButton anywhere;
    private RadioButton custom;
    private LinearLayout hide;
    private LinearLayout deliverhide;
    private RadioButton deliver;
    private ConstraintLayout arrow;
    private ConstraintLayout deliverarrow;
    private TextView deli;
    private TextView del;
    private TextView de;
    private TextView d;
    private EditText order;
    private Button placeOrder;
    private  EditText pickupname;
    private EditText pickupphone;
    private  EditText delivername;
    private EditText deliverphone;
    private Button confirmpick;
    private Button confirmdeliver;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private static final int PICK_IMAGE= 10;
    Uri imageUri;
    private static final int PICK = 20;
    Uri photoUri;
    String get;
    String no;
    String deliveryaddress;
    String pickaddress;
    String delivermapaddress;
    String pickmapaddress;

    int count;
    FragmentManager manager= getSupportFragmentManager();
    FragmentTransaction t1=manager.beginTransaction();
    final MyFragment myFragment=new MyFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        order=(EditText)findViewById(R.id.order);
        pickupname=(EditText)findViewById(R.id.pickupname);
        pickupphone=(EditText)findViewById(R.id.pickupphone);
        delivername=(EditText)findViewById(R.id.delivername);
        deliverphone=(EditText)findViewById(R.id.deliverphone);
        placeOrder=(Button)findViewById(R.id.placeOrder);
        addlist = (ImageView) findViewById(R.id.addlist);
        addphoto = (ImageView) findViewById(R.id.addphoto);
        anywhere = (RadioButton) findViewById(R.id.anywhere);
        d=(TextView)findViewById(R.id.d);
        custom = (RadioButton) findViewById(R.id.custom);
        hide = (LinearLayout) findViewById(R.id.hide);
        deli=(TextView)findViewById(R.id.deli);
        del=(TextView)findViewById(R.id.del);
        de=(TextView)findViewById(R.id.de);
        deliverhide = (LinearLayout) findViewById(R.id.deliverhide);
        deliver = (RadioButton) findViewById(R.id.deliver);
        deliverarrow=(ConstraintLayout)findViewById(R.id.deliverarrow);
        arrow=(ConstraintLayout) findViewById(R.id.arrow);

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                /*rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");*/
               /* String name= get;
                String phoneno=no;*/
               /* String menu= order.getText().toString();
                String pickname=pickupname.getText().toString();
                String pickphone=pickupphone.getText().toString();
                String deliverusername=delivername.getText().toString();
                String deliverphoneno= deliverphone.getText().toString();
                String deliveraddress=deliveryaddress;
                String pickupaddress=pickaddress;
                String mapdeliveradddress=delivermapaddress;
                String mappickupaddress=pickmapaddress;*/
               /* UserHelperClass helperClass= new UserHelperClass(menu,pickname,pickphone,deliverusername,deliverphoneno,pickupaddress,deliveraddress,mapdeliveradddress,mappickupaddress);
                reference.child(menu).setValue(helperClass);*/
                if(get==null||no==null) {
                    Toast.makeText(getApplicationContext(),"Register now",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, Login.class);
                    String menu= order.getText().toString();
                    String pickname=pickupname.getText().toString();
                    String pickphone=pickupphone.getText().toString();
                    String deliverusername=delivername.getText().toString();
                    String deliverphoneno= deliverphone.getText().toString();
                    String deliveraddress=deliveryaddress;
                    String pickupaddress=pickaddress;
                    String mapdeliveradddress=delivermapaddress;
                    String mappickupaddress=pickmapaddress;
                    i.putExtra("menu", menu);
                    i.putExtra("pickname",pickname);
                    i.putExtra("pickphone",pickphone);
                    i.putExtra("deliverusername",deliverusername);
                    i.putExtra("deliverphoneno",deliverphoneno);
                    i.putExtra("deliveraddress",deliveraddress);
                    i.putExtra("pickupaddress",pickupaddress);
                    i.putExtra("mapdeliveradddress",mapdeliveradddress);
                    i.putExtra("mappickupaddress",mappickupaddress);
                    startActivityForResult(i,33);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Registed successfully",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, HomePage.class);
                    i.putExtra("username",get);
                    i.putExtra("userphoneno",no);
                    startActivity(i);
                }
            }
        });
        deliverarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Deliver.class);
                startActivityForResult(intent,2);
                deliver.setVisibility(View.VISIBLE);
                custom.setVisibility(View.VISIBLE);
            }
        });
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Address.class);
                startActivityForResult(intent,1);
                custom.setVisibility(View.VISIBLE);
                deliver.setVisibility(View.VISIBLE);
            }
        });
        deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deliver.isSelected())
                {
                    deliver.setSelected(false);
                    deliver.setChecked(false);
                    deliverhide.setVisibility(View.GONE);
                }
                else
                {
                    deliver.setSelected(true);
                    deliver.setChecked(true);
                    deliverhide.setVisibility(View.VISIBLE);
                }
            }
        });
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (custom.isSelected())
                {
                    custom.setSelected(false);
                    custom.setChecked(false);
                    hide.setVisibility(View.GONE);
                }
                else
                {
                    custom.setSelected(true);
                    custom.setChecked(true);
                    hide.setVisibility(View.VISIBLE);
                }
            }
        });
        anywhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide.setVisibility(View.GONE);
            }
        });
        addlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGall();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.firstmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.your_order) {
           /* String list=order.getText().toString();
            Bundle b1=new Bundle();
            b1.putString("order",list);
            myFragment.setArguments(b1);
            t1.add(myFragment,"Fragment");
            t1.commit();*/
            Intent intent= new Intent(MainActivity.this,ViewOrder.class);
            startActivity(intent);
        }
        else  if(id==R.id.profile)
        {
            if(get==null||no==null)
            {
                Toast.makeText(getApplicationContext(),"Register First",Toast.LENGTH_SHORT).show();
            }
            else {
                FragmentTransaction t=manager.beginTransaction();
                final MyDialogFragment myDialogFragment= new MyDialogFragment();
                Bundle b=new Bundle();
                b.putString("name",get);
                b.putString("phoneno",no);
                myDialogFragment.setArguments(b);
                t.add(myDialogFragment,"MyFragment");
                t.commit();
            }
        }
        return super.onOptionsItemSelected(item);
        }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    private void openGall() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            addlist.setImageURI(imageUri);
        }
        if (resultCode == RESULT_OK && requestCode == PICK) {
            photoUri = data.getData();
            addphoto.setImageURI(photoUri);
        }
        if(requestCode==2 && resultCode==RESULT_OK){
                d.setText(data.getStringExtra("messaging"));
                deliveryaddress = data.getStringExtra("messaging");
                deli.setText(data.getStringExtra("message"));
                delivermapaddress=data.getStringExtra("message");
        }
        if(requestCode==1 && resultCode==RESULT_OK){
                del.setText(data.getStringExtra("locate"));
                pickaddress=data.getStringExtra("locate");
                de.setText(data.getStringExtra("other"));
                pickmapaddress=data.getStringExtra("other");
        }
        if(requestCode==33 && resultCode==RESULT_OK){
            get= data.getStringExtra("username");
            no=data.getStringExtra("phone");
           /* reference=rootNode.getReference("register_name");
            reference.setValue(get);
            reference=rootNode.getReference("register_phoneno");
            reference.setValue(no);*/
        }
    }
}
