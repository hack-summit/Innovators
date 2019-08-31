package com.example.mayank.medidatanew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendDataTextActivity extends AppCompatActivity {



    EditText txt;
    Button btn;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_text);

    txt=findViewById(R.id.data);
    btn=findViewById(R.id.btn);
    databaseReference =db.getReference("Data");


    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sendData();
        }
    });


    }

public void sendData()


{


    String dataFieldText=txt.getText().toString();
    String id =databaseReference.push().getKey();
    if(!TextUtils.isEmpty(dataFieldText))

    {

        Data data = new Data(dataFieldText,id);

        databaseReference.child(id).setValue(data);

        Toast.makeText(this,"Data has been send",Toast.LENGTH_SHORT).show();


    }
else

    {

        Toast.makeText(this,"Please enter the data",Toast.LENGTH_SHORT).show();

    }

}


}



