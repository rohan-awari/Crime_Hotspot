package com.example.police;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {
Button user,police;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        user=(Button) findViewById(R.id.btn_userlogin);
        police=(Button)findViewById(R.id.btn_policelogin);




        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ChooseActivity.this,Login.class);
                startActivity(i);
            }
        });

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ChooseActivity.this,PliceLogin.class);
                startActivity(i);
            }
        });
    }
}