package org.example.finalprojectalpha.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import org.example.finalprojectalpha.Controls.UnitControl;
import org.example.finalprojectalpha.App;

import java.util.ArrayList;
import java.util.List;

public class Units {

    private static final List<Unit> list = new ArrayList<>();
    private static final List<Node> nodes = new ArrayList<>();

    public static boolean contains(Unit newUnit) {
        return list.contains(newUnit);
    }


    public static int findIndex(String unitName) {
        for (Unit unit : list) {
            if (unit.getName().equals(unitName))
                return list.indexOf(unit);
        }
        return -1;
    }

    public static Unit get(int index) {
        return list.get(index);
    }
    public static Unit get(String unitName) {
        return list.get(findIndex(unitName));
    }

    public static ArrayList<Comparable> getComparable() {
        return new ArrayList<>(list);
    }

    public static void remove(Unit unit) {
        list.remove(unit);
    }

    public static ArrayList<Unit> getArrayList() {
        return (ArrayList<Unit>) list;
    }


    public static void add(Unit newUnit) {
        list.add(newUnit);
    }
    public static void add(String name) {
        add(new Unit(name));
        Node unit = new UnitControl().render(list.getLast());
        App.addUnitToGridpane(unit);}

    public static int nodesSize() {
        return nodes.size();
    }
    public static ObservableList<String> getObservableList() {
        ObservableList<String> unitNames = FXCollections.observableArrayList();
        for (Unit unit : list) {
            unitNames.add(unit.getName());
        }
        return unitNames;
    }

    public static List<Node> getNodes() {
        return nodes;
    }

    public static int getIndexOf(Unit unit) {
        return list.indexOf(unit);
    }

    public static int size() {
        return list.size();
    }

    public static void addNode(HBox render) {
        nodes.add(render);
    }

    public static Node getLastNode() {
        return nodes.getLast();
    }

    public static void clearNodes() {
        nodes.clear();
    }
}
