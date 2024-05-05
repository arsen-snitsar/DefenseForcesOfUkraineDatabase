package org.example.finalprojectalpha.Data;

public class Unit {

    private String unitName;
    private int unitId;
    private static int unitCount = 0;

    public Unit(String unitName) {
        this.unitName = unitName;
        this.unitId = unitCount;
        unitCount++;
    }

    public String getUnitName() {
        return unitName;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
