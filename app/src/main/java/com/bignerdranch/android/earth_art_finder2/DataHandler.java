package com.bignerdranch.android.earth_art_finder2;

import java.util.ArrayList;

public class DataHandler {

    public static ArrayList<DataItem> getDataItems()
    {
        ArrayList<DataItem> lstData = new ArrayList<>();

        lstData.add(new DataItem(R.drawable.spiraljetty, "Spiral Jetty"));
        lstData.add(new DataItem(R.drawable.amarilloramp, "Amarillo Ramp"));
        lstData.add(new DataItem(R.drawable.lightingfield, "Lighting Field"));
        lstData.add(new DataItem(R.drawable.suntunnels, "Sun Tunnels"));
        lstData.add(new DataItem(R.drawable.doublenegative, "Double Negative"));
        lstData.add(new DataItem(R.drawable.brokencircle, "Broken Circle"));
        lstData.add(new DataItem(R.drawable.city, "City"));
        return lstData;
    }
}
