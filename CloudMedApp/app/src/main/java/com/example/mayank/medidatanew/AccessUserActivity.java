package com.example.mayank.medidatanew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccessUserActivity extends AppCompatActivity {
    TextView a,b,c;
    Button btn;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_user);

        a=(TextView)findViewById(R.id.one);
        b=(TextView)findViewById(R.id.two);
        c=(TextView)findViewById(R.id.three);
        btn=(Button) findViewById(R.id.btnn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reff=FirebaseDatabase.getInstance().getReference().child("MediData").child("DemzblGmTFPF0bNL74r9qHe0WRq1");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String Ch =dataSnapshot.child("Chroninical Disease").getValue().toString();
                        String fr =dataSnapshot.child("Fracture").getValue().toString();
                        String po =dataSnapshot.child("Previous Operation").getValue().toString();

                        a.setText(Ch);
                        b.setText(fr);
                        c.setText(po);

                        


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


    }
}
