package com.bignerdranch.android.earth_art_finder2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        TextView textView = (TextView)findViewById(R.id.textView);

        textView.setText(getIntent().getStringExtra("Art Piece"));
        imageView.setImageResource(getIntent().getIntExtra("Art",R.drawable.spiraljetty));
    }
}
