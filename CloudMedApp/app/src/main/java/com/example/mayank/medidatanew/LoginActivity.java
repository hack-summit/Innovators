package com.example.mayank.medidatanew;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private Button loginBtn;
    private EditText UserEmail,UserPass;
    private TextView NeedNewAccount;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth =FirebaseAuth.getInstance();

        loginBtn=(Button)findViewById(R.id.btn_signup);
        UserEmail=(EditText)findViewById(R.id.input_email);
        UserPass=(EditText)findViewById(R.id.input_password);
        NeedNewAccount=(TextView)findViewById(R.id.link_login);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                AllowUserToLgin();
                
            }
        });


        NeedNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                
                SendUserToSignUpActivity();

             }
        });


    }

    private void SendUserToSignUpActivity() {



        Intent sendtoSignUpActivity=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(sendtoSignUpActivity);
        sendtoSignUpActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        finish();

    }





    @Override
    protected void onStart() {
        super.onStart();



        FirebaseUser currentUser = mAuth.getCurrentUser();






        if(currentUser!=null)





        {

            SendUserToMainActivity();





        }







    }

    private void AllowUserToLgin() {

        String email = UserEmail.getText().toString();
        String password = UserPass.getText().toString();




        if (TextUtils.isEmpty(email))

        {

            Toast.makeText(this, "Please write your email ", Toast.LENGTH_SHORT).show();


        }



        else if (TextUtils.isEmpty(password))


        {


            Toast.makeText(this, "Please write your password ", Toast.LENGTH_SHORT).show();


        }


        else

        {

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful())


                        {

                            SendUserToMainActivity();

                        }

                    }
                });

        }




    }

    private void SendUserToMainActivity() {

        Intent sendtoMainActivity=new Intent(this,MainActivity.class);
        startActivity(sendtoMainActivity);
        sendtoMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        finish();
    }






}
