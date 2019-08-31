package com.example.mayank.medidatanew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Setup1Activity extends AppCompatActivity {


    private EditText age,gender,address,occupation,bloodgroup;
    private Button btnForSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup1);

        age =(EditText)findViewById(R.id.input_age);
        gender =(EditText)findViewById(R.id.input_gender);
        address =(EditText)findViewById(R.id.input_add);
        occupation =(EditText)findViewById(R.id.input_occ);
        bloodgroup =(EditText)findViewById(R.id.input_bg);

        btnForSave=(Button)findViewById(R.id.btn_submit);


    }
}
