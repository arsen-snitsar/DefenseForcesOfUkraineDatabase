package org.example.finalprojectalpha.Data;

import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Battle implements Comparable<Battle> {

    private String battleName;
    private int battleId;
    private String imagePath;
    private static int battleCount = 0;
    private MyQueue<BattleEvent> battleFlow = new MyQueue<BattleEvent>();
    private ArrayList<Unit> unitsInvolved = new ArrayList<Unit>();

    public String getImagePath() {
        return imagePath;
    }

    public String getBattleName() {
        return battleName;
    }

    public Battle(String battleName, String imagePath) {
        this.battleName = battleName;
        this.battleId = battleCount;
        this.imagePath = imagePath;
        battleCount++;
    }

    public int getBattleId() {
        return battleId;
    }

    public ObservableList<String> getUnitsObsList() {
        ObservableList<String> units = javafx.collections.FXCollections.observableArrayList();
        for (Unit unit : unitsInvolved) {
            units.add(unit.getUnitName());
        }
        return units;
    }

    public void setImagePath(String string) {
        this.imagePath = string;
    }

    public ArrayList<Unit> getUnitsInvolved() {
        return unitsInvolved;
    }

    public void setName(String text) {
        this.battleName = text;
    }

    @Override
    public int compareTo(Battle o) {
        return this.battleName.compareTo(o.battleName);
    }

    public void addUnitInvolved(Unit newUnit) {
        unitsInvolved.add(newUnit);
    }
}