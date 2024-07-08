package org.example.finalprojectalpha.Data;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Objects;

public class Battle implements Comparable<Battle> {

    private String name;
    private int id;
    private String imagePath;
    private static int count = 0;
    private MyLinkedList<BattleEvent> flow = new MyLinkedList<>();
    private ArrayList<Unit> unitsInvolved = new ArrayList<>();

    public boolean containsEvent(String event) {
        for (int i = 0; i < flow.getSize(); i++) {
            String current = flow.get(i).getEventText();
            if (event.equals(current))
                return true;
        }
        return false;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public Battle(String name, String imagePath) {
        this.name = name;
        this.id = count;
        this.imagePath = imagePath;
        count++;
    }
    public Battle(String name) {
        this.name = name;
        this.id = count;
        this.imagePath = "null";
        count++;
    }

    public int getId() {
        return id;
    }

    public ObservableList<String> getUnitsObsList() {
        ObservableList<String> units = javafx.collections.FXCollections.observableArrayList();
        for (Unit unit : unitsInvolved) {
            units.add(unit.getName());
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
        this.name = text;
    }

    @Override
    public int compareTo(Battle o) {
        return this.name.compareTo(o.name);
    }

    public void addUnitInvolved(Unit newUnit) {
        unitsInvolved.add(newUnit);
    }

    public void addEvent(String newBattleEventText) {
        BattleEvent newBattleEvent = new BattleEvent(newBattleEventText);
        flow.add(newBattleEvent);
    }

    public BattleEvent getLastEvent() {
        return flow.getLast();
    }

    public MyLinkedList<BattleEvent> getFlow() {
        return flow;
    }
    public BattleEvent getFirstEvent() {
        return flow.getFirst();
    }
    public void addEvent(BattleEvent event) {
        flow.add(event);
    }
}