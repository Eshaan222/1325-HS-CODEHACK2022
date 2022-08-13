package com.Tracker.schoolbustracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {
    int chatNumber;
    EditText messageTextView;
    String busId;
    Button sendButton;
    FirebaseDatabase database;
    DatabaseReference myChatRef;
    ArrayList<model> datalist;
    myadapter adapter;
    RecyclerView recview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageTextView = findViewById(R.id.chat_textview);
        sendButton = findViewById(R.id.sendChatButton);

        database = FirebaseDatabase.getInstance();
        datalist = new ArrayList<model>();

        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("School Bus Tracker");
        alert.setMessage("Please type the bus id");

// Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);
        AlertDialog.Builder alertForChatNumber = new AlertDialog.Builder(this);
        alertForChatNumber.setTitle("School Bus Tracker");
        alertForChatNumber.setMessage("Are you the bus driver?");


        alertForChatNumber.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                chatNumber = 1;
                myChatRef = database.getReference(busId+"chats");
                adapter = new myadapter(datalist);
                recview.setAdapter(adapter);


                myChatRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        datalist.clear();
                        for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {

                            String message = zoneSnapshot.child("message").getValue(String.class);
                            int chatNumberFirebase = Integer.parseInt(zoneSnapshot.child("chatNumber").getValue(String.class));
//                            Toast.makeText(ChatActivity.this,value,Toast.LENGTH_LONG).show();
                            model ld;
                            if(chatNumber == chatNumberFirebase) {
                                ld = new model(message, chatNumber,"right");
                            }else{
                                ld = new model(message,chatNumber,"left");
                            }
                            datalist.add(ld);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


//                Toast.makeText(ChatActivity.this,value,Toast.LENGTH_LONG).show();
            }
        });

        alertForChatNumber.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                chatNumber = 0;
                myChatRef = database.getReference(busId+"chats");
                adapter = new myadapter(datalist);
                recview.setAdapter(adapter);

                myChatRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        datalist.clear();
                        for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {

                            String message = zoneSnapshot.child("message").getValue(String.class);
                            int chatNumberFirebase = Integer.parseInt(zoneSnapshot.child("chatNumber").getValue(String.class));
//                            Toast.makeText(ChatActivity.this,value,Toast.LENGTH_LONG).show();
                            model ld;
                            if(chatNumber == chatNumberFirebase) {
                                ld = new model(message, chatNumber,"right");
                            }else{
                                ld = new model(message,chatNumber,"left");
                            }
                            datalist.add(ld);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                busId = value;
                alertForChatNumber.show();
//                Toast.makeText(ChatActivity.this,value,Toast.LENGTH_LONG).show();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
                Intent intent = new Intent(ChatActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        alert.show();



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messageTextView.getText().toString();
                HashMap<String, String> map = new HashMap<>();
                map.put("message", message);
                map.put("chatNumber",String.valueOf(chatNumber));


                myChatRef.push().setValue(map);

                messageTextView.setText("");

            }
        });


    }
}