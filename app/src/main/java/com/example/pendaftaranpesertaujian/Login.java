package com.example.pendaftaranpesertaujian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button btnlogin,btnregis;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.edtusername);
        password = findViewById(R.id.edtpassword);
        btnlogin = findViewById(R.id.btnlogin);
        btnregis = findViewById(R.id.btnregister);
        auth = FirebaseAuth.getInstance();
        login();



        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registrasi.class);
                startActivity(intent);

            }
        });
    }

    private void login() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Susername = username.getText().toString();
                final String Spassword = password.getText().toString();

                if (Susername.isEmpty() || (Spassword.isEmpty())){
                    Toast.makeText(Login.this, "Data Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(Susername,Spassword).addOnCompleteListener(Login.this
                            , new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                        Toast.makeText(Login.this,"Gagal Login Karena Email Atau Password Salah",Toast.LENGTH_SHORT).show();
                                    }else{

                                        Toast.makeText(Login.this,"Berhasil Login",Toast.LENGTH_SHORT).show();

                                        Bundle bundle = new Bundle();
                                        //bundle.putString("email",Susername);
                                        //bundle.putString("pass",Spassword);
                                        startActivity(new Intent(Login.this, MainActivity.class).putExtra("emailpass", bundle));
                                        finish();



                                    }
                                }


                            });


                }

            }
        });
    }

}
