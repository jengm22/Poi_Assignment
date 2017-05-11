package com.example.a1jengm22.poi_assignment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends Activity
{

    MapView mv;

    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;

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

        if (item.getItemId() == R.id.preferences)
        {
            // react to the menu item being selected...
            // System.exit(0); this code closes the app

            Intent intent = new Intent(this, Preferences.class);
            startActivityForResult(intent, 1);

            return true;
        }
       return false;

    }

    public void onStart()
    {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean autoupload = prefs.getBoolean("autoupload", true);
        //String pizzaCode = prefs.getString("pizza", "NONE");

        if (autoupload == true)
        {
            //upload after doing task 6 and 7
        }


        // do something with the preference data...
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {

        if(resultCode == RESULT_OK)
        {

            if (requestCode == 0)
            {
                Bundle extras=intent.getExtras();
                String name = extras.getString("com.example.a1jengm22.poiName");
                String type = extras.getString("com.example.a1jengm22.poiType");
                String desc = extras.getString("com.example.a1jengm22.poiDesc");

                double latitude = mv.getMapCenter().getLatitude();
                double longitude = mv.getMapCenter().getLongitude();

                //to link listener to the overlay layer
                items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), markerGestureListener);
                OverlayItem banjul = new OverlayItem(name, type, desc, new GeoPoint(latitude, longitude));

                items.addItem(banjul);
                mv.getOverlays().add(items);

                //saving the poi added

                try {
                    PrintWriter pw =
                            new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/data.txt",true));


                    for(int i=0;  i<items.size(); i++)
                    {
                        OverlayItem place = items.getItem(i);
                        pw.println(place.getTitle()+","+ place.getSnippet()+ ","+ place.getPoint().getLatitude()+ ","+ place.getPoint().getLongitude());
                    }



                    pw.close(); // close the file to ensure data is flushed to file
                } catch (IOException e) {
                    new AlertDialog.Builder(this).setMessage("ERROR: " + e).
                            setPositiveButton("OK", null).show();
                }

            }

            if(requestCode==1)
            {

                if (resultCode==RESULT_OK)
                {
                    Bundle extras=intent.getExtras();
                    boolean preferences = extras.getBoolean("com.example.a1jengm22.preferences");

                }
            }

        }

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

}

