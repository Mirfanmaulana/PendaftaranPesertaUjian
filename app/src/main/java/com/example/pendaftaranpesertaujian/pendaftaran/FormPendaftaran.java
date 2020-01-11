package com.example.pendaftaranpesertaujian.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pendaftaranpesertaujian.R;
import com.example.pendaftaranpesertaujian.model.Form_Pendaftaran;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormPendaftaran extends AppCompatActivity {
    private DatabaseReference database;
    private EditText instansi,unit,sabuk,nama,ttl,alamat,pekerjaan,umur,bb;
    private Button simpan;
    private String Sinstansi,Sunit,Ssabuk,Snama,Sttl,Salamat,Spekerjaan,Sumur,Sbb;
    private String keyInstansi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pendaftaran);
        instansi = findViewById(R.id.instansi);
        unit = findViewById(R.id.unit);
        sabuk = findViewById(R.id.sabuk);
        nama = findViewById(R.id.nama);
        ttl = findViewById(R.id.ttl);
        alamat = findViewById(R.id.alamat);
        pekerjaan = findViewById(R.id.pekerjaan);
        umur = findViewById(R.id.umur);
        bb = findViewById(R.id.beratbadan);

        keyInstansi = getIntent().getStringExtra("key");

        simpan = findViewById(R.id.btn_simpan);

        database = FirebaseDatabase.getInstance().getReference();



        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Sinstansi = instansi.getText().toString();
                 Sunit = unit.getText().toString();
                 Ssabuk =sabuk.getText().toString();
                 Snama = nama.getText().toString();
                 Sttl = ttl.getText().toString();
                 Salamat =alamat.getText().toString();
                 Spekerjaan = pekerjaan.getText().toString();
                 Sumur = umur.getText().toString();
                 Sbb = bb.getText().toString();

                 if (Sinstansi.isEmpty() ||Sunit.isEmpty() || Ssabuk.isEmpty() || Snama.isEmpty() || Sttl.isEmpty() || Salamat.isEmpty() || Spekerjaan.isEmpty()
                    || Sumur.isEmpty() || Sbb.isEmpty()){
                     Toast.makeText(FormPendaftaran.this,"Data Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                 }else{

                     simpan(new Form_Pendaftaran(instansi.getText().toString(),unit.getText().toString(),sabuk.getText().toString(),nama.getText().toString(),ttl.getText().toString(),
                             alamat.getText().toString(),pekerjaan.getText().toString(),umur.getText().toString(),bb.getText().toString(),"Belum Dikonfirmasi"));

                 }

            }
        });
    }

    private void simpan(Form_Pendaftaran form_pendaftaran) {

        database.child("data_pendaftaran").child(keyInstansi).push().setValue(form_pendaftaran).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                instansi.setText("");
                unit.setText("");
                sabuk.setText("");
                nama.setText("");
                ttl.setText("");
                alamat.setText("");
                pekerjaan.setText("");
                umur.setText("");
                bb.setText("");
                Snackbar.make(findViewById(R.id.btn_simpan), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();


            }
        });
    }


}
