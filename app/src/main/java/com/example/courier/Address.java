package com.example.courier;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Address extends AppCompatActivity {
    private Button otheradd;
    private TextView getother;
    private LinearLayout other;
    private EditText add1;
    private EditText add2;
    private EditText add3;
    private EditText add4;
    private EditText add5;
    private EditText add6;
    private Button done;
    private Button set;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        otheradd=(Button)findViewById(R.id.otheradd);
        getother=(TextView)findViewById(R.id.getother);
        other=(LinearLayout)findViewById(R.id.other);
        add1=(EditText)findViewById(R.id.add1);
        add2=(EditText)findViewById(R.id.add2);
        add3=(EditText)findViewById(R.id.add3);
        add4=(EditText)findViewById(R.id.add4);
        add5=(EditText)findViewById(R.id.add5);
        add6=(EditText)findViewById(R.id.add6);
        done=(Button)findViewById(R.id.done);
        set=(Button) findViewById(R.id.set);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ms= new Intent(Address.this,MainActivity.class);
                ms.putExtra("locate",add1.getText().toString()+" "+add2.getText().toString()+" "+add3.getText().toString()+ " "+add4.getText().toString()+" "+add5.getText().toString()+" "+add6.getText().toString());
                setResult(RESULT_OK,ms);
                finish();
            }
        });
        otheradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d=new Intent(Address.this,OtherMap.class);
                startActivityForResult(d,99);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==99){
            if(resultCode==RESULT_OK){
                String jee=data.getStringExtra("mes");
                getother.setText(data.getStringExtra("mes"));
                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       /* rootNode=FirebaseDatabase.getInstance();
                        reference=rootNode.getReference("pickup_address");
                        reference.setValue(jee);*/
                        Intent k= new Intent(Address.this,MainActivity.class);
                        k.putExtra("other",jee);
                        setResult(RESULT_OK,k);
                        finish();
                    }
                });
            }
        }
    }
}