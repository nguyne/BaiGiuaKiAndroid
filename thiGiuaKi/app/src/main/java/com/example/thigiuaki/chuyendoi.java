package com.example.thigiuaki;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thigiuaki.sv.SinhVien;

public class chuyendoi extends AppCompatActivity {
    EditText txtMaSv, txtHoTen, txtSoTC;
    Button btnthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyendoi);
        anhxa();
        laydulieu();
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void laydulieu() {
        Intent intent = getIntent();
        SinhVien sv1 = (SinhVien) intent.getSerializableExtra("du lieu");
        //Toast.makeText(chuyendoi.this, sv1.getHoten(),Toast.LENGTH_SHORT).show();
        txtMaSv.setText(sv1.getMaSV());
        txtHoTen.setText(sv1.getHoTen());
        txtSoTC.setText(sv1.getSoTC());
    }
    public void anhxa(){
        txtMaSv=findViewById(R.id.ac_txtmaSv);
        txtHoTen= findViewById(R.id.ac_txthoTen);
        txtSoTC = findViewById(R.id.ac_txtsoTC);
        btnthoat = findViewById(R.id.ac_btnthoat);
    }
}