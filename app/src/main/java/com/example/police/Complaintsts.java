package com.example.police;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Complaintsts extends AppCompatActivity {
    TestAdapter adapter;
    TableLayout layout;
    String compid;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaintsts);

        layout=(TableLayout) findViewById(R.id.abctable);



        try {
            adapter = new TestAdapter(this);
            adapter.createDatabase();
            adapter.open();


            TableRow row1 = new TableRow(this);
            TableLayout.LayoutParams lp2 = new TableLayout.LayoutParams(MATCH_PARENT
                    , MATCH_PARENT);
            lp2.setMargins(0, 30, 0, 0);
            TextView tv2 = new TextView(this);
            tv2.setText("No.");
            tv2.setTextSize(20);
            row1.addView(tv2);



            TextView tv3 = new TextView(this);
            tv3.setText("Name.");
            tv3.setTextSize(20);
            row1.addView(tv3);
            layout.addView(row1);

            layout.setStretchAllColumns(true);
            Cursor cursor = adapter.selectComplaint();
            int l=0;
            while (cursor.moveToNext()) {
                TableRow row2=new TableRow(this);
                row2.setMinimumHeight(50);
                if(l%2==0)
                    row2.setBackgroundColor(getResources().getColor(R.color.gry));
                else
                    row2.setBackgroundColor(getResources().getColor(R.color.lgry));
                row2.getBackground().setAlpha(40);

                final TextView oid = new TextView(this);
                oid.setText(cursor.getString(0));
                compid = (cursor.getString(0));
                oid.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                oid.setTextColor(Color.BLACK);
                oid.setPadding(0, 0, 0, 0);


                final TextView name = new TextView(this);
                name.setText(cursor.getString(2));
                String idd = (cursor.getString(0));
                name.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                name.setTextColor(Color.BLACK);

                name.setPadding(0, 0, 0, 0);
                row2.addView(oid);
                row2.addView(name);
                row2.setLayoutParams(lp2);
                layout.addView(row2);
                l++;
                row2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor cc = adapter.getAllComplaintdetails(compid);
                        while (cc.moveToNext()) {
                            Intent intent = new Intent(getApplicationContext(), ViewComplaint.class);

                            // now by putExtra method put the value in key, value pair
                            // key is message_key by this key we will receive the value, and put the string

                            intent.putExtra("message_key", idd);

                            // start the Intent
                            startActivity(intent);


                        }
                    };


                });


            }

        }catch (Exception e){}


    }
}