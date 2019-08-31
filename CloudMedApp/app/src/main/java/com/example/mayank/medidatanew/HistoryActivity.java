package com.example.mayank.medidatanew;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class HistoryActivity extends AppCompatActivity {



    private EditText allergy,cd,po,fr;
    private Button btn_sub;
    private FirebaseAuth mAuth;
    private DatabaseReference UserRef;
    String CurentUserId;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mAuth=FirebaseAuth.getInstance();
        CurentUserId =mAuth.getCurrentUser().getUid();
        UserRef = FirebaseDatabase.getInstance().getReference().child("MediData").child(CurentUserId);




        allergy =(EditText)findViewById(R.id.al);
        cd =(EditText)findViewById(R.id.cd);
        po =(EditText)findViewById(R.id.po);
        fr =(EditText)findViewById(R.id.fr);

        btn_sub=(Button)findViewById(R.id.btn_su);



        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaveAccountSetupInformation();
            }
        });


    }

    private void SaveAccountSetupInformation() {





        String all = allergy.getText().toString();
        String cdd = cd.getText().toString();
        String poo = po.getText().toString();
        String frr = fr.getText().toString();




        if (TextUtils.isEmpty(all))

        {

            Toast.makeText(this, "Please write your allergy", Toast.LENGTH_SHORT).show();


        }



        else if (TextUtils.isEmpty(cdd))


        {


            Toast.makeText(this, "Please write your cd ", Toast.LENGTH_SHORT).show();


        }


        else if (TextUtils.isEmpty(poo))


        {


            Toast.makeText(this, "Please write your Previous Operation ", Toast.LENGTH_SHORT).show();


        }


        else if (TextUtils.isEmpty(frr))


        {


            Toast.makeText(this, "Please write your fracture", Toast.LENGTH_SHORT).show();


        }







        else

        {


            HashMap usermap = new HashMap();
            usermap.put("allergy",all);
            usermap.put("Chrononical Disease",cdd);
            usermap.put("Previous Operation",poo);
            usermap.put("Fracture",frr);



            UserRef.updateChildren(usermap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if (task.isSuccessful())


                    {

                        Toast.makeText(HistoryActivity.this,"your Account is crreated Succesfully",Toast.LENGTH_LONG).show();
                        SendUserToMainActivity();


                    }


                    else {

                        String message = task.getException().getMessage();
                        Toast.makeText(HistoryActivity.this,"Error"+message,Toast.LENGTH_LONG).show();

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
