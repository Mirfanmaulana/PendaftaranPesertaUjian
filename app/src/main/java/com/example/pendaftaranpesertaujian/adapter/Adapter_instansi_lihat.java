package com.example.pendaftaranpesertaujian.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendaftaranpesertaujian.R;
import com.example.pendaftaranpesertaujian.model.Instansi;
import com.example.pendaftaranpesertaujian.pendaftaran.FormPendaftaran;
import com.example.pendaftaranpesertaujian.pendaftaran.RV_Peserta;
import com.example.pendaftaranpesertaujian.pendaftaran.Rv_instansi_lihat;

import java.util.ArrayList;

public class Adapter_instansi_lihat extends RecyclerView.Adapter<Adapter_instansi_lihat.InstansiViewHolder> {

    private ArrayList<Instansi> datalist;
    private Context context;

    public Adapter_instansi_lihat(ArrayList<Instansi> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public InstansiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_instansi_lihat,parent,false);
        InstansiViewHolder vh = new InstansiViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull InstansiViewHolder holder, int position) {
        final String Sinstansi = datalist.get(position).getInstansi();

        final String key = datalist.get(position).getKey();

        holder.card_instansi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), RV_Peserta.class);
                intent.putExtra("key", key);
                context.startActivity(intent);

            }
        });

        holder.instansi.setText(Sinstansi);


    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class InstansiViewHolder extends RecyclerView.ViewHolder {

        private TextView instansi;
        private CardView card_instansi;

        public InstansiViewHolder(@NonNull View itemView) {
            super(itemView);

            instansi = (TextView) itemView.findViewById(R.id.item_instansi);
            card_instansi = (CardView) itemView.findViewById(R.id.card_instansi);
        }
    }
}
