package com.example.police;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    LinearLayout ll;
    TextView tv,tv1,tff;
    Button b1;

    TestAdapter adapter;
    EditText uname,upass;
   // com.example.police.TestAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv=(TextView)findViewById(R.id.txt_adminaccounntd);
        b1=(Button)findViewById(R.id.btn_loginnn);
        uname=(EditText)findViewById(R.id.txt_uname);
        upass=(EditText)findViewById(R.id.txt_password);
        tv1=(TextView)findViewById(R.id.password_linkk);

        final RadioGroup radioGrp = new RadioGroup(this);
        RadioButton radioButton = new RadioButton(this);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login.this,ForgetPassword.class);
                startActivity(i);
            }
        });
        try {
            adapter = new TestAdapter(this);
            adapter.createDatabase();
            adapter.open();

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(Login.this,Signin.class);
                    startActivity(i);
                }
            });


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


                   int i = adapter.checkUserLoginn(username, upassword);
                    if (i == 1) {

                      userlogin();
                        /*Intent intent = new Intent(Login.this, com.example.police.FreindHomepage.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();*/
                        return;

                    }


                    else {

                        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(Login.this);

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



    private void userlogin() {
        final ProgressDialog dialog =
                new ProgressDialog(Login.this);
        dialog.setIcon(R.drawable.alert);
        dialog.setTitle("Login");
        dialog.setMessage("Please wait User Login is Processing...");
        dialog.show();

        final Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
                String mpass = null;

                Intent i = new Intent(Login.this, FreindHomepage.class);
                startActivity(i);

                Toast.makeText(getApplicationContext(), "Your Login is Successful..." , Toast.LENGTH_SHORT).show();
                uname.setText("");
                upass.setText("");
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 6000);
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


