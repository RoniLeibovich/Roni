package com.roni2024.ronisfirstappplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, bL;
    TextView tv1;
    ConstraintLayout layout;

    ImageView iv;
    SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        // מציאת כפתור 1 והגדרת קליק
        b1 = findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setText("Thank you for your click");
                Log.d("Roni", "b1");
            }
        });

        // מציאת כפתור 2 והגדרת קליק
        b2 = findViewById(R.id.btn2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setText("So Fun");
                Log.d("Roni", "b2");
            }
        });

        // מציאת כפתור לינארי והגדרת קליק
        bL = findViewById(R.id.btnLinear);
        bL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // התחברות לפעילות NoamActivity2
                Intent intent = new Intent(MainActivity.this, NoamActivity2.class);
                startActivity(intent);
                finish();
            }
        });


        // מציאת ImageView והגדרת SeekBar
        iv = findViewById(R.id.iv); // הוודא ש-ID תואם ל-ImageView ב-XML
        sb = findViewById(R.id.sb); // הוודא ש-ID תואם ל-SeekBar ב-XML

        // ברירת המחדל היא 100 (שקיפות מלאה)
        sb.setProgress(100);

        // הגדרת מאזין ל-SeekBar לשנות את השקיפות
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float alpha = (float) i / 100; // מחשבים את הערך לשקיפות
                iv.setAlpha(alpha); // עדכון השקיפות של התמונה
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // כאן אפשר להוסיף לוגיקה אם צריך
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // כאן אפשר להוסיף לוגיקה אם צריך
            }
        });
    }
}
