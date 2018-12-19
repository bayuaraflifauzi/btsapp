package com.bekasideveloper.btsapp.model;

import javax.persistence.*;

@Entity
@Table(name = "perusahaan")
public class Perusahaan {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "email")
    private String email;
    @Column(name = "nama_pendaftar")
    private String namaPendaftar;
    @Column(name = "nama_perusahaan")
    private String namaPerusahaan;
    @Column(name = "alamat_perusahaan", columnDefinition = "TEXT")
    private String alamatPerusahaan;
    @Column(name = "no_telp")
    private String noTelp;
    @Column(name = "npwpd")
    private String npwpd;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            insertable = false,
            updatable = false,
            referencedColumnName = "user_id")
    private User user;

    public Perusahaan() {
    }

    public Perusahaan(
            String userId,
            String email,
            String namaPendaftar,
            String namaPerusahaan,
            String alamatPerusahaan,
            String noTelp,
            String npwpd) {
        this.userId = userId;
        this.email = email;
        this.namaPendaftar = namaPendaftar;
        this.namaPerusahaan = namaPerusahaan;
        this.alamatPerusahaan = alamatPerusahaan;
        this.noTelp = noTelp;
        this.npwpd = npwpd;
    }

    public Perusahaan(
            String userId,
            String email,
            String namaPendaftar,
            String namaPerusahaan,
            String alamatPerusahaan,
            String noTelp,
            String npwpd,
            User user) {
        this.userId = userId;
        this.email = email;
        this.namaPendaftar = namaPendaftar;
        this.namaPerusahaan = namaPerusahaan;
        this.alamatPerusahaan = alamatPerusahaan;
        this.noTelp = noTelp;
        this.npwpd = npwpd;
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNamaPendaftar() {
        return namaPendaftar;
    }

    public void setNamaPendaftar(String namaPendaftar) {
        this.namaPendaftar = namaPendaftar;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getAlamatPerusahaan() {
        return alamatPerusahaan;
    }

    public void setAlamatPerusahaan(String alamatPerusahaan) {
        this.alamatPerusahaan = alamatPerusahaan;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getNpwpd() {
        return npwpd;
    }

    public void setNpwpd(String npwpd) {
        this.npwpd = npwpd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
