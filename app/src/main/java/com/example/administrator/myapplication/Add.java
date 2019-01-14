package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.rules.Timeout;

import java.util.HashMap;
import java.util.Map;

public class Add extends AppCompatActivity {
    String Url="http://logixspace.com/mzc/add.php";
    EditText ed1,ed2,ed3,ed4;
    Button b1,b2;
    ProgressBar p1;
    String name,rollno,admno,branch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ed1=(EditText)findViewById(R.id.name);
        ed2=(EditText)findViewById(R.id.rolno);
        ed3=(EditText)findViewById(R.id.adno);
        ed4=(EditText)findViewById(R.id.branch);
        b1=(Button)findViewById(R.id.sub);
        p1=(ProgressBar)findViewById(R.id.pb);
        //b2=(Button)findViewById(R.id.caln);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = ed1.getText().toString();
                rollno = ed2.getText().toString();
                admno = ed3.getText().toString();
                branch = ed4.getText().toString();
//              Toast.makeText(getApplicationContext(),name+rollno+admno+branch, Toast.LENGTH_SHORT).show();
                p1.setVisibility(View.VISIBLE);
                addToDB();
            }
        });
}

    private void addToDB() {
        //Toast.makeText(getApplicationContext(),"HELLO", Toast.LENGTH_SHORT).show();
       StringRequest stringRequest= new StringRequest(Request.Method.POST, Url,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {

                       try {
                           JSONObject jsonObject=new JSONObject(response);
                           String respServer = jsonObject.getString("status");
                           p1.setVisibility(View.GONE);
                           if (respServer.equals("success")) {
                               Toast.makeText(getApplicationContext(), "Success API", Toast.LENGTH_SHORT).show();

                           } else {
                               Toast.makeText(getApplicationContext(), "Error API", Toast.LENGTH_SHORT).show();

                           }
                       } catch (JSONException e) {
                           Toast.makeText(getApplicationContext(), "EXCEPTION"+e, Toast.LENGTH_SHORT).show();
                           e.printStackTrace();
                       }

                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               }
       )

        {
            //DATA TO BE SEND

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<String, String>();
                params.put("name",name);
                params.put("rollno",rollno);
                params.put("admno",admno);
                params.put("branch",branch);
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        }
    }
