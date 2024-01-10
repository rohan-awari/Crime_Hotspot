package com.example.police;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Viewcriminaldetials extends AppCompatActivity {
    TestAdapter adapter;
    TextView vno,vdtae,name,station,accus,cadd,comp,add,panem,age,adhar,contact,rls,txtactive,txtcomplete,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcriminaldetaisl);
        vno=(TextView) findViewById(R.id.txt_vcnoc);
        vdtae=(TextView) findViewById(R.id.txt_vcdatec);
        name=(TextView) findViewById(R.id.txt_vcnamec);
        station=(TextView) findViewById(R.id.txt_vcstationc);
        accus=(TextView) findViewById(R.id.txt_vcaccusec);
        cadd=(TextView)findViewById(R.id.txt_vcsadd);
        comp=(TextView) findViewById(R.id.txt_vccomplaintc);
        add=(TextView) findViewById(R.id.txt_vcaddrssc);
        age=(TextView) findViewById(R.id.txt_vcagec);
        panem=(TextView)findViewById(R.id.txt_vcparent);
        adhar=(TextView) findViewById(R.id.txt_vcadharnoc);
        contact=(TextView) findViewById(R.id.txt_vccontactc);
        rls=(TextView) findViewById(R.id.txt_vcrealtionshipc);
        desc=(TextView) findViewById(R.id.txt_vcdesc);
        // layout=(TableLayout) findViewById(R.id.compalitlist);

        try {
            adapter = new TestAdapter(this);
            adapter.createDatabase();
            adapter.open();

            Intent intent = getIntent();
            String str = intent.getStringExtra("message_key");
            Cursor cursor = adapter.getAllCriminaldetails(str);
            while (cursor.moveToNext()) {
                vno=(TextView) findViewById(R.id.txt_vcnoc);
                vdtae=(TextView) findViewById(R.id.txt_vcdatec);
                name=(TextView) findViewById(R.id.txt_vcnamec);
                station=(TextView) findViewById(R.id.txt_vcstationc);
                accus=(TextView) findViewById(R.id.txt_vcaccusec);
                cadd=(TextView)findViewById(R.id.txt_vcsadd);
                comp=(TextView) findViewById(R.id.txt_vccomplaintc);
                add=(TextView) findViewById(R.id.txt_vcaddrssc);
                age=(TextView) findViewById(R.id.txt_vcagec);
                panem=(TextView)findViewById(R.id.txt_vcparent);
                adhar=(TextView) findViewById(R.id.txt_vcadharnoc);
                contact=(TextView) findViewById(R.id.txt_vccontactc);
                rls=(TextView) findViewById(R.id.txt_vcrealtionshipc);
                desc=(TextView) findViewById(R.id.txt_vcdesc);
            }

        }catch (Exception e){}

        }
}