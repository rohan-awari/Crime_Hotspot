package com.example.police;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FreindHomepage extends AppCompatActivity {
Button b1,criminalimg,crime,logout,status,crimeee,facereg;
TestAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freind_homepage);
        b1=(Button) findViewById(R.id.btn_copmreg);
        facereg=(Button)findViewById(R.id.btn_facerecognization);
        criminalimg=(Button)findViewById(R.id.btn_apiiiii);
        crime=(Button)findViewById(R.id.btn_criminaldetails);
        logout=(Button)findViewById(R.id.btn_friendlogout);
        status=(Button)findViewById(R.id.btn_compsts);
        crimeee=(Button) findViewById(R.id.btn_prediction);



        try {
            adapter = new TestAdapter(this);
            adapter.createDatabase();
            adapter.open();

            facereg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(FreindHomepage.this,CriminalFaceRecognization.class);
                    startActivity(i);
                }
            });

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(FreindHomepage.this, ComplaintRegister.class);
                    startActivity(i);
                }
            });

            crimeee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(FreindHomepage.this, PredictionActivity.class);
                    startActivity(i);
                }
            });

            criminalimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(FreindHomepage.this, ViewCriminal.class);
                    startActivity(i);

                }
            });

            crime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(FreindHomepage.this, CrimeImage.class);
                    startActivity(i);
                }
            });

            status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(FreindHomepage.this, StatuShow.class);
                    startActivity(i);
                }
            });

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(FreindHomepage.this);
                    alertDialogBuilder.setIcon(R.drawable.alert);
                    alertDialogBuilder.setTitle("Do you want to logout..?");
                    // alertDialogBuilder.setMessage("No Any Card Added ..");
                    final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialogBuilder.setPositiveButton("Yes.",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    finish();
                                    System.exit(0);

                                }
                            });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(FreindHomepage.this, Login.class);
                            startActivity(i);
                        }
                    });
                    android.app.AlertDialog alDialog = alertDialogBuilder.create();
                    alDialog.show();

                }


            });
        }catch (Exception e){}
    }
    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}