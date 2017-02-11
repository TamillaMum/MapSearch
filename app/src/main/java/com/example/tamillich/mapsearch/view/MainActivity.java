package com.example.tamillich.mapsearch.view;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tamillich.mapsearch.R;
import com.example.tamillich.mapsearch.controler.PlaceSearchIntentService;
import com.example.tamillich.mapsearch.module.Const;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Button mByPlace, mByNear;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    protected String mLatitudeLabel;
    protected String mLongitudeLabel;
    private String mLatitudeText, mLongitudeText;
    private EditText mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSearchText = (EditText) findViewById(R.id.searchPlace);
        mLatitudeLabel = "Latitude";
        mLongitudeLabel = "Longitude";

        buildGoogleApiClient();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        mByPlace = (Button) findViewById(R.id.button_byPlace);
        mByPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mSearchText.getText().toString().isEmpty()) {

                    Intent intent = new Intent(MainActivity.this, PlaceSearchIntentService.class);
                    intent.setAction(Const.ACTION_FIND_NEAR_BY_PLACE);
                    String coordinates = mLatitudeText+","+mLongitudeText;
                    Log.e("Coordinate" , coordinates);
                    intent.putExtra(Const.EXTRA_CURRENT_COORDINATE,coordinates);
                    intent.putExtra(Const.EXTRA_SEARCH_QUERY, mSearchText.getText().toString());
                    startService(intent);
                } else
                    Toast.makeText(MainActivity.this, "Enter the search Place", Toast.LENGTH_SHORT).show();
            }
        });
        mByNear = (Button) findViewById(R.id.button_byRadius);
        mByNear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, PlaceSearchIntentService.class);
                intent.setAction(Const.ACTION_FIND_BY_RADIUS);
                startService(intent);

            }
        });

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter actionsToCatch = new IntentFilter(Const.ACTION_FIND_BY_RADIUS);
        actionsToCatch.addAction(Const.ACTION_FIND_NEAR_BY_PLACE);
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(mReciver, actionsToCatch);
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(mReciver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public BroadcastReceiver mReciver = new BroadcastReceiver() {

        private String mAction;

        @Override
        public void onReceive(Context context, Intent intent) {

            mAction = intent.getAction();

            switch (mAction) {
                case Const.ACTION_FIND_BY_RADIUS:

                    Toast.makeText(context, "From radius", Toast.LENGTH_SHORT).show();

                    break;
                case Const.ACTION_FIND_NEAR_BY_PLACE:
                    String json = intent.getStringExtra(Const.RESULT_JSON);
                    if (json != null)
                        Toast.makeText(context, json, Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context, "Error request", Toast.LENGTH_SHORT).show();

                    break;
            }

        }
    };

    @Override
    public void onConnected(@Nullable Bundle bundle) {

       /* if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
           final int REQUEST_LOCATION = 2;

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Display UI and wait for user interaction
            } else {
                ActivityCompat.requestPermissions(
                        this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_LOCATION);
            }
        } else {
            // permission has been granted, continue as usual
            Location myLocation =
                    LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText = String.valueOf(mLastLocation.getLatitude());
            mLongitudeText = String.valueOf(mLastLocation.getLongitude());

            Toast.makeText(MainActivity.this,mLatitudeText+" "+mLongitudeText,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No Connection", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(getClass().toString(), "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }
}
