package com.example.pendaftaranpesertaujian.model;
import java.io.Serializable;

public class Register implements Serializable {
    private String nama;
    private String email;
    private String password;

    public Register(String nama, String email, String password) {
        this.nama = nama;
        this.email = email;
        this.password = password;
    }

    public Register() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
