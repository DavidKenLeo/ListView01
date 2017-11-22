package com.dkl.auser.listview01;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Context context;
    String str[];
    boolean b[] ;
    private ImageView img;
    private ListView lv;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.listView);


        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
       StringRequest request = new StringRequest("http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Gson gson = new Gson();
                        Zoo z = gson.fromJson(response,Zoo.class);
                        Log.d("ZOO", z.result.results[0].E_Name);

                        adapter = new MyAdapter(MainActivity.this,z.result.results);
                        lv.setAdapter(adapter);

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Zoo error", "error"+error.toString());
            }
        }
        );
//        String url = "http://www.zoo.gov.tw/iTAP/05_Exhibit/01_FormosanAnimal.jpg";
//
//        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap response) {
//                img.setImageBitmap(response);
//            }
//        }, 0, 0, ImageView.ScaleType.CENTER,   null  , new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        queue.add(imageRequest );
        queue.add(request);
        queue.start();
    }
}
