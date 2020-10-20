package com.jry.rudyjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private Button btnsave;
    private EditText binput ;
    private TextView mBrand ;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onStart() {
        super.onStart();

        mFirestore.collection("Car").document("one").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@NonNull DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                String brand = documentSnapshot.getString("brand");
                mBrand.setText("แสดงข้อมูล : " + brand);

            }
        });
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFirestore = FirebaseFirestore.getInstance();
        mBrand = (TextView) findViewById(R.id.tvShowbrand);
        btnsave = (Button) findViewById(R.id.btnsave);
        binput = (EditText) findViewById(R.id.edtinputband);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFirestore.collection("Car").document("one").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(HomeActivity.this, "เพิ่มข้อมูลสำเร็จ:" , Toast.LENGTH_LONG).show();

                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists() && documentSnapshot != null) {
                                String brand = documentSnapshot.getString("brand");
                                mBrand.setText("แสดงข้อมูล : " + brand);
                            }
                        } else {
                            Toast.makeText(HomeActivity.this, "เพิ่มข้อมูลไม่สำเร็จ:" , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand = binput.getText().toString();
                Map<String, String> dataToAdd = new HashMap<>();
                dataToAdd.put("brand", brand);
                mFirestore.collection("Car").document("one").set(dataToAdd).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HomeActivity.this, "เพิ่มข้อมูลไม่สำเร็จ:" + e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }
}