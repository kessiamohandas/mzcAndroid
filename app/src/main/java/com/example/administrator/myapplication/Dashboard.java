package com.example.administrator.myapplication;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    ImageView img1,img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        img1=(ImageView)findViewById(R.id.add);
        img2=(ImageView)findViewById(R.id.search);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(getApplicationContext(),"Add button",Toast.LENGTH_SHORT).show();
                Intent i =new Intent(getApplicationContext(),Add.class);
                startActivity(i);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(),"Search button",Toast.LENGTH_SHORT).show();
                Intent i =new Intent(getApplicationContext(),Search.class);
                startActivity(i);
            }
        });

    }



}
