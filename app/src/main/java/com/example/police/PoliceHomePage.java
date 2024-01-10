package com.example.police;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PoliceHomePage extends AppCompatActivity {
Button criminal,crimimg,csts,logout,viewcriminal,face;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_home_page);
        criminal=(Button) findViewById(R.id.btn_addcriminal);
        crimimg=(Button)findViewById(R.id.btn_addco);
        csts=(Button)findViewById(R.id.btn_complaintsts);
        logout=(Button)findViewById(R.id.btn_policelogout);
        viewcriminal=(Button)findViewById(R.id.btn_viewcriminal);
     //   face=(Button)findViewById(R.id.btn_facerecog);

        viewcriminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PoliceHomePage.this,CriminalModule.class);
                startActivity(i);

            }
        });


     /*   face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PoliceHomePage.this,CriminalFaceRecognization.class);
                startActivity(i);
            }
        });*/

        criminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PoliceHomePage.this,CriminalFaceRecognization.class);
                startActivity(i);
            }
        });

        crimimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PoliceHomePage.this,CrimeImage.class);
                startActivity(i);

            }
        });

        csts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PoliceHomePage.this,Complaintsts.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(PoliceHomePage.this);
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
                                Intent i=new Intent(PoliceHomePage.this,Login.class);
                                startActivity(i);
                            }
                        });
                        android.app.AlertDialog alDialog = alertDialogBuilder.create();
                        alDialog.show();

                    }





        });
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