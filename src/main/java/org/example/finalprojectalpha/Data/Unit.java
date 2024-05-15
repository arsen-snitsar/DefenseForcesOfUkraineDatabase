package org.example.finalprojectalpha.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Unit implements Comparable<Unit> {

    private String unitName;
    private int unitId;
    private String insigniaPath;
    private ArrayList<Battle> battlesParticipated = new ArrayList<Battle>();

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

    public ObservableList<String> getBattlesObsList() {
        ObservableList<String> battles = FXCollections.observableArrayList();
        for (Battle battle : battlesParticipated) {
            battles.add(battle.getBattleName());
        }
        return battles;
    }
    public ArrayList<Battle> getBattlesParticipated() {
        return battlesParticipated;
    }

    @Override
    public int compareTo(Unit o) {
        return this.unitName.toLowerCase().compareTo(o.unitName.toLowerCase());
    }

    @Override
    public String toString() {
        return this.unitName; // or whatever property you want to print
    }

    public void addBattleParticipated(Battle battle) {
        battlesParticipated.add(battle);
    }
}
