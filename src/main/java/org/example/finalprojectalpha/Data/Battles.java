package org.example.finalprojectalpha.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Controls.BattleControl;

import java.util.ArrayList;
import java.util.List;

public class Battles {

    private static final List<Battle> list = new ArrayList<>();
    private static final List<Node> nodes = new ArrayList<>();

    public static List<Battle> getList() {
        return list;
    }

    public static ObservableList<String> getObservableList() {
        ObservableList<String> battleNames = FXCollections.observableArrayList();
        for (Battle battle : list) {
            battleNames.add(battle.getName());
        }
        return battleNames;
    }

    public static void add(Battle newBattle) {
        if (!list.contains(newBattle))
            list.add(newBattle);
    }

    public static void add(String newBattleName) {
        add(new Battle(newBattleName, "null"));
        Node battleNode = new BattleControl(getLast());
        nodes.add(battleNode);
        App.addBattleToGridpane(battleNode);
    }

    public static void remove(Battle battle){
        list.remove(battle);
    }

    public static int findIndex(String battleName) {
        for (Battle battle : list) {
            if (battle.getName().equals(battleName)) {
                return list.indexOf(battle);
            }
        }
        return -1;
    }

    public static ArrayList<Battle> getArrayList() {
        return new ArrayList<>(list);
    }

    public static Battle getLast() {
        return list.getLast();
    }

    public static int size() {
        return list.size();
    }

    public static ArrayList<Comparable> getComparable() {
        return new ArrayList<Comparable>(list);
    }

    public static List<Node> getNodes() {
        return nodes;
    }

    public static void addNode(HBox hBoxToAdd) {
        nodes.add(hBoxToAdd);
    }

    public static int getIndexOf(Battle battle) {
        return list.indexOf(battle);
    }

    public static void clearNodes() {
        nodes.clear();
    }

    public static int nodesSize() {
        return nodes.size();
    }
    public static Node getLastNode() {
        return nodes.getLast();
    }

    public static boolean contains(String name) {
        for (Battle battle : list) {
            if (battle.getName().equals(name))
                return true;
        }
        return false;
    }
}
