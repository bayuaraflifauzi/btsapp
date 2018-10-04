package com.bekasideveloper.btsapp.wrapper.output;

public class CellBtsWrapper {
    private String kodeCellBts;

    private int radiusCellBts;

    private String latCellBts;

    private String longCellBts;


    public CellBtsWrapper() {
    }

    public CellBtsWrapper(String kodeCellBts, int radiusCellBts, String latCellBts, String longCellBts) {
        this.kodeCellBts = kodeCellBts;
        this.radiusCellBts = radiusCellBts;
        this.latCellBts = latCellBts;
        this.longCellBts = longCellBts;
    }

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
