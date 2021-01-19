package com.example.courier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class OtherMap extends FragmentActivity implements  OnMapReadyCallback {
    Button but_location;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    FusedLocationProviderClient client;
    private static final int REQUEST_CODE = 101;
    TextView getadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_map);
        client= LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
        but_location = (Button)findViewById(R.id.but_location);
        getadd=(TextView)findViewById(R.id.getadd);
    }
    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location !=null)
                {
                    currentLocation=location;
                    Toast.makeText(getApplicationContext(),currentLocation.getLatitude()+""+currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
                    supportMapFragment.getMapAsync(OtherMap.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng= new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        LatLng center= googleMap.getCameraPosition().target;
        String address= getStringAddress(latLng.latitude,latLng.longitude);
        MarkerOptions markerOptions= new MarkerOptions().position(latLng).title(address);
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        googleMap.addMarker(markerOptions);
        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLng center= googleMap.getCameraPosition().target;
                LatLng latLng= new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
                if(googleMap.addMarker(markerOptions)!=null)
                {
                    googleMap.addMarker(markerOptions).remove();
                    googleMap.addMarker(new MarkerOptions().position(center).title(getStringAddress(center.latitude,center.longitude)));
                    latLng=googleMap.addMarker(markerOptions).getPosition();
                    getadd.setText(getStringAddress(center.latitude,center.longitude));
                    String loca= getStringAddress(center.latitude,center.longitude);
                    but_location.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent ik=new Intent(OtherMap.this, com.example.courier.Address.class);
                            ik.putExtra("mes",loca);
                            setResult(RESULT_OK,ik);
                            finish();
                        }
                    });
                }
            }
        });
    }

    private String getStringAddress(double latitude, double longitude) {
        String loc="";
        Geocoder geocoder;
        List<Address> addresse;
        geocoder= new Geocoder(this,Locale.getDefault());
        try{
            addresse= geocoder.getFromLocation(latitude,longitude,1);
            loc= addresse.get(0).getAddressLine(0);
        }
        catch (IOException e)
        {
            e.printStackTrace();;
        }
        return loc;
    }

    private String getAddress(Context ctx, double latitude, double longitude) {
        String fulladd=null;
        try{
            Geocoder geocoder= new Geocoder(ctx,Locale.getDefault());
            List<android.location.Address> addresses= geocoder.getFromLocation(latitude,longitude,1);
            if(addresses.size()>0){
                Address address= addresses.get(0);
                fulladd=address.getAddressLine(0);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return fulladd;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==101){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                fetchLastLocation();
            }
        }
    }
}