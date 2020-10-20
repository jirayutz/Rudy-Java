package com.jry.rudyjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView tvShow;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvShow = (TextView) findViewById(R.id.tvShow);
        btnOK = (Button) findViewById(R.id.btnOK);
        Intent intent = getIntent();
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Useruid = intent.getStringExtra("UserUid");
                tvShow.setText(Useruid);
            }
        });
    }
}