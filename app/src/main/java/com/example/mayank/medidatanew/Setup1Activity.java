package com.example.mayank.medidatanew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Setup1Activity extends AppCompatActivity {


    private EditText name,age,gender,address,occupation,bloodgroup;
    private Button btnForSave;
    private FirebaseAuth mAuth;
    private DatabaseReference UserRef;
    String CurentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup1);




        mAuth=FirebaseAuth.getInstance();
        CurentUserId =mAuth.getCurrentUser().getUid();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(CurentUserId);


        name =(EditText)findViewById(R.id.input_name);
        age =(EditText)findViewById(R.id.input_age);
        gender =(EditText)findViewById(R.id.input_gender);
        address =(EditText)findViewById(R.id.input_add);
        occupation =(EditText)findViewById(R.id.input_occ);
        bloodgroup =(EditText)findViewById(R.id.input_bg);

        btnForSave=(Button)findViewById(R.id.btn_submit);



        btnForSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaveAccountSetupInformation();
            }
        });


    }

    private void SaveAccountSetupInformation() {


        String Name = name.getText().toString();
        String Age = age.getText().toString();
        String Gender = gender.getText().toString();
        String Address = address.getText().toString();
        String Occupation = occupation.getText().toString();
        String BG = bloodgroup.getText().toString();



        if (TextUtils.isEmpty(Name))

        {

            Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show();


        }



        else if (TextUtils.isEmpty(Age))


        {


            Toast.makeText(this, "Please write your age ", Toast.LENGTH_SHORT).show();


        }


        else if (TextUtils.isEmpty(Gender))


        {


            Toast.makeText(this, "Please write your gender ", Toast.LENGTH_SHORT).show();


        }


        else if (TextUtils.isEmpty(Address))


        {


            Toast.makeText(this, "Please write your address", Toast.LENGTH_SHORT).show();


        }


        else if (TextUtils.isEmpty(Occupation))


        {


            Toast.makeText(this, "Please write your occupation ", Toast.LENGTH_SHORT).show();


        }


        else if (TextUtils.isEmpty(BG))


        {


            Toast.makeText(this, "Please write your Blood Grouup ", Toast.LENGTH_SHORT).show();


        }




        else

        {


            HashMap usermap = new HashMap();
            


        }











    }
}
