package com.example.pendaftaranpesertaujian.informasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pendaftaranpesertaujian.R;
import com.example.pendaftaranpesertaujian.adapter.Adapter_informasi;
import com.example.pendaftaranpesertaujian.model.Informasi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Rv_informasi extends AppCompatActivity {
    private DatabaseReference database_informasi;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter_informasi;
    private RecyclerView.LayoutManager layoutManager_informasi;
    private ArrayList<Informasi> daftar_informasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_informasi);
        recyclerView = findViewById(R.id.rv_informasi);
        recyclerView.setHasFixedSize(true);
        layoutManager_informasi = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager_informasi);

        database_informasi = FirebaseDatabase.getInstance().getReference();
        database_informasi.child("Admin Informasi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                daftar_informasi = new ArrayList<>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Informasi informasi = dataSnapshot1.getValue(Informasi.class);
                    informasi.setKey(dataSnapshot1.getKey());
                    daftar_informasi.add(informasi);

                    adapter_informasi = new Adapter_informasi(daftar_informasi, Rv_informasi.this);
                    recyclerView.setAdapter(adapter_informasi);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });
    }
}
