package com.example.police;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewComplaint extends AppCompatActivity {
TableLayout layout;
TestAdapter adapter;
TextView vno,vdtae,name,station,accus,comp,add,age,adhar,contact,rls,txtactive,txtcomplete;
String svno,svdtae,sname,sstation,saccus,scomp,sadd,sage,sadhar,scontact,srls,textactive,textcomplete;
Button active,complete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaint);
        vno=(TextView) findViewById(R.id.txt_vcno);
        vdtae=(TextView) findViewById(R.id.txt_vcdate);
        name=(TextView) findViewById(R.id.txt_vcname);
        station=(TextView) findViewById(R.id.txt_vcstation);
        accus=(TextView) findViewById(R.id.txt_vcaccuse);
        comp=(TextView) findViewById(R.id.txt_vccomplaint);
        add=(TextView) findViewById(R.id.txt_vcaddrss);
        age=(TextView) findViewById(R.id.txt_vcage);
        adhar=(TextView) findViewById(R.id.txt_vcadharno);
        contact=(TextView) findViewById(R.id.txt_vccontact);
        rls=(TextView) findViewById(R.id.txt_vcrealtionship);
        // layout=(TableLayout) findViewById(R.id.compalitlist);
        active=(Button) findViewById(R.id.btn_active);
        complete=(Button) findViewById(R.id.btn_compliete);
        txtactive=(TextView) findViewById(R.id.txt_active);
        txtcomplete=(TextView) findViewById(R.id.txt_complete);

        try {
            adapter = new TestAdapter(this);
            adapter.createDatabase();
            adapter.open();

            Intent intent = getIntent();
            String str = intent.getStringExtra("message_key");
            Cursor cursor = adapter.getAllComplaintdetails(str);
            while (cursor.moveToNext()){
                vno.setText(cursor.getString(0));
                vdtae.setText(cursor.getString(1));
                name.setText(cursor.getString(2));
                station.setText(cursor.getString(3));
                accus.setText(cursor.getString(4));
                comp.setText(cursor.getString(5));
                add.setText(cursor.getString(6));
                age.setText(cursor.getString(7));
                adhar.setText(cursor.getString(8));
                contact.setText(cursor.getString(9));
                rls.setText(cursor.getString(10));
            }


            active.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    complete.setEnabled(false);
                   //svno,svdtae,sname,sstation,saccus,scomp,sadd,sage,sadhar,scontact,srls;
                 svno=vno.getText().toString();
               /*  svdtae=vdtae.getText().toString();
                 sname=name.getText().toString();
                 sstation=station.getText().toString();
                 saccus=accus.getText().toString();
                 scomp=comp.getText().toString();
                 sadd=add.getText().toString();
                 sage=age.getText().toString();
                 sadhar=adhar.getText().toString();
                 scontact=contact.getText().toString();
                 srls=rls.getText().toString();*/
                 textactive=txtactive.getText().toString();
                // textcomplete=txtcomplete.getText().toString();

                 addcomplaint();



                }
            });

            complete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    active.setEnabled(false);
                    svno=vno.getText().toString();
                   /* svdtae=vdtae.getText().toString();
                    sname=name.getText().toString();
                    sstation=station.getText().toString();
                    saccus=accus.getText().toString();
                    scomp=comp.getText().toString();
                    sadd=add.getText().toString();
                    sage=age.getText().toString();
                    sadhar=adhar.getText().toString();
                    scontact=contact.getText().toString();
                    srls=rls.getText().toString();
                    textactive=txtactive.getText().toString();*/
                    textcomplete=txtcomplete.getText().toString();

                   completecomplit();
                }
            });

        }catch (Exception e){}

    }

    private void completecomplit() {
        final ProgressDialog dialog =
                new ProgressDialog(ViewComplaint.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Processing Your Complaint...");
        dialog.setMessage("Please wait...");
        dialog.show();

        long i = adapter.InsertUpadteComplaintStatuscomplete(svno,textcomplete);
        Toast.makeText(getApplicationContext(), "Your Complaint is Register Sucessfully..." + i, Toast.LENGTH_SHORT).show();

        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;

                final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(ViewComplaint.this);
                // alertDialogBuilder.setIcon(R.drawable.ic_credit_card_black_24dp);
                alertDialogBuilder.setTitle("Your Complaint is Active Sucessfully..");
                // alertDialogBuilder.setMessage("No Any Card Added ..");
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.setPositiveButton("Ok.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                // Intent i = new Intent(ComplaintRegister.this, FreindHomepage.class);
                                //startActivity(i);

                            }
                        });
                android.app.AlertDialog alDialog = alertDialogBuilder.create();
                alDialog.show();

            }
        };


        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 6000);
    }



    private void addcomplaint() {
        final ProgressDialog dialog =
                new ProgressDialog(ViewComplaint.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Processing Your Complaint...");
        dialog.setMessage("Please wait...");
        dialog.show();

        long i = adapter.InsertUpadteComplaintStatus(svno,textactive);
       Toast.makeText(getApplicationContext(), "Your Complaint is Register Sucessfully..." + i, Toast.LENGTH_SHORT).show();

        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;

                final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(ViewComplaint.this);
                // alertDialogBuilder.setIcon(R.drawable.ic_credit_card_black_24dp);
                alertDialogBuilder.setTitle("Your Complaint is Active Sucessfully..");
                // alertDialogBuilder.setMessage("No Any Card Added ..");
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.setPositiveButton("Ok.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                               // Intent i = new Intent(ComplaintRegister.this, FreindHomepage.class);
                                //startActivity(i);

                            }
                        });
                android.app.AlertDialog alDialog = alertDialogBuilder.create();
                alDialog.show();

            }
        };


        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 6000);
    }

}