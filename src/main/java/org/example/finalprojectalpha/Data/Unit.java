package org.example.finalprojectalpha.Data;

public class Unit {

    private String unitName;
    private int unitId;
    private String insigniaPath;

    private static int unitCount = 0;

    public Unit(String unitName) {
        this.unitName = unitName;
        this.unitId = unitCount;
        unitCount++;
    }

    public Unit(String unitName, String insigniaPath) {
        this.unitName = unitName;
        this.insigniaPath = insigniaPath;
        this.unitId = unitCount;
        unitCount++;
    }

    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getUnitId() {
        return unitId;
    }

    public String getInsigniaPath() {
        return insigniaPath;
    }
    public void setInsigniaPath(String insigniaPath) {
        this.insigniaPath = insigniaPath;
    }
}
