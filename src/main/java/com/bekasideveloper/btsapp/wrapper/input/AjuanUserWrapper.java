package com.bekasideveloper.btsapp.wrapper.input;

public class AjuanUserWrapper {
    private String userId;
    private String idKecamatan;
    private String kecamatan;
    private String latitude;
    private String longitude;
    private String namaFileNPWPD;
    private String namaDokumenAjuan;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNamaFileNPWPD() {
        return namaFileNPWPD;
    }

    public void setNamaFileNPWPD(String namaFileNPWPD) {
        this.namaFileNPWPD = namaFileNPWPD;
    }

    public String getNamaDokumenAjuan() {
        return namaDokumenAjuan;
    }

    public void setNamaDokumenAjuan(String namaDokumenAjuan) {
        this.namaDokumenAjuan = namaDokumenAjuan;
    }
}
