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


    private Button btn;

    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");


        btn=findViewById(R.id.Login);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });


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

                    SendUserToSetUpActivity();


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
        loginIntend.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity(loginIntend);
        finish();


    }

    public void openLoginActivity()

    {


        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);

    }




}



