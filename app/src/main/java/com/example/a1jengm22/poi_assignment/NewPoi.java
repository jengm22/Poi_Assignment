package com.example.a1jengm22.poi_assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class NewPoi extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poi);

        Button add = (Button) findViewById(R.id.btn1);
        add.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        boolean addNewPoi=false;
        if (v.getId()==R.id.btn1)
        {
            addNewPoi=true;
        }
        bundle.putBoolean("com.example.newPoi",addNewPoi);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}
