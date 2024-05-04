package org.example.finalprojectalpha.Data;

public class Unit {

    private String unitName;
    private static int unitCount = 0;

    public Unit(String unitName) {
        this.unitName = unitName;
        unitCount++;
    }

    public String getUnitName() {
        return unitName;
    }
}
