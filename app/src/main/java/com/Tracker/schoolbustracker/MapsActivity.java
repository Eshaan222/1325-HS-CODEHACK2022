package com.Tracker.schoolbustracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends AppCompatActivity {

    Button search,chatButton;
    EditText busId;

    DatabaseReference myRef,myRefLong,myRefLat;
    FirebaseDatabase database;

    String latitude,longitude;
    WebView webView;

    public static final int chatNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        database = FirebaseDatabase.getInstance();
        chatButton = findViewById(R.id.ChatButton);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this,ChatActivity.class);
//                intent.putExtra(String.valueOf(chatNumber),"1");
                startActivity(intent);
            }
        });

        search = findViewById(R.id.searchButton);
        busId = findViewById(R.id.enterBusId);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        busId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String busIdCode = busId.getText().toString();

                myRef = database.getReference("123456");
                myRefLong = database.getReference("123456/longitude");
                myRefLat = database.getReference("123456/latitude");

                myRefLong.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        longitude = snapshot.getValue(String.class);
//                        Toast.makeText(MapsActivity.this,longitude,Toast.LENGTH_LONG).show();
                        webView.loadUrl("https://www.google.com/maps?q="+latitude+","+longitude);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRefLat.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        latitude = snapshot.getValue(String.class);
//                        Toast.makeText(MapsActivity.this,latitude,Toast.LENGTH_LONG).show();
                        webView.loadUrl("https://www.google.com/maps?q="+latitude+","+longitude+"");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });



    }
}