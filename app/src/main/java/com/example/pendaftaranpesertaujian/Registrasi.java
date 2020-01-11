package com.example.pendaftaranpesertaujian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pendaftaranpesertaujian.model.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registrasi extends AppCompatActivity {
   private EditText nama, username,password;
   private Button btnregister;
    private FirebaseAuth mAuth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        nama = findViewById(R.id.edtregisnama);
        username = findViewById(R.id.edtregisusername);
        password = findViewById(R.id.edtregispassword);
        btnregister = findViewById(R.id.btnregistrasi);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        registerUser();





    }
    private void registerawal(){
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Snama = nama.getText().toString();
                String Susername = username.getText().toString();
                String Spassword = password.getText().toString();
                if (Snama.isEmpty() || Susername.isEmpty() || Spassword.isEmpty()) {
                    Toast.makeText(Registrasi.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {

                    //create user dengan firebase auth
                    mAuth.createUserWithEmailAndPassword(Susername,Spassword)
                            .addOnCompleteListener(Registrasi.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //jika gagal register do something
                                    if (!task.isSuccessful()){
                                        Toast.makeText(Registrasi.this,
                                                "Register gagal karena "+ task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }else {
                                        //jika sukses akan menuju ke login activity
                                        startActivity(new Intent(Registrasi.this,Login.class));
                                    }

                                    // ...

                                }

                            });


                }
            }
        });



    }

    private void registerUser() {
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //menampung imputan user
                String emailUser = username.getText().toString().trim();
                String passwordUser = password.getText().toString().trim();

                //validasi email dan password
                // jika email kosong
                if (emailUser.isEmpty()){
                    username.setError("Email tidak boleh kosong");
                }
                // jika email not valid
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()){
                    username.setError("Email tidak valid");
                }
                // jika password kosong
                else if (passwordUser.isEmpty()){
                    password.setError("Password tidak boleh kosong");
                }
                //jika password kurang dari 6 karakter
                else if (passwordUser.length() < 6){
                    password.setError("Password minimal terdiri dari 6 karakter");
                }
                else {
                    //create user dengan firebase auth
                    mAuth.createUserWithEmailAndPassword(emailUser,passwordUser)
                            .addOnCompleteListener(Registrasi.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //jika gagal register do something
                                    if (!task.isSuccessful()){
                                        Toast.makeText(Registrasi.this,
                                                "Register gagal karena "+ task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }else {

                                        simpan(new Register(nama.getText().toString(),username.getText().toString(),password.getText().toString()));
                                        //jika sukses akan menuju ke login activity
                                        startActivity(new Intent(Registrasi.this,Login.class));
                                    }
                                }
                            });
                }
            }
        });
    }

    private void simpan(Register register) {

        database.child("Registrasi").push().setValue(register).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                nama.setText("");
                username.setText("");
                password.setText("");
                Snackbar.make(findViewById(R.id.btnregistrasi), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }




}
