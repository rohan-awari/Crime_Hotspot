package com.example.police;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class FindCriminal extends AppCompatActivity {
    TestAdapter adapter;
    TextView vno,vdtae,name,station,accus,cadd,comp,add,panem,age,adhar,contact,rls,txtactive,txtcomplete,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_criminal);
        vno=(TextView) findViewById(R.id.txt_vcnocs);
        vdtae=(TextView) findViewById(R.id.txt_vcdatecs);
        name=(TextView) findViewById(R.id.txt_vcnamecs);
        station=(TextView) findViewById(R.id.txt_vcstationcs);
        accus=(TextView) findViewById(R.id.txt_vcaccusecs);
        cadd=(TextView)findViewById(R.id.txt_vcsadds);
        comp=(TextView) findViewById(R.id.txt_vccomplaintcs);
        add=(TextView) findViewById(R.id.txt_vcaddrsscs);
        age=(TextView) findViewById(R.id.txt_vcagecs);
        panem=(TextView)findViewById(R.id.txt_vcparents);
        adhar=(TextView) findViewById(R.id.txt_vcadharnocs);
        contact=(TextView) findViewById(R.id.txt_vccontactcs);
        rls=(TextView) findViewById(R.id.txt_vcrealtionshipcs);
        desc=(TextView) findViewById(R.id.txt_vcdescs);



        try {
            adapter = new TestAdapter(this);
            adapter.createDatabase();
            adapter.open();

            Intent intent = getIntent();
            String str = intent.getStringExtra("Key");
            Cursor cursor = adapter.getAllCriminalbyyname(str);
            while (cursor.moveToNext()) {
                vno.setText(cursor.getString(0));
                vdtae.setText(cursor.getString(1));
                name.setText(cursor.getString(2));
                panem.setText(cursor.getString(5));
                cadd.setText(cursor.getString(4));
                station.setText(cursor.getString(3));
                accus.setText(cursor.getString(6));
                comp.setText(cursor.getString(7));
                add.setText(cursor.getString(8));
                age.setText(cursor.getString(9));
                adhar.setText(cursor.getString(10));
                contact.setText(cursor.getString(12));
                rls.setText(cursor.getString(13));
                desc.setText(cursor.getString(14));
            }

        }catch (Exception e){}


    }
}