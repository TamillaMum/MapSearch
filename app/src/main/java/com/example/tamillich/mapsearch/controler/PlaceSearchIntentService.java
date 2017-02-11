package com.example.tamillich.mapsearch.controler;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.tamillich.mapsearch.module.Const;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class PlaceSearchIntentService extends IntentService {
    private String mAction;


    public PlaceSearchIntentService() {
        super("PlaceSearchIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        mAction = intent.getAction();

        switch (mAction) {
            case Const.ACTION_FIND_BY_RADIUS: {
                Log.e(Const.ACTION_FIND_BY_RADIUS, "do something");


                Intent resultIntent = new Intent(intent.getAction());
                notifyActionDone(resultIntent);
            }
            break;
            case Const.ACTION_FIND_NEAR_BY_PLACE: {

                String coordinate = intent.getStringExtra(Const.EXTRA_CURRENT_COORDINATE);
                String query = intent.getStringExtra(Const.EXTRA_SEARCH_QUERY);
                String json = onHttpRequest(Const.SEARCH_BY_PLACE + query + Const.MY_LOCATION + coordinate + Const.RADIUS + Const.GOOGLE_API);

                Intent resultIntent = new Intent(intent.getAction());
                resultIntent.putExtra(Const.RESULT_JSON, json);
                notifyActionDone(resultIntent);

            }
            break;

        }
    }

    private void notifyActionDone(Intent resultIntent) {

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(resultIntent);

    }


    private String onHttpRequest(String urlString) {

        BufferedReader input = null;
        HttpURLConnection httpCon = null;
        InputStream input_stream = null;
        InputStreamReader input_stream_reader = null;
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(urlString);

            Log.e("TEST", "TRY IN CONNECTION" + urlString);
            httpCon = (HttpURLConnection) url.openConnection();
            if (httpCon.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e("NETWORK", "Cannot Connect to : " + urlString);
                return null;
            }

            input_stream = httpCon.getInputStream();
            input_stream_reader = new InputStreamReader(input_stream);
            input = new BufferedReader(input_stream_reader);
            String line;
            while ((line = input.readLine()) != null) {
                response.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input_stream_reader.close();
                    input_stream.close();
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (httpCon != null) {
                    httpCon.disconnect();
                }
            }
        }
        return response.toString();
    }


}
