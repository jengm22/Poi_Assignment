package com.example.a1jengm22.poi_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by 1jengm22 on 11/05/2017.
 */
public class Preferences extends PreferenceActivity implements View.OnClickListener
{
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    public void onClick(View v)
    {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        boolean preferences=false;

        bundle.putBoolean("com.example.a1jengm22.preferences",preferences);

        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}
