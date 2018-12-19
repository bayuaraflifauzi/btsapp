package com.bekasideveloper.btsapp.wrapper.output;

public class HistoryAjuanWrapper {
    private String ajuanId;
    private String idKecamatan;
    private String kecamatan;
    private String longitudeAjuan;
    private String latitudeAjuan;
    private Long tanggalDibuatMilis;
    private Integer status;

    public HistoryAjuanWrapper() {
    }
    public HistoryAjuanWrapper(String ajuanId, String idKecamatan, String kecamatan, String longitudeAjuan, String latitudeAjuan, Long tanggalDibuatMilis, Integer status) {
        this.ajuanId = ajuanId;
        this.idKecamatan = idKecamatan;
        this.kecamatan = kecamatan;
        this.longitudeAjuan = longitudeAjuan;
        this.latitudeAjuan = latitudeAjuan;
        this.tanggalDibuatMilis = tanggalDibuatMilis;
        this.status = status;
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
