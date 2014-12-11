package com.finder.app.activity;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.finder.app.R;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 10/08/12
 * Time: 20:00
 */

public class FinderMapActivity extends AbstractActivity  {

/*implements LocationListener

    private LocationManager locationManager;
    private MapController mapController;

    private MapView mapView;

    private double latitude;
    private double longitude;
    private double altitude;
    private float accuracy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);


        LinearLayout mapActionBarLayout = (LinearLayout) findViewById(R.id.map_actionbar);
       // mapActionBarLayout.addView(getActionBar());

        mapView = (MapView) this.findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);

        mapController = mapView.getController();
        mapController.setZoom(17);
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);

        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 0, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        altitude = location.getAltitude();
        accuracy = location.getAccuracy();

        String msg = String.format(
                getResources().getString(R.string.new_location), latitude,
                longitude, altitude, accuracy);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

        GeoPoint p = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));

        mapController.animateTo(p);
        mapController.setCenter(p);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle bundle) {
        String newStatus = "";
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                newStatus = "OUT_OF_SERVICE";
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                newStatus = "TEMPORARILY_UNAVAILABLE";
                break;
            case LocationProvider.AVAILABLE:
                newStatus = "AVAILABLE";
                break;
        }
        String msg = String.format(
                getResources().getString(R.string.provider_disabled), provider,
                newStatus);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        String msg = String.format(
                getResources().getString(R.string.provider_enabled), provider);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }*/
}

