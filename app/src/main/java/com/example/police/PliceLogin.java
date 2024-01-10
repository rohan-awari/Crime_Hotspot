package com.example.police;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PliceLogin extends AppCompatActivity {
    LinearLayout ll;
    TextView tv,tff;
    Button b1;

    TestAdapter adapter;
    EditText uname,upass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plice_login);

      //  tv=(TextView)findViewById(R.id.txt_padminaccounntd);
        b1=(Button)findViewById(R.id.btn_ploginnn);
        uname=(EditText)findViewById(R.id.txt_puname);
        upass=(EditText)findViewById(R.id.txt_ppassword);

        try {
            adapter = new TestAdapter(this);
            adapter.createDatabase();
            adapter.open();




            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username, upassword;
                    username = uname.getText().toString();
                    upassword = upass.getText().toString();

                    if (TextUtils.isEmpty(username)) {
                        uname.setError("Enter Mobile Number");
                    }
                    if (TextUtils.isEmpty(upassword)) {
                        upass.setError("Enter Password");
                    }
                    if (username.equalsIgnoreCase("7249860824")&&(upassword.equalsIgnoreCase("12345"))){

                        policelogin();
                    }
                    else {

                        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(PliceLogin.this);

                        alertDialogBuilder.setTitle("Invalid User Name Or Password..");

                        final android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialogBuilder.setPositiveButton("Please Try Again.",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                    }
                                });
                        android.app.AlertDialog alDialog = alertDialogBuilder.create();
                        alDialog.show();


                    }
                    uname.setText("");
                    upass.setText("");


                }


            });
        }
        catch (Exception e){}


    }

    private void policelogin() {
        final ProgressDialog dialog =
                new ProgressDialog(PliceLogin.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Login");
        dialog.setMessage("Please wait Police Login is Processing...");
        dialog.show();

        final Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;
                Intent i = new Intent(PliceLogin.this, PoliceHomePage.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Your Login is Successful..." , Toast.LENGTH_SHORT).show();

                uname.setText("");
                upass.setText("");

            }
        };


        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 6000);

    }



}