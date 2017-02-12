package com.example.tamillich.mapsearch.module;

/**
 * Created by Andfile on 12.02.2017.
 */

public class Places {

    private String mFormatted_address;
    private Geometry mGeometry;
    private String mIcon;
    private String mId;
    private String mName;
    private String[] mTypes;

    public String getFormatted_address() {
        return mFormatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        mFormatted_address = formatted_address;
    }

    public Geometry getGeometry() {
        return mGeometry;
    }

    public void setGeometry(Geometry geometry) {
        mGeometry = geometry;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String[] getTypes() {
        return mTypes;
    }

    public void setTypes(String[] types) {
        mTypes = types;
    }
}
