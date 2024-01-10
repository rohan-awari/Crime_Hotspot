package com.example.police;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class
PredictionActivity extends AppCompatActivity {
Spinner sp1;
TestAdapter adapter;
    String[] courses = {"Select City","Hadapsar", "Swargate",
            "Shivaji nagar", "Katraj",
            "Malwadi", "Loni Kalbhor"};
    String city;
    ListView listView;
    EditText e1;

    // Define array adapter for ListView
    ArrayAdapter<String> myadapter;

    // Define array List for List View data
    ArrayList<String> mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);
        sp1=(Spinner) findViewById(R.id.spin_area);
        String namecity;
        e1=(EditText) findViewById(R.id.txt_crtrtr);
       ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(ad);

        try {
            adapter = new TestAdapter(this);
            adapter.createDatabase();
            adapter.open();

            //addcity();

            sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    city=sp1.getItemAtPosition(i).toString();
                if(i==1){
                    addhadpsarpridiction();
                }
                if(i==2){
                    addswraget();
                }
                if(i==3){
                    addshivajinager();
                }

                if(i==4){
                        addkatraj();
                }

                if(i==5){
                        addmalwadi();
                }
                if(i==6){
                        addmaloni();
                }



                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        }catch (Exception e){}


    }

    private void addmaloni() {
        final ProgressDialog dialog =
                new ProgressDialog(PredictionActivity.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Searching Crime..");
        dialog.setMessage("Please wait...");
        dialog.show();



        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;


                int i = adapter.checkpridictmaloni(city);

                int num,incremn;
                incremn=15;
                num=i+incremn;

                if (num<=10){

                    e1.setText("This area is unsafe for Bike Theft  60%" +city);
                    Toast.makeText(PredictionActivity.this, "This area is Bike Theft  60%", Toast.LENGTH_LONG).show();

                }
                else  {
                    e1.setText("This area is Safe.."+city);
                    Toast.makeText(PredictionActivity.this, "This area is Safe..", Toast.LENGTH_LONG).show();
                }


                /*final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(PredictionActivity.this);
                // alertDialogBuilder.setIcon(R.drawable.ic_credit_card_black_24dp);
                alertDialogBuilder.setTitle("This Area Is Safe..");
                // alertDialogBuilder.setMessage("No Any Card Added ..");
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.setPositiveButton("Ok.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                //Intent i = new Intent(PredictionActivity.this, PoliceHomePage.class);
                                //startActivity(i);

                            }
                        });
                android.app.AlertDialog alDialog = alertDialogBuilder.create();
                alDialog.show();*/



            }
        };


        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 6000);

    }

    private void addmalwadi() {
        final ProgressDialog dialog =
                new ProgressDialog(PredictionActivity.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Searching Crime..");
        dialog.setMessage("Please wait...");
        dialog.show();



        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;


                int i = adapter.checkpridictmalvadi(city);

                int num,incremn;
                incremn=15;
                num=i+incremn;

                if (num<=10){

                    e1.setText("This area is unsafe for Chain sneking  40%" +city);
                    Toast.makeText(PredictionActivity.this, "This area Chain sneking area 40%", Toast.LENGTH_LONG).show();

                }
                else  {
                    e1.setText("This area is Safe.."+city);
                    Toast.makeText(PredictionActivity.this, "This area is Safe..", Toast.LENGTH_LONG).show();
                }


                /*final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(PredictionActivity.this);
                // alertDialogBuilder.setIcon(R.drawable.ic_credit_card_black_24dp);
                alertDialogBuilder.setTitle("This Area Is Safe..");
                // alertDialogBuilder.setMessage("No Any Card Added ..");
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.setPositiveButton("Ok.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                //Intent i = new Intent(PredictionActivity.this, PoliceHomePage.class);
                                //startActivity(i);

                            }
                        });
                android.app.AlertDialog alDialog = alertDialogBuilder.create();
                alDialog.show();*/



            }
        };


        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 6000);

    }

    private void addkatraj() {
        final ProgressDialog dialog =
                new ProgressDialog(PredictionActivity.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Searching Crime..");
        dialog.setMessage("Please wait...");
        dialog.show();



        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;


                int i = adapter.checkpridictionnkatraj(city);

                int num,incremn;
                incremn=10;
                num=i+incremn;

                if (num<=15){

                    e1.setText("This area is unsafe foe Murder 70%" +city);
                    Toast.makeText(PredictionActivity.this, "This area is Murderd area", Toast.LENGTH_LONG).show();

                }
                else  {
                    e1.setText("This area is Safe.."+city);
                    Toast.makeText(PredictionActivity.this, "This area is Safe..", Toast.LENGTH_LONG).show();
                }


                /*final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(PredictionActivity.this);
                // alertDialogBuilder.setIcon(R.drawable.ic_credit_card_black_24dp);
                alertDialogBuilder.setTitle("This Area Is Safe..");
                // alertDialogBuilder.setMessage("No Any Card Added ..");
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.setPositiveButton("Ok.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                //Intent i = new Intent(PredictionActivity.this, PoliceHomePage.class);
                                //startActivity(i);

                            }
                        });
                android.app.AlertDialog alDialog = alertDialogBuilder.create();
                alDialog.show();*/



            }
        };


        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 6000);
    }





    private void addshivajinager() {
        final ProgressDialog dialog =
                new ProgressDialog(PredictionActivity.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Searching Crime..");
        dialog.setMessage("Please wait...");
        dialog.show();



        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;


                int i = adapter.checkpridictionnshivajinager(city);

                int num,incremn;
                incremn=15;
                num=i+incremn;

                if (num<=10){

                    e1.setText("This area is not Safe"+city);
                    Toast.makeText(PredictionActivity.this, "This area is Robbery area 20 to 50%", Toast.LENGTH_LONG).show();

                }
                else  {
                    e1.setText("This area is Safe.."+city);
                    Toast.makeText(PredictionActivity.this, "This area is Safe..", Toast.LENGTH_LONG).show();
                }


                /*final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(PredictionActivity.this);
                // alertDialogBuilder.setIcon(R.drawable.ic_credit_card_black_24dp);
                alertDialogBuilder.setTitle("This Area Is Safe..");
                // alertDialogBuilder.setMessage("No Any Card Added ..");
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.setPositiveButton("Ok.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                //Intent i = new Intent(PredictionActivity.this, PoliceHomePage.class);
                                //startActivity(i);

                            }
                        });
                android.app.AlertDialog alDialog = alertDialogBuilder.create();
                alDialog.show();*/



            }
        };


        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 6000);
    }





    private void addswraget() {

        final ProgressDialog dialog =
                new ProgressDialog(PredictionActivity.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Searching Crime..");
        dialog.setMessage("Please wait...");
        dialog.show();



        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;


                int i = adapter.checkpridictionnswrget(city);

                int num,incremn;
                incremn=10;
                num=i+incremn;

                if (num<=15){

                    e1.setText("This area is not Safe"+city);
                    Toast.makeText(PredictionActivity.this, "This area is not Safe", Toast.LENGTH_LONG).show();

                }
                else  {
                    e1.setText("This area is Safe.."+city);
                    Toast.makeText(PredictionActivity.this, "This area is  Safe..", Toast.LENGTH_LONG).show();
                }


                /*final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(PredictionActivity.this);
                // alertDialogBuilder.setIcon(R.drawable.ic_credit_card_black_24dp);
                alertDialogBuilder.setTitle("This Area Is Safe..");
                // alertDialogBuilder.setMessage("No Any Card Added ..");
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.setPositiveButton("Ok.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                //Intent i = new Intent(PredictionActivity.this, PoliceHomePage.class);
                                //startActivity(i);

                            }
                        });
                android.app.AlertDialog alDialog = alertDialogBuilder.create();
                alDialog.show();*/



            }
        };


        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 6000);
    }





    private void addcity() {
        Cursor c=adapter.selectCriminal();
        mylist=new ArrayList<String>();
        while(c.moveToNext())
        {

            mylist.add(c.getString(15).toString());

        }

        myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mylist);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(myadapter);

    }

    private void addhadpsarpridiction() {
        final ProgressDialog dialog =
                new ProgressDialog(PredictionActivity.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Searching Crime..");
        dialog.setMessage("Please wait...");
        dialog.show();



        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;


                        int i = adapter.checkpridictionn(city);

                             int num,incremn;
                             incremn=15;
                             num=i+incremn;

                            if (num<=10){

                                e1.setText("This area is Not Safe"+city);
                                Toast.makeText(PredictionActivity.this, "This area is not Safe", Toast.LENGTH_LONG).show();

                            }
                            else  {
                                e1.setText("This area is Safe.."+city);
                                Toast.makeText(PredictionActivity.this, "This area is Safe..", Toast.LENGTH_LONG).show();
                            }


                /*final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(PredictionActivity.this);
                // alertDialogBuilder.setIcon(R.drawable.ic_credit_card_black_24dp);
                alertDialogBuilder.setTitle("This Area Is Safe..");
                // alertDialogBuilder.setMessage("No Any Card Added ..");
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.setPositiveButton("Ok.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                //Intent i = new Intent(PredictionActivity.this, PoliceHomePage.class);
                                //startActivity(i);

                            }
                        });
                android.app.AlertDialog alDialog = alertDialogBuilder.create();
                alDialog.show();*/



            }
        };


        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 6000);
    }


}
