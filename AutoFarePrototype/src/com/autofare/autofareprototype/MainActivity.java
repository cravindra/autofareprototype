package com.autofare.autofareprototype;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends FragmentActivity implements LocationListener{
	LocationManager mLocationManager;
	TextView tvEstFare,tvDistance;
	EditText etSrcAdd,etDestAdd;
	Button btCalculate;
	SupportMapFragment fm;
	GoogleMap map;
	
	
	MapHandler mapHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
            setContentView(R.layout.activity_main);
      
            
        btCalculate=(Button)this.findViewById(R.id.button1);
       
        etSrcAdd=(EditText)this.findViewById(R.id.et_src);
        etDestAdd=(EditText)this.findViewById(R.id.et_dest);
        tvEstFare=(TextView)this.findViewById(R.id.tv_fare);
        tvDistance=(TextView)this.findViewById(R.id.tv_dist);
        fm=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        map=fm.getMap();
        mapHandler=new MapHandler(map,tvEstFare,etSrcAdd,etDestAdd,fm,this,tvDistance);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = mLocationManager.getBestProvider(criteria, true);
        Location location = mLocationManager.getLastKnownLocation(provider);

        //Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null) {
        	double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
            
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            map.animateCamera(yourLocation);
        }
        else {
            //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,(long) 0, (float)0, (android.location.LocationListener) this);
        }
        
       
        
        
    }
    public void callHandler(View view)
    {
    	mapHandler.clickEventHandler();
    	Toast.makeText(this, "Clicked! Asking closest Auto Wallah..", Toast.LENGTH_SHORT).show();
    }
	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}
    
  
  
       
	

   
    
    
    
   /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
