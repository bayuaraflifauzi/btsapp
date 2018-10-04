package com.bekasideveloper.btsapp.wrapper.output;

public class KecamatanWrapper {
    private String idKecamatan;

    private String namaKecamatan;

    public KecamatanWrapper() {
    }

    public KecamatanWrapper(String idKecamatan, String namaKecamatan) {
        this.idKecamatan = idKecamatan;
        this.namaKecamatan = namaKecamatan;
    }

    public String getIdKecamatan() {
        return idKecamatan;
    }

    public void setIdKecamatan(String idKecamatan) {
        this.idKecamatan = idKecamatan;
    }

    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
    }
}
