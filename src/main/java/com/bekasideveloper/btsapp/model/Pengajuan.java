package com.bekasideveloper.btsapp.model;

import javax.persistence.*;

@Entity
@Table(name = "pengajuan")
public class Pengajuan {
    @EmbeddedId
    private PengajuanId pengajuanId;
    @Column(name = "scan_npwpd_file", columnDefinition = "TEXT")
    private String scanNpwpdFile;
    @Column(name = "dokumen_pengajuan", columnDefinition = "TEXT")
    private String dokumenPengajuan;

    @Column(name = "id_kecamatan")
    private String idKecamatan;
    @Column(name = "kecamatan")
    private String kecamatan;
    @Column(name = "latitude_ajuan")
    private String latitudeAjuan;
    @Column(name = "longitude_ajuan")
    private String longitudeAjuan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            insertable = false,
            updatable = false,
            referencedColumnName = "user_id")
    private User user;

    public Pengajuan() {
    }

    public Pengajuan(PengajuanId pengajuanId, String scanNpwpdFile, String dokumenPengajuan, String idKecamatan, String kecamatan, String latitudeAjuan, String longitudeAjuan, User user) {
        this.pengajuanId = pengajuanId;
        this.scanNpwpdFile = scanNpwpdFile;
        this.dokumenPengajuan = dokumenPengajuan;
        this.idKecamatan = idKecamatan;
        this.kecamatan = kecamatan;
        this.latitudeAjuan = latitudeAjuan;
        this.longitudeAjuan = longitudeAjuan;
        this.user = user;
    }

    public PengajuanId getPengajuanId() {
        return pengajuanId;
    }

    public void setPengajuanId(PengajuanId pengajuanId) {
        this.pengajuanId = pengajuanId;
    }

    public String getScanNpwpdFile() {
        return scanNpwpdFile;
    }

    public void setScanNpwpdFile(String scanNpwpdFile) {
        this.scanNpwpdFile = scanNpwpdFile;
    }

    public String getDokumenPengajuan() {
        return dokumenPengajuan;
    }

    public void setDokumenPengajuan(String dokumenPengajuan) {
        this.dokumenPengajuan = dokumenPengajuan;
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

    public String getLatitudeAjuan() {
        return latitudeAjuan;
    }

    public void setLatitudeAjuan(String latitudeAjuan) {
        this.latitudeAjuan = latitudeAjuan;
    }

    public String getLongitudeAjuan() {
        return longitudeAjuan;
    }

    public void setLongitudeAjuan(String longitudeAjuan) {
        this.longitudeAjuan = longitudeAjuan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
