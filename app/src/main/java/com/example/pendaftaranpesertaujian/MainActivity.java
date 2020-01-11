package com.example.pendaftaranpesertaujian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pendaftaranpesertaujian.informasi.Rv_informasi;
import com.example.pendaftaranpesertaujian.pendaftaran.RV_Peserta;
import com.example.pendaftaranpesertaujian.pendaftaran.Rv_instansi;
import com.example.pendaftaranpesertaujian.pendaftaran.Rv_instansi_lihat;

public class MainActivity extends AppCompatActivity {
    private Button datapeserta,informasi,penjadwalan,lihatdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datapeserta = findViewById(R.id.btn_datapeserta);
        informasi = findViewById(R.id.btn_informasi);
        penjadwalan = findViewById(R.id.btn_penjadwalan);
        lihatdata = findViewById(R.id.btn_lihatdata);

        datapeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, Rv_instansi.class);
                startActivity(intent);
            }
        });
        informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, Rv_informasi.class);
                startActivity(intent);
            }
        });
        lihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, Rv_instansi_lihat.class);
                startActivity(intent);
            }
        });

    }
}
