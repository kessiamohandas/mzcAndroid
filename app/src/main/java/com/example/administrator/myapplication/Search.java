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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Search extends AppCompatActivity {
    EditText E1,E2,E3,E4;
    Button B1;
    String admno ,url="http://logixspace.com/mzc/search.php";
    ProgressBar p1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        E1=(EditText)findViewById(R.id.admno);
        E2=(EditText)findViewById(R.id.nam);
        E3=(EditText)findViewById(R.id.rol);
        E4=(EditText)findViewById(R.id.bach);
        B1=(Button)findViewById(R.id.ser);
        p1=(ProgressBar)findViewById(R.id.pb);
B1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        admno=E1.getText().toString();
        searchFromDB();
    }


});

    }

    private void searchFromDB() {
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String respFromServer = jsonObject.getString("status");

                            p1.setVisibility(View.GONE);
                            if (respFromServer.equals("success")) {
                                E2.setVisibility(View.VISIBLE);
                                E3.setVisibility(View.VISIBLE);
                                E4.setVisibility(View.VISIBLE);

                                Toast.makeText(getApplicationContext(), "Success API", Toast.LENGTH_SHORT).show();

                                JSONArray jsonArray = jsonObject.getJSONArray("data");

                                for(int i=0; i<jsonArray.length(); i++)
                                {
                                    JSONObject obj=jsonArray.getJSONObject(i);
                                    String Name=obj.getString("name");
                                    String RollNo=obj.getString("rollno");
                                    String Branch=obj.getString("branch");
                                    E2.setText(Name);
                                    E3.setText(RollNo);
                                    E4.setText(Branch);

                                   // Toast.makeText(getApplicationContext(), Name, Toast.LENGTH_SHORT).show();
                                   // Toast.makeText(getApplicationContext(), RollNo, Toast.LENGTH_SHORT).show();
                                   // Toast.makeText(getApplicationContext(), Branch, Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(getApplicationContext(), "Error API", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("admno",admno);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
