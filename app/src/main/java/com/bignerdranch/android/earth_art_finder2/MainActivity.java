package com.bignerdranch.android.earth_art_finder2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<DataItem> lstData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstData = new ArrayList<>();

        lstData.add(new DataItem(R.drawable.spiraljetty, "Spiral Jetty"));
        lstData.add(new DataItem(R.drawable.amarilloramp, "Amarillo Ramp"));
        lstData.add(new DataItem(R.drawable.lightingfield, "Lighting Field"));
        lstData.add(new DataItem(R.drawable.suntunnels, "Sun Tunnels"));
        lstData.add(new DataItem(R.drawable.doublenegative, "Double Negative"));
        lstData.add(new DataItem(R.drawable.brokencircle, "Broken Circle"));
        lstData.add(new DataItem(R.drawable.city, "City"));

        ListView listView = (ListView)findViewById(R.id.ListView);

        CustomAdapter adapter = new CustomAdapter(this,R.layout.itemrow,lstData);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("Art Piece", lstData.get(position).ArtName);
                intent.putExtra("Art", lstData.get(position).resIdThumbnail);

                intent.setClass(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
