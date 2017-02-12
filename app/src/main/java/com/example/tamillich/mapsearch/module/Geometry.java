package com.example.tamillich.mapsearch.module;

/**
 * Created by Andfile on 12.02.2017.
 */
public class Geometry {

    private MyLocation mLocation;
    private Viewport mViewport;

    public Viewport getViewport() {
        return mViewport;
    }

    public void setViewport(Viewport viewport) {
        mViewport = viewport;
    }

    public MyLocation getLocation() {
        return mLocation;
    }

    public void setLocation(MyLocation location) {
        mLocation = location;
    }
}
