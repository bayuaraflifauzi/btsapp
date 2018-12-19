package com.bekasideveloper.btsapp.wrapper.output;

public class PerusahaanWrapper {
    private String email;
    private String namaPendaftar;
    private String namaPerusahaan;
    private String alamatPerusahaan;
    private String noTelp;
    private String npwpd;

    public PerusahaanWrapper() {
    }

    public PerusahaanWrapper(String email, String namaPendaftar, String namaPerusahaan, String alamatPerusahaan, String noTelp, String npwpd) {
        this.email = email;
        this.namaPendaftar = namaPendaftar;
        this.namaPerusahaan = namaPerusahaan;
        this.alamatPerusahaan = alamatPerusahaan;
        this.noTelp = noTelp;
        this.npwpd = npwpd;
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
}
