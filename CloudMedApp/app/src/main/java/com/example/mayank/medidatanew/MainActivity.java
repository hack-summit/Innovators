package com.example.mayank.medidatanew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private Button btn_logout;
    private Button btnData;

    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");


        btn_logout=findViewById(R.id.logout);
        btnData=findViewById(R.id.AddFile);



        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sendToDataPage();

            }
        });



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToRegisterActivity();
            }
        });


    }

    private void SendUserToRegisterActivity() {

        Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
        finish();




    }

    private void sendToDataPage() {



        sendToAddDataActivity();



    }

    private void sendToAddDataActivity() {



        Intent dataintent = new Intent(MainActivity.this,AddDataActivity.class);
        startActivity(dataintent);
        finish();





    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser==null)


        {
            SendUserToLoginActivity();


        }


        else

        {

            CheckUserExistence();

        }


    }

    private void CheckUserExistence() {

        final String current_user_id=mAuth.getCurrentUser().getUid();
        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (!dataSnapshot.hasChild(current_user_id))

                {

                   // SendUserToSetUpActivity();


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    private void SendUserToSetUpActivity() {

        Intent setupIntend = new Intent(MainActivity.this,Setup1Activity.class);
        setupIntend .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity(setupIntend);
        finish();



    }



    private void SendUserToLoginActivity() {

        Intent loginIntend = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(loginIntend);
        finish();


    }





}



