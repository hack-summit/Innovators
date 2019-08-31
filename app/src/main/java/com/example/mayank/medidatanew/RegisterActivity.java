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

public class RegisterActivity extends AppCompatActivity {


    private Button SinUpBtn;
    private EditText Username,UserEmail,UserPass,ConfirmPass;
    private TextView AlreadyHave;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth=FirebaseAuth.getInstance();

        SinUpBtn=(Button)findViewById(R.id.btn_signup);
        UserEmail=(EditText)findViewById(R.id.input_email);
        UserPass=(EditText)findViewById(R.id.input_password);
        ConfirmPass=(EditText)findViewById(R.id.input_confirmpassword);

        AlreadyHave=(TextView)findViewById(R.id.link_login);

        SinUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });



    }

    private void CreateAccount() {
        
        String email = UserEmail.getText().toString();
        String password = UserPass.getText().toString();
        String Confermpassword = ConfirmPass.getText().toString();


        if (TextUtils.isEmpty(email))
            
        {

            Toast.makeText(this, "Please write your email ", Toast.LENGTH_SHORT).show();


        }



        else if (TextUtils.isEmpty(password))


        {


            Toast.makeText(this, "Please write your password ", Toast.LENGTH_SHORT).show();


        }



        else if (TextUtils.isEmpty(Confermpassword))


        {


            Toast.makeText(this, "Please write your conferm password ", Toast.LENGTH_SHORT).show();


        }


        else if (!password.equals(Confermpassword))


        {


            Toast.makeText(this, "Conferm password does not match with Password", Toast.LENGTH_SHORT).show();


        }



        else
        {

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful())


                        {
                            
                            SendUserToOTPLoginActivity();

                            Toast.makeText(RegisterActivity.this, "You are authenticated", Toast.LENGTH_SHORT).show();


                        }



                        else
                        {

                        String message=task.getException().getMessage();
                        Toast.makeText(RegisterActivity.this, "Error Occured"+message, Toast.LENGTH_SHORT).show();




                        }

                    }
                });
        }


    }


    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser==null)


        {
            SendUserToLoginActivity();


        }




    }

    private void SendUserToLoginActivity() {



            Intent sendtoMainActivity=new Intent(this,MainActivity.class);
            startActivity(sendtoMainActivity);
            sendtoMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            finish();




    }


    private void SendUserToOTPLoginActivity() {

        Intent sendtoOTP=new Intent(RegisterActivity.this,LoginOtpActivity.class);
        sendtoOTP.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(sendtoOTP);
        finish();


    }
}
