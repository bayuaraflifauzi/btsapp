package com.bekasideveloper.btsapp.model;

import javax.persistence.*;

@Entity
@Table(name = "cell_bts")
public class CellBts {

    @Column(name = "ID_CELL_BTS", unique = true)
    private String kodeCellBts;

    @Column(name = "RADIUS_CELL_BTS")
    private int radiusCellBts;

    @EmbeddedId
    private CellBtsId cellBtsId;

    @ManyToOne
    @JoinColumn(name = "ID_KECAMATAN")
    public Kecamatan kecamatan;

    public String getKodeCellBts() {
        return kodeCellBts;
    }

    public void setKodeCellBts(String kodeCellBts) {
        this.kodeCellBts = kodeCellBts;
    }

    public int getRadiusCellBts() {
        return radiusCellBts;
    }

    public void setRadiusCellBts(int radiusCellBts) {
        this.radiusCellBts = radiusCellBts;
    }

    public CellBtsId getCellBtsId() {
        return cellBtsId;
    }

    public void setCellBtsId(CellBtsId cellBtsId) {
        this.cellBtsId = cellBtsId;
    }

    public Kecamatan getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(Kecamatan kecamatan) {
        this.kecamatan = kecamatan;
    }
}
