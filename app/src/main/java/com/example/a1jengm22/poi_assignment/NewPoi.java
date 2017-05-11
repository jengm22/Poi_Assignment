package com.example.a1jengm22.poi_assignment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewPoi extends Activity implements View.OnClickListener
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
        EditText poiName = (EditText)findViewById(R.id.poiName);
        EditText poiType = (EditText)findViewById(R.id.poiType);
        EditText poiDesc = (EditText)findViewById(R.id.poiDesc);

        String name = poiName.getText().toString();
        String type = poiType.getText().toString();
        String desc = poiDesc.getText().toString();


        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        boolean addNewPoi=false;
        if (v.getId()==R.id.btn1)
        {
            addNewPoi=true;
        }
        bundle.putString("com.example.a1jengm22.poiName",name);
        bundle.putString("com.example.a1jengm22.poiType",type);
        bundle.putString("com.example.a1jengm22.poiDesc",desc);

        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}
