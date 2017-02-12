package com.example.tamillich.mapsearch.module;

import java.util.ArrayList;

/**
 * Created by Andfile on 12.02.2017.
 */

public class JsonRequestResult {

    private String mHtml_attributions;
    private String mNext_page_token;
    private ArrayList<Places> mResults;
    private String mStatus;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public ArrayList<Places> getResults() {
        return mResults;
    }

    public void setResults(ArrayList<Places> results) {
        mResults = results;
    }

    public String getNext_page_token() {
        return mNext_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        mNext_page_token = next_page_token;
    }

    public String getHtml_attributions() {
        return mHtml_attributions;
    }

    public void setHtml_attributions(String html_attributions) {
        mHtml_attributions = html_attributions;
    }
}
