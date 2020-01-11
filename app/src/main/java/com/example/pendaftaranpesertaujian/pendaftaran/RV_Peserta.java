package com.example.pendaftaranpesertaujian.pendaftaran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pendaftaranpesertaujian.R;
import com.example.pendaftaranpesertaujian.adapter.Adapter_form_pendaftaran;
import com.example.pendaftaranpesertaujian.adapter.Adapter_instansi;
import com.example.pendaftaranpesertaujian.model.Form_Pendaftaran;
import com.example.pendaftaranpesertaujian.model.Instansi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RV_Peserta extends AppCompatActivity {
    private static Activity activity;
    private DatabaseReference database;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Form_Pendaftaran> daftar_pendaftar;
    private String keyInstansi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv__peserta);

        keyInstansi = getIntent().getStringExtra("key");

        recyclerView = findViewById(R.id.Rv_peserta);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("data_pendaftaran").child(keyInstansi).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                daftar_pendaftar = new ArrayList<>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Form_Pendaftaran pendaftar = dataSnapshot1.getValue(Form_Pendaftaran.class);
                    pendaftar.setKey(dataSnapshot1.getKey());
                    daftar_pendaftar.add(pendaftar);

                    adapter = new Adapter_form_pendaftaran(daftar_pendaftar, RV_Peserta.this);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        RV_Peserta.activity = activity;
        return new Intent(activity, Rv_instansi.class);

    }





    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.form_pendaftaran,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.form_pendaftaran :
                Intent intent = new Intent(RV_Peserta.this,FormPendaftaran .class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
