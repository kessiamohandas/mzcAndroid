package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usrnm,pass;
    Button b1;
    String email,passwrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usrnm =(EditText)findViewById(R.id.email);
        pass =(EditText)findViewById(R.id.pass);
        b1=(Button)findViewById(R.id.loginb);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               email=usrnm.getText().toString();
                passwrd=pass.getText().toString();
                //Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),passwrd,Toast.LENGTH_SHORT).show();

                //if (email.equals("Admin") && passwrd.equals("123"))
               // {
                    Intent i =new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(i);
                //}
               // else
                 //   Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
