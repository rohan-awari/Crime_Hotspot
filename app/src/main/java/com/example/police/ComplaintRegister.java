package com.example.police;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ComplaintRegister extends AppCompatActivity {
    EditText date, no, cname, caccused, ccomplaint, cadd, cage, cadhar, cmobile, crlsn;
    Spinner sp1;
    Button b1;
    ArrayAdapter<String> ad;
    List<String> list;
    String[] courses = {"Hadapsar", "Swargate",
            "Shivaji nagar", "Katraj",
            "Malwadi", "Loni Kalbhor"};
    String num, cdate, name, station, assuced, complaint, address, age, adhar, mobile, relation,
            sact,scomp;
    TestAdapter adapter;
    TextView active,complt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_register);

        no = (EditText) findViewById(R.id.txt_cid);
        cname = (EditText) findViewById(R.id.txt_cname);
        date = (EditText) findViewById(R.id.txt_cdate);
        sp1 = (Spinner) findViewById(R.id.spin_item);
        ccomplaint = (EditText) findViewById(R.id.txt_cdetails);
        cadd = (EditText) findViewById(R.id.txt_cadd);
        cage = (EditText) findViewById(R.id.txt_cage);
        cadhar = (EditText) findViewById(R.id.txt_cadhar);
        caccused = (EditText) findViewById(R.id.txt_caccsud);
        cmobile = (EditText) findViewById(R.id.txt_cmobile);
        crlsn = (EditText) findViewById(R.id.txt_crelation);
        active=(TextView)findViewById(R.id.txt_actives);
        complt=(TextView)findViewById(R.id.txt_completesss);

        b1 = (Button) findViewById(R.id.btn_register);

        String date_n = new SimpleDateFormat("MM, dd, yyyy", Locale.getDefault()).format(new Date());
        date.setText(date_n);

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(ad);

        try {
            adapter = new TestAdapter(this);
            adapter.createDatabase();
            adapter.open();

            autoincrementid();
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    num = no.getText().toString();
                    cdate = date.getText().toString();
                    name = cname.getText().toString();
                    station = sp1.getSelectedItem().toString();
                    assuced = caccused.getText().toString();
                    complaint = ccomplaint.getText().toString();
                    address = cadd.getText().toString();
                    age = cage.getText().toString();
                    adhar = cadhar.getText().toString();
                    mobile = cmobile.getText().toString();
                    relation = crlsn.getText().toString();
                    sact=active.getText().toString();
                   // scomp=complt.getText().toString();
                    if (TextUtils.isEmpty(name)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Complainant Name..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(assuced)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Accused Name..", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if (TextUtils.isEmpty(complaint)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Complainant Details..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(address)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Address..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(age)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Complainant Age..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(adhar)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Complainant Adhar Number..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (cadhar.length() != 12) {
                        Toast.makeText(getApplicationContext(), "Please Enter 12 Digit Aadhar  Number.", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if (TextUtils.isEmpty(mobile)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Complainant Mobile Number..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (cmobile.length() != 10) {
                        Toast.makeText(getApplicationContext(), "Please Enter 10 Digit Mobile Number.", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if (TextUtils.isEmpty(relation)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Complainant and Accused Relationship..", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        addComplaint();
                    }
                }


            });

            // EditText date,no,cname,caccused,ccomplaint,cadd,cage,cadhar,cmobile,crlsn;
            // String num,cdate,name,station,assuced,complaint,address,age,adhar,mobile,relation;


        } catch (Exception e) {
        }
    }

    private void autoincrementid() {

        String number = " ";
        int no1;
        int flag = 0;
        Cursor cursor1 = adapter.selectComplaint();
        //cursor1.moveToFirst();
        while (cursor1.moveToNext()) {
            flag = 1;
        }
        cursor1.close();
        //setting id into edit text
        if (flag == 1) {
            try {
                Cursor cursor2 = adapter.complaintincrementid();
                while (cursor2.moveToNext()) {
                    if (cursor2.getString(0) != null)
                        number = cursor2.getString(0);

                }
                int n = Integer.parseInt(number);
                no1 = n + 1;
                no.setText("" + no1);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        }

    private void addComplaint() {
        final ProgressDialog dialog =
                new ProgressDialog(ComplaintRegister.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Processing Your Complaint...");
        dialog.setMessage("Please wait...");
        dialog.show();

        long i = adapter.InsertComplaint(num, cdate, name, station, assuced, complaint, address, age, adhar, mobile, relation,
                sact,scomp);
        Toast.makeText(getApplicationContext(), "Complaint Register Sucessfully..." + i, Toast.LENGTH_SHORT).show();

        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;

                final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(ComplaintRegister.this);
                // alertDialogBuilder.setIcon(R.drawable.ic_credit_card_black_24dp);
                alertDialogBuilder.setTitle("Your Complaint is Register Sucessfully..");
                // alertDialogBuilder.setMessage("No Any Card Added ..");
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.setPositiveButton("Ok.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent i = new Intent(ComplaintRegister.this, FreindHomepage.class);
                                startActivity(i);

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


   /*     private void addComplaint() {
            final ProgressDialog dialog =
                    new ProgressDialog(ComplaintRegister.this);
            dialog.setIcon(R.drawable.alert);
            dialog.setTitle("Processing Your Complaint...");
            dialog.setMessage("Please wait Your Complaint is Processing To Register...");
            dialog.show();

            //  long i = adapter.InsertOrder(oid, odate, fname, orate, oqty, ototal);
            //Toast.makeText(getApplicationContext(), "Data Save Sucessfully..." + i, Toast.LENGTH_SHORT).show();

            final Runnable progressRunnable = new Runnable() {

                @Override
                public void run() {
                    dialog.cancel();
                    String mpass = null;

                    final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(Addorder.this);
                    // alertDialogBuilder.setIcon(R.drawable.ic_credit_card_black_24dp);
                    alertDialogBuilder.setTitle("Your Complaint is Register Sucessfully..");
                    // alertDialogBuilder.setMessage("No Any Card Added ..");
                    final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialogBuilder.setPositiveButton("Ok.",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    // Intent i = new Intent(Addorder.this, UserHome.class);
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

    });

}*/





