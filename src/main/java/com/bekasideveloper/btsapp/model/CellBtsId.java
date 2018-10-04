package com.bekasideveloper.btsapp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CellBtsId implements Serializable {

    @Column(name = "LAT_CELL_BTS")
    private String latCellBts;

    @Column(name = "LONG_CELL_BTS")
    private String longCellBts;

    public CellBtsId() {
    }

    public CellBtsId(String latCellBts, String longCellBts) {
        this.latCellBts = latCellBts;
        this.longCellBts = longCellBts;
    }

    public String getLatCellBts() {
        return latCellBts;
    }

    public void setLatCellBts(String latCellBts) {
        this.latCellBts = latCellBts;
    }

    public String getLongCellBts() {
        return longCellBts;
    }

    public void setLongCellBts(String longCellBts) {
        this.longCellBts = longCellBts;
    }
}
