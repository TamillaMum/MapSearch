package com.example.tamillich.mapsearch.module;

/**
 * Created by jbt on 12/02/2017.
 */
public class Viewport {

    private MyLocation mNortheast;
    private MyLocation mSouthwest;

    public MyLocation getSouthwest() {
        return mSouthwest;
    }

    public void setSouthwest(MyLocation southwest) {
        mSouthwest = southwest;
    }

    public MyLocation getNortheast() {
        return mNortheast;
    }

    public void setNortheast(MyLocation northeast) {
        mNortheast = northeast;
    }
}
