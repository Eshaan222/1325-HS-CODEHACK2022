package com.Tracker.schoolbustracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GeneratorActivity extends AppCompatActivity {
    Button btn;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        btn = findViewById(R.id.generateButton);
        txt = findViewById(R.id.IdCodeText);

        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        String join=String.format("%06d", number);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText(join);
            }
        });
    }
}