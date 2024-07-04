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

    private static final List<Battle> battles = new ArrayList<>();
    private static final List<Node> nodes = new ArrayList<>();

    public static List<Battle> getList() {
        return battles;
    }

    public static ObservableList<String> getObservableList() {
        ObservableList<String> battleNames = FXCollections.observableArrayList();
        for (Battle battle : battles) {
            battleNames.add(battle.getName());
        }
        return battleNames;
    }

    public static void add(Battle newBattle) {
        if (!battles.contains(newBattle))
            battles.add(newBattle);
    }

    public static void add(String newBattleName) {
        add(new Battle(newBattleName, "null"));
        Node battleNode = new BattleControl(getLast());
        nodes.add(battleNode);
        App.addBattleToGridpane(battleNode);
    }

    public static int findIndex(String battleName) {
        for (Battle battle : battles) {
            if (battle.getName().equals(battleName)) {
                return battles.indexOf(battle);
            }
        }
        return -1;
    }

    public static ArrayList<Battle> getArrayList() {
        return new ArrayList<>(battles);
    }

    public static Battle getLast() {
        return battles.getLast();
    }

    public static int size() {
        return battles.size();
    }

    public static ArrayList<Comparable> getComparable() {
        return new ArrayList<Comparable>(battles);
    }

    public static List<Node> getNodes() {
        return nodes;
    }

    public static void addNode(HBox hBoxToAdd) {
        nodes.add(hBoxToAdd);
    }

    public static int getIndexOf(Battle battle) {
        return battles.indexOf(battle);
    }

    public static void clearNodes() {
        nodes.clear();
    }

    public static int getNodesSize() {
        return nodes.size();
    }
    public static Node getLastNode() {
        return nodes.getLast();
    }
}
