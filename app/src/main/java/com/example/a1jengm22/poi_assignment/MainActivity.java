package com.example.a1jengm22.poi_assignment;

import android.content.Intent;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity
{

    MapView mv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        org.osmdroid.config.Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));
        setContentView(R.layout.activity_main);

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(51.05,-0.72));
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.addNewPoi)
        {
            // react to the menu item being selected...
            // System.exit(0); this code closes the app

            Intent intent = new Intent(this, NewPoi.class);
            startActivityForResult(intent, 0);

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

}

