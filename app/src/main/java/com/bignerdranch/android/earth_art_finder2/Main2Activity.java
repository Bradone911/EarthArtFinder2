package com.bignerdranch.android.earth_art_finder2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        TextView textView = (TextView)findViewById(R.id.textView);
        Button nxtButton = (Button)findViewById(R.id.nxtbutton);

        textView.setText(getIntent().getStringExtra("Art Piece"));
        imageView.setImageResource(getIntent().getIntExtra("Art",R.drawable.spiraljetty));

        int position = getIntent().getIntExtra("Current Position", 0);
        position++;
        position %= MainActivity.lstData.size();
        final int nextPosition = position;

        nxtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Art Piece", MainActivity.lstData.get(nextPosition).ArtName);
                intent.putExtra("Art", MainActivity.lstData.get(nextPosition).resIdThumbnail);
                intent.putExtra("Current Position", nextPosition);

                intent.setClass(Main2Activity.this, Main2Activity.class);/////*****Main2Activity was MainActivity
                startActivity(intent);
            }

        });

    }
}
