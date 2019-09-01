package com.example.mayank.medidatanew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorIDActivity extends AppCompatActivity {


    private EditText DID;
    private Button button;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_id);



        DID=(EditText)findViewById(R.id.did);
        button=(Button) findViewById(R.id.butt);


        databaseReference =db.getReference("Doctor Id");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });




    }



    public void sendData()


    {


        String dataFieldText=DID.getText().toString();
        String id =databaseReference.push().getKey();
        if(!TextUtils.isEmpty(dataFieldText))

        {

            Data data = new Data(dataFieldText,id);

            databaseReference.child(id).setValue(data);

            Toast.makeText(this,"Data has been send",Toast.LENGTH_SHORT).show();

            sendToAccessData();


        }
        else

        {

            Toast.makeText(this,"Please enter the data",Toast.LENGTH_SHORT).show();

        }

    }



    public void sendToAccessData()
    {


        Intent sendtoAcessUser=new Intent(DoctorIDActivity.this,AccessUserActivity.class);
        startActivity(sendtoAcessUser);
        finish();



    }


}
