package org.example.finalprojectalpha.Controls;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.*;

import java.util.ArrayList;

public class BattleLabelControl extends BattleControl {

    private String sortOrder = "asc";

    private HBox changeSort(GridPane gridPane) {
        sort(gridPane, Battles.getComparable());

        HBox hBoxToRet;
        if (sortOrder.equals("desc")) {
            sortOrder = "asc";
            hBoxToRet =
                    removeImageAndButton(new BattleLabelControl(gridPane, "Battle Image | Battle Name | ▼"));
            hBoxToRet.getChildren().add(getSearchField(gridPane));
        } else {
            sortOrder = "desc";
            hBoxToRet =
                    removeImageAndButton(new BattleLabelControl(gridPane, "Battle Image | Battle Name | ▲"));
            hBoxToRet.getChildren().add(getSearchField(gridPane));
        }
        return hBoxToRet;
    }

    private void sort(GridPane gridPane, ArrayList<Comparable> battlesComparable) {
        Quicksort.sort(battlesComparable, sortOrder);
        for (Comparable comparable : battlesComparable) {
            Battles.addNode(new BattleControl((Battle) comparable));
            gridPane.add(Battles.getLastNode(), 0, Battles.nodesSize());
        }
        App.addNewBattleButtonToGridpane();
    }

    private HBox removeImageAndButton(HBox hBox) {
        if (!hBox.getChildren().isEmpty()) {
            hBox.getChildren().removeFirst();
        }
        if (!hBox.getChildren().isEmpty()) {
            hBox.getChildren().removeLast();
        }
        return hBox;
    }

    private TextField getSearchField(GridPane gridPane) {
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setFont(App.defFont);
        if (Settings.getUseBinarySearch()) {
            searchField.setOnAction(_ -> {
                gridPane.getChildren().removeAll(Battles.getNodes());
                sort(gridPane, Battles.getComparable());
                for (Comparable comparable : Battles.getComparable()) {
                    HBox hBox = new BattleControl((Battle) comparable);
                    Battles.addNode(hBox);
                    gridPane.add(hBox, 0, Battles.nodesSize());
                }
                String key = searchField.getText();
                searchField.clear();
                ArrayList<Comparable> battleNames = new ArrayList<>();
                for (Comparable comparable : Battles.getComparable()) {
                    battleNames.add(((Battle) comparable).getName());
                }
                int index = BinarySearch.search(battleNames, key);
                gridPane.getChildren().removeAll(Battles.getNodes());
                Battles.clearNodes();
                gridPane.getChildren().remove(App.getAddNewBattleControlNode());
                HBox hBox;
                if (index != -1) {
                    hBox = new BattleControl((Battle) Battles.getComparable().get(index));
                } else {
                    hBox = new BattleControl(new Battle("No such battle", null));
                }
                Battles.addNode(hBox);
                gridPane.add(hBox, 0, 1);
                gridPane.add(App.getAddNewBattleControlNode(), 0, 2);
            });
        } else {
            searchField.textProperty().addListener((_, _, newValue) -> {
                gridPane.getChildren().removeAll(Battles.getNodes());
                Battles.clearNodes();
                gridPane.getChildren().remove(App.getAddNewBattleControlNode());
                for (Comparable comparable : Battles.getComparable()) {
                    Battle battle = (Battle) comparable;
                    if (battle.getName().toLowerCase().contains(newValue.toLowerCase())) {
                        HBox hBox = new BattleControl(battle);
                        Battles.addNode(hBox);
                        gridPane.add(hBox, 0, Battles.nodesSize());
                    }
                }
                gridPane.add(App.getAddNewBattleControlNode(), 0, Battles.nodesSize() + 1);
            });
        }
        return searchField;
    }

    public BattleLabelControl(GridPane gridPane) {
        super(new Battle("Battle Image | Battle Name", null));
        removeImageAndButton(this);
        this.getChildren().add(getSearchField(gridPane));
        this.setOnMouseClicked(e -> {
            gridPane.getChildren().removeAll(Battles.getNodes());
            Battles.clearNodes();
            gridPane.getChildren().remove(App.getAddNewBattleControlNode());
            HBox hbox = changeSort(gridPane);
            this.getChildren().setAll(hbox.getChildren());
        });
    }

    public BattleLabelControl(GridPane gridPane, String name) {
        super(new Battle(name, null));

        this.setOnMouseClicked(e -> {
            gridPane.getChildren().removeAll(Battles.getNodes());
            Battles.clearNodes();
            gridPane.getChildren().remove(App.getAddNewBattleControlNode());

            HBox hbox = changeSort(gridPane);
            this.getChildren().setAll(hbox.getChildren());
        });
    }
}
