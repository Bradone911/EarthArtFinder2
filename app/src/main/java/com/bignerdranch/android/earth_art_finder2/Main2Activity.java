package com.bignerdranch.android.earth_art_finder2;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.File;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final int cameraRequest = 7785;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setText(getIntent().getStringExtra("Art Piece"));
        imageView.setImageResource(getIntent().getIntExtra("Art", R.drawable.spiraljetty));

        ImageView mImageView = (ImageView) findViewById(R.id.imageView2);

        createPrevButton();
        createNextButton();
        createCameraButton();
        createMapButton();

        //back button up top in app bar
        getSupportActionBar().setTitle("Land Art Description");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }


    private void createPrevButton() {
        Button prevButton = (Button) findViewById(R.id.prevbutton);
        int position = getIntent().getIntExtra("Current Position", 0);
        position--;
        position = position < 0 ? MainActivity.lstData.size() + position : position;
        final int prevPosition = position;

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Art Piece", MainActivity.lstData.get(prevPosition).ArtName);
                intent.putExtra("Art", MainActivity.lstData.get(prevPosition).resIdThumbnail);
                intent.putExtra("Current Position", prevPosition);

                intent.setClass(Main2Activity.this, Main2Activity.class);/////*****Main2Activity was MainActivity
                startActivity(intent);
            }

        });
    }

    private void createNextButton() {
        Button nxtButton = (Button) findViewById(R.id.nxtbutton);
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

    private void createCameraButton(){
        Button btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, cameraRequest);
            }
        });
    }

    private void createMapButton(){
        if (isServicesOK()) {
            Button btnMap = (Button) findViewById(R.id.mapbutton);
            btnMap.setOnClickListener(new View.OnClickListener()    {
                @Override
                public void onClick(View view)  {
                    Intent intent = new Intent(Main2Activity.this, MapActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)   {
        Log.i("request code", Integer.toString(requestCode));
        Log.i("result code", Integer.toString(resultCode));
        Uri u;
        if(requestCode == cameraRequest){
            if(resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap)extras.get("data");
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            }
        }
    }
    public boolean isServicesOK()   {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Main2Activity.this);

        if (available == ConnectionResult.SUCCESS)  {
            //everything is fine and the user can make map request
            Log.d(TAG, "isServiceOK: Google Play Services is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available))  {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fit it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Main2Activity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}

