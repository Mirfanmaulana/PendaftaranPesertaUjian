package com.example.pendaftaranpesertaujian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendaftaranpesertaujian.R;
import com.example.pendaftaranpesertaujian.model.Form_Pendaftaran;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Adapter_form_pendaftaran extends RecyclerView.Adapter<Adapter_form_pendaftaran.Form_PendaftaranViewHolder>  {
    private ArrayList<Form_Pendaftaran> datalist;
    private Context context;


    public Adapter_form_pendaftaran(ArrayList<Form_Pendaftaran> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;

    }

    @Override
    public Adapter_form_pendaftaran.Form_PendaftaranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_peserta,parent,false);
        Form_PendaftaranViewHolder vh = new Form_PendaftaranViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_form_pendaftaran.Form_PendaftaranViewHolder holder, int position) {
        final String Sinstansi = datalist.get(position).getInstansi();
        final String Snama = datalist.get(position).getNama();
        final String SSabuk = datalist.get(position).getSabuk();
        final String Sbb = datalist.get(position).getBb();
        final String Skonfirmasi = datalist.get(position).getStatus();



        holder.instansi.setText(Sinstansi);
        holder.nama.setText(Snama);
        holder.sabuk.setText(SSabuk);
        holder.bb.setText(Sbb);
        holder.konfirmasi.setText(Skonfirmasi);


    }

    @Override
    public int getItemCount() {
        return datalist.size();

    }

    public class Form_PendaftaranViewHolder extends RecyclerView.ViewHolder {
        private TextView instansi, nama, sabuk,bb,konfirmasi;
        public Form_PendaftaranViewHolder(@NonNull View itemView) {
            super(itemView);
            instansi = itemView.findViewById(R.id.txt_instansi);
            nama = itemView.findViewById(R.id.txt_peserta_nama);
            sabuk = itemView.findViewById(R.id.txt_sabuk);
            bb = itemView.findViewById(R.id.txt_bb);
            konfirmasi = itemView.findViewById(R.id.konfirmasi);

        }
    }
}
