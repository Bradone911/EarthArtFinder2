package com.bignerdranch.android.earth_art_finder2;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<DataItem> lstData;
    public static DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHandler(this);

        lstData = db.getAllItemsList();

        ListView listView = (ListView)findViewById(R.id.ListView);

        CustomAdapter adapter = new CustomAdapter(this, R.layout.itemrow, lstData);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {/////does parent need to be adapterView
                Intent intent = new Intent();
                intent.putExtra("Art Piece", lstData.get(position).ArtName);
                intent.putExtra("Art", lstData.get(position).resIdThumbnail);
                intent.putExtra("Current Position", position);
                //intent.putExtra("Item List", lstData);

                intent.setClass(MainActivity.this, Main2Activity.class);/////*****Main2Activity was MainActivity
                startActivity(intent);
            }
        });
    }
}
