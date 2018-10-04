package com.bekasideveloper.btsapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "kecamatan")
public class Kecamatan {

    @Id
    @Column(name = "ID_KECAMATAN")
    private String idKecamatan;

    @Column(name = "NAMA_KECAMATAN")
    private String namaJenisBarang;

    @OneToMany(mappedBy = "daftarCellBts")
    public List<CellBts> daftarCellBts;

    public String getIdKecamatan() {
        return idKecamatan;
    }

    public void setIdKecamatan(String idKecamatan) {
        this.idKecamatan = idKecamatan;
    }

    public String getNamaJenisBarang() {
        return namaJenisBarang;
    }

    public void setNamaJenisBarang(String namaJenisBarang) {
        this.namaJenisBarang = namaJenisBarang;
    }

    public List<CellBts> getDaftarCellBts() {
        return daftarCellBts;
    }

    public void setDaftarCellBts(List<CellBts> daftarCellBts) {
        this.daftarCellBts = daftarCellBts;
    }
}
