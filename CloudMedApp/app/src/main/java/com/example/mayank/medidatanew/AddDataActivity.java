package com.example.mayank.medidatanew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddDataActivity extends AppCompatActivity {


    private Button btn_his;
    private Button btn_report;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);


        btn_his=(Button)findViewById(R.id.History);
        btn_report=(Button)findViewById(R.id.Reports);



        btn_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToHistoryData();

            }
        });




        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendToPicUpload();

            }
        });




    }

    private void sendToPicUpload() {

        Intent sendtoPicUpload=new Intent(this,PicUploadActivity.class);
        startActivity(sendtoPicUpload);
        finish();


    }

    private void sendToHistoryData() {


        Intent sendtoHistory=new Intent(this,HistoryActivity.class);
        startActivity(sendtoHistory);
        finish();


    }


}
