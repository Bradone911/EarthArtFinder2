package com.bignerdranch.android.earth_art_finder2;

public class DataItem {

    int resIdThumbnail;
    String ArtName;

    public DataItem(int resIdThumbnail, String ArtName) {
        this.resIdThumbnail = resIdThumbnail;
        this.ArtName = ArtName;
    }

    public DataItem(){
        this(-1, "This shouldn't exist ever");
    }

    public void setResIdThumbnail(int resIdThumbnail) {
        this.resIdThumbnail = resIdThumbnail;
    }

    public void setArtName(String artName){
        this.ArtName = artName;
    }
}
