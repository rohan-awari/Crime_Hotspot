package com.example.police;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddCriminal extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
EditText caseid,casetype,ddate,crname,add,pname,age,mobile,pstationno,fno,aname,fyear,section,desc;
Spinner sp1;
TestAdapter adapter;
Button b1;
    ArrayAdapter<String> ad;
    List<String> list;
    String[] courses = {"Male", "Female"};
    String pcaseid,pcasetype,pddate,pcrname,cadd,cpname,gender,page,pmobile,ppstationno,pfno,paname,pfyear,psection,cdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_criminal);
        ddate=(EditText) findViewById(R.id.txt_ccdate);
        caseid=(EditText)findViewById(R.id.txt_caseid);
        casetype=(EditText)findViewById(R.id.txt_ctype);
        crname=(EditText)findViewById(R.id.txt_criminalname);
        add=(EditText)findViewById(R.id.txt_criminaladd);
        pname=(EditText)findViewById(R.id.txt_criminalparent);
        age=(EditText)findViewById(R.id.txt_crage);
        mobile=(EditText)findViewById(R.id.txt_crmobile);
        pstationno=(EditText)findViewById(R.id.txt_policstation);
        fno=(EditText)findViewById(R.id.txt_firno);
        aname=(EditText)findViewById(R.id.txt_apname);
        fyear=(EditText)findViewById(R.id.txt_firyear);
        section=(EditText)findViewById(R.id.txt_crsection);
        sp1=(Spinner)findViewById(R.id.spin_gender);
        b1=(Button)findViewById(R.id.btn_crregister);
        desc=(EditText)findViewById(R.id.txt_criminaldesc);


        Bundle bundle = getIntent().getExtras();
        String namestudent = bundle.getString("Key");
        crname.setText(namestudent);


        String date_n = new SimpleDateFormat("MM/dd/yy", Locale.getDefault()).format(new Date());
        ddate.setText(date_n);

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(ad);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        ddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddCriminal.this,
                        date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.
                        get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        try {
            adapter = new TestAdapter(this);
            adapter.createDatabase();
            adapter.open();
            autoincriment();

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //caseid,casetype,ddate,crname,age,mobile,pstationno,fno,aname,fyear,section
                pcaseid=caseid.getText().toString();
                pcasetype=casetype.getText().toString();
                pddate=ddate.getText().toString();
                pcrname=crname.getText().toString();
                cadd=add.getText().toString();
                cpname=pname.getText().toString();
                gender=sp1.getSelectedItem().toString();
                page=age.getText().toString();
                pmobile=mobile.getText().toString();
                ppstationno=pstationno.getText().toString();
                pfno=fno.getText().toString();
                paname=aname.getText().toString();
                pfyear=fyear.getText().toString();
                psection=section.getText().toString();
                cdesc=desc.getText().toString();


                    if (TextUtils.isEmpty(pcasetype)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Case Type..", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if (TextUtils.isEmpty(pddate)){
                        Toast.makeText(getApplicationContext(), "Please Enter Case date..", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if (TextUtils.isEmpty(pcrname)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Criminal name..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(cdesc)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Case Description..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(cpname)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Criminal Parent name..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(cadd)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Criminal Address..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(page)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Criminal Age..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty( ppstationno)) {
                        Toast.makeText(getApplicationContext(), "Please Fill All Details..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(pmobile)) {
                        Toast.makeText(getApplicationContext(), "Please Enter  Mobile Number..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (mobile.length() != 10) {
                        Toast.makeText(getApplicationContext(), "Please Enter 10 Digit Mobile Number.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(pfno)) {
                        Toast.makeText(getApplicationContext(), "Please Enter FIR Number..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(pfyear)) {
                        Toast.makeText(getApplicationContext(), "Please Enter FIR Year..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(psection)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Criminal Section..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        addCriminal();
                    }
                }

            });

        }catch (Exception e){}



    }

    private void autoincriment() {
        String number = " ";
        int no1;
        int flag = 0;
        Cursor cursor1 = adapter.selectCriminal();
        //cursor1.moveToFirst();
        while (cursor1.moveToNext()) {
            flag = 1;
        }
        cursor1.close();
        //setting id into edit text
        if (flag == 1) {
            try {
                Cursor cursor2 = adapter.criminalincrementid();
                while (cursor2.moveToNext()) {
                    if (cursor2.getString(0) != null)
                        number = cursor2.getString(0);

                }
                int n = Integer.parseInt(number);
                no1 = n + 1;
                caseid.setText("" + no1);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private void addCriminal() {

        final ProgressDialog dialog =
                new ProgressDialog(AddCriminal.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Processing Your Request..");
        dialog.setMessage("Please wait...");
        dialog.show();

        long i = adapter.InsertCriminal(pcaseid,pcasetype,pddate,pcrname,cadd,cpname,gender,page,pmobile
                ,ppstationno,pfno,paname,pfyear,psection,cdesc);
      //  Toast.makeText(getApplicationContext(), "Criminal Register Sucessfully..." + i, Toast.LENGTH_SHORT).show();

        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;

                final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(AddCriminal.this);
                // alertDialogBuilder.setIcon(R.drawable.ic_credit_card_black_24dp);
                alertDialogBuilder.setTitle("Criminal Save Sucessfully..");
                // alertDialogBuilder.setMessage("No Any Card Added ..");
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.setPositiveButton("Ok.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent i = new Intent(AddCriminal.this, PoliceHomePage.class);
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




    private void updateLabel() {
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        ddate.setText(dateFormat.format(myCalendar.getTime()));
    }
}