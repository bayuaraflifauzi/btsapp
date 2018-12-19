package com.bekasideveloper.btsapp.wrapper.output;

public class AjuanWrapper {
    private String email;
    private String npwpd;
    private String namaPerusahaan;
    private String namaPendaftar;
    private String alamatPerusahaan;
    private String noTelp;

    private String ajuanId;
    private String idKecamatan;
    private String kecamatan;
    private String longitudeAjuan;
    private String latitudeAjuan;
    private Long tanggalDibuatMilis;
    private Integer status;

    public AjuanWrapper() {
    }

    public AjuanWrapper(
            String email,
            String npwpd,
            String namaPerusahaan,
            String namaPendaftar,
            String alamatPerusahaan,
            String noTelp,
            String ajuanId,
            String idKecamatan,
            String kecamatan,
            String longitudeAjuan,
            String latitudeAjuan,
            Long tanggalDibuatMilis,
            Integer status) {
        this.email = email;
        this.npwpd = npwpd;
        this.namaPerusahaan = namaPerusahaan;
        this.namaPendaftar = namaPendaftar;
        this.alamatPerusahaan = alamatPerusahaan;
        this.noTelp = noTelp;
        this.ajuanId = ajuanId;
        this.idKecamatan = idKecamatan;
        this.kecamatan = kecamatan;
        this.longitudeAjuan = longitudeAjuan;
        this.latitudeAjuan = latitudeAjuan;
        this.tanggalDibuatMilis = tanggalDibuatMilis;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNpwpd() {
        return npwpd;
    }

    public void setNpwpd(String npwpd) {
        this.npwpd = npwpd;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getNamaPendaftar() {
        return namaPendaftar;
    }

    public void setNamaPendaftar(String namaPendaftar) {
        this.namaPendaftar = namaPendaftar;
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

    public String getAjuanId() {
        return ajuanId;
    }

    public void setAjuanId(String ajuanId) {
        this.ajuanId = ajuanId;
    }

    public String getIdKecamatan() {
        return idKecamatan;
    }

    public void setIdKecamatan(String idKecamatan) {
        this.idKecamatan = idKecamatan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getLongitudeAjuan() {
        return longitudeAjuan;
    }

    public void setLongitudeAjuan(String longitudeAjuan) {
        this.longitudeAjuan = longitudeAjuan;
    }

    public String getLatitudeAjuan() {
        return latitudeAjuan;
    }

    public void setLatitudeAjuan(String latitudeAjuan) {
        this.latitudeAjuan = latitudeAjuan;
    }

    public Long getTanggalDibuatMilis() {
        return tanggalDibuatMilis;
    }

    public void setTanggalDibuatMilis(Long tanggalDibuatMilis) {
        this.tanggalDibuatMilis = tanggalDibuatMilis;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
