package com.example.pendaftaranpesertaujian.pendaftaran;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendaftaranpesertaujian.R;
import com.example.pendaftaranpesertaujian.adapter.Adapter_instansi;
import com.example.pendaftaranpesertaujian.adapter.Adapter_instansi_lihat;
import com.example.pendaftaranpesertaujian.model.Instansi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Rv_instansi_lihat extends AppCompatActivity {
    private static Activity activity;
    private DatabaseReference database_instansi;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter_instansi_lihat;
    private RecyclerView.LayoutManager layoutManager_instansi;
    private ArrayList<Instansi> daftar_instansi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_instansi);

        recyclerView = findViewById(R.id.Rv_instansi);
        recyclerView.setHasFixedSize(true);
        layoutManager_instansi = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager_instansi);

        database_instansi = FirebaseDatabase.getInstance().getReference();

        database_instansi.child("Admin Instansi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                daftar_instansi = new ArrayList<>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Instansi instansi = dataSnapshot1.getValue(Instansi.class);
                    instansi.setKey(dataSnapshot1.getKey());
                    daftar_instansi.add(instansi);

                    adapter_instansi_lihat = new Adapter_instansi_lihat(daftar_instansi, Rv_instansi_lihat.this);
                    recyclerView.setAdapter(adapter_instansi_lihat);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        Rv_instansi_lihat.activity = activity;
        return new Intent(activity, Rv_instansi_lihat.class);

    }

}
