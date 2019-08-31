package com.example.mayank.medidatanew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    private Button loginBtn;
    private EditText UserEmail,UserPass;
    private TextView NeedNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginBtn=(Button)findViewById(R.id.btn_signup);
        UserEmail=(EditText)findViewById(R.id.input_email);
        UserPass=(EditText)findViewById(R.id.input_password);
        NeedNewAccount=(TextView)findViewById(R.id.link_login);



        NeedNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                
                SendUserToRegisterActivity();

             }
        });


    }

    private void SendUserToRegisterActivity() {

        Intent sendtoRegister=new Intent(this,RegisterActivity.class);
        startActivity(sendtoRegister);


    }
}
