package com.example.police;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Signin extends AppCompatActivity {
EditText no,name,add,mobile,adhar,pass,ans;
Spinner sp1,sp2;
Button b1;
TestAdapter adapter;

String sno,sname,sadd,smobile,sgen,sadhar,quest,answer,spass;
    ArrayAdapter<String> ad;
    List<String> list;
    String[] courses = {"Male", "Female"};
    List<String> list1;
    TextView tv1;
    ArrayAdapter<String> arrayAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        sp2=(Spinner)findViewById(R.id.questions);
        ans=(EditText)findViewById(R.id.txt_answer);
        no=(EditText) findViewById(R.id.txt_userid);
        name=(EditText) findViewById(R.id.txt_username);
        add=(EditText) findViewById(R.id.txt_useraddress);
        mobile=(EditText) findViewById(R.id.txt_usermobile);
        sp1=(Spinner)findViewById(R.id.spin_gend);
        adhar=(EditText) findViewById(R.id.txt_useradhar);
        pass=(EditText) findViewById(R.id.txt_userpass);
        b1=(Button) findViewById(R.id.btn_user);


        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(ad);

        list1 = new ArrayList<String>();
        list1.add("Select Security question");
        list1.add("My first school name? ");
        list1.add("My pet name?");
        list1.add("My first laptop company name?");
        list1.add("Birth Place?");

        arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(arrayAdapter1);



        try {
            adapter = new TestAdapter(this);
            adapter.createDatabase();
            adapter.open();

            autoincerment();

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // sno,sname,sadd,smobile,sgen,sadhar,spass
                  //  no,name,add,sp1,mobile,adhar,pass
                    sno=no.getText().toString();
                    sname=name.getText().toString();
                    sadd=add.getText().toString();
                    sgen=sp1.getSelectedItem().toString();
                    smobile=mobile.getText().toString();
                    quest=sp2.getSelectedItem().toString();
                    answer=ans.getText().toString();
                    sadhar=adhar.getText().toString();
                    spass=pass.getText().toString();


                    String mpass = null;
                    Cursor cursor = adapter.selectuser();
                    while (cursor.moveToNext()) {
                        mpass = cursor.getString(4).toString();
                        if (smobile.equalsIgnoreCase(mpass)) {

                            Toast.makeText(getApplicationContext(), "This User is Already Registered..!", Toast.LENGTH_SHORT).show();
                            Log.w("5", "ok");
                            return;
                        }
                    }


                    if (TextUtils.isEmpty(sname)) {
                        Toast.makeText(getApplicationContext(), "Please Enter User Name..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(sadd)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Address..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(smobile)) {
                        Toast.makeText(getApplicationContext(), "Please Enter  Mobile Number..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (smobile.length() != 10) {
                        Toast.makeText(getApplicationContext(), "Please Enter 10 Digit Mobile Number.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(sadhar)) {
                        Toast.makeText(getApplicationContext(), "Please Enter User Aadhar Number..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(answer)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Questions Answer..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(spass)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Password..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        RegisterUser();
                    }


                }
            });
        }catch (Exception e){}

    }

    private void autoincerment() {
        String number = " ";
        int no1;
        int flag = 0;
        Cursor cursor1 = adapter.selectuser();
        //cursor1.moveToFirst();
        while (cursor1.moveToNext()) {
            flag = 1;
        }
        cursor1.close();
        //setting id into edit text
        if (flag == 1) {
            try {
                Cursor cursor2 = adapter.userincrementid();
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

    private void RegisterUser() {
        final ProgressDialog dialog =
                new ProgressDialog(Signin.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Processing Your Request...");
        dialog.setMessage("Please wait...");
        dialog.show();

        long i = adapter.Insertuser(sno,sname,sadd,smobile,sgen,sadhar,quest,answer,spass);
        Toast.makeText(getApplicationContext(), "User Register Successfully..." + i, Toast.LENGTH_SHORT).show();

        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;

                final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.
                        Builder(Signin.this);
                // alertDialogBuilder.setIcon(R.drawable.ic_credit_card_black_24dp);
                alertDialogBuilder.setTitle(" User Register Successfully..");
                // alertDialogBuilder.setMessage("No Any Card Added ..");
                final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.setPositiveButton("OK.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent i = new Intent(Signin.this, Login.class);
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
