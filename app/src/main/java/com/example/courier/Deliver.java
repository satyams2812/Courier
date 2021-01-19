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

public class Deliver extends AppCompatActivity {
    private Button otheradd;
    private TextView getotherr;
    private LinearLayout other;
    private EditText add1;
    private EditText add2;
    private EditText add3;
    private EditText add4;
    private EditText add5;
    private EditText add6;
    private Button donee;
    private Button sett;
    private Button out;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);
        out=(Button)findViewById(R.id.out);
        getotherr=(TextView)findViewById(R.id.getotherr);
        other=(LinearLayout)findViewById(R.id.other);
        add1=(EditText)findViewById(R.id.add1);
        add2=(EditText)findViewById(R.id.add2);
        add3=(EditText)findViewById(R.id.add3);
        add4=(EditText)findViewById(R.id.add4);
        add5=(EditText)findViewById(R.id.add5);
        add6=(EditText)findViewById(R.id.add6);
        donee=(Button)findViewById(R.id.donee);
        sett=(Button) findViewById(R.id.sett);
        donee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("deliver_address");
                reference.setValue(add1.getText().toString()+" "+add2.getText().toString()+" "+add3.getText().toString()+ " "+add4.getText().toString()+" "+add5.getText().toString()+" "+add6.getText().toString());*/
                Intent ms= new Intent(Deliver.this,MainActivity.class);
                ms.putExtra("messaging",add1.getText().toString()+" "+add2.getText().toString()+" "+add3.getText().toString()+ " "+add4.getText().toString()+" "+add5.getText().toString()+" "+add6.getText().toString());
               setResult(RESULT_OK,ms);
               finish();
            }
        });
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d=new Intent(Deliver.this,ShowMap.class);
                startActivityForResult(d,88);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==88){
            if(resultCode==RESULT_OK){
                String jeee=data.getStringExtra("messes");
                getotherr.setText(data.getStringExtra("messes"));
                sett.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*rootNode=FirebaseDatabase.getInstance();
                        reference=rootNode.getReference("deliver_address");
                        reference.setValue(jeee);*/
                        Intent k= new Intent(Deliver.this,MainActivity.class);
                        k.putExtra("message",jeee);
                        setResult(RESULT_OK,k);
                        finish();
                    }
                });
            }
        }
    }
}