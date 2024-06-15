package org.example.finalprojectalpha.Controls;

import javafx.scene.Node;
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
        sort(gridPane, battlesComparable);

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
            App.getBattleNodes().add(new BattleControl((Battle) comparable));
            gridPane.add((Node) App.getBattleNodes().getLast(), 0, App.getBattleNodes().size());
        }
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

    private ArrayList<Comparable> battlesComparable = App.getBattlesComparable();

    private TextField getSearchField(GridPane gridPane) {
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setFont(new Font(18));
        if (Settings.getUseBinarySearch()) {
            searchField.setOnAction(event -> {
                gridPane.getChildren().removeAll(App.getBattleNodes());
                sort(gridPane, battlesComparable);
                for (Comparable comparable : battlesComparable) {
                    HBox hBox = new BattleControl((Battle) comparable);
                    App.getBattleNodes().add(hBox);
                    gridPane.add(hBox, 0, App.getBattleNodes().size());
                }
                String key = searchField.getText();
                searchField.clear();
                ArrayList<Comparable> battleNames = new ArrayList<>();
                for (Comparable comparable : battlesComparable) {
                    battleNames.add(((Battle) comparable).getBattleName());
                }
                int index = BinarySearch.search(battleNames, key);
                gridPane.getChildren().removeAll(App.getBattleNodes());
                App.getBattleNodes().clear();
                gridPane.getChildren().remove(App.getAddNewBattleControlNode());
                HBox hBox;
                if (index != -1) {
                    hBox = new BattleControl((Battle) battlesComparable.get(index));
                } else {
                    hBox = new BattleControl(new Battle("No such battle", null));
                }
                App.getBattleNodes().add(hBox);
                gridPane.add(hBox, 0, 1);
                gridPane.add(App.getAddNewBattleControlNode(), 0, 2);
            });
        } else {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                gridPane.getChildren().removeAll(App.getBattleNodes());
                App.getBattleNodes().clear();
                gridPane.getChildren().remove(App.getAddNewBattleControlNode());
                for (Comparable comparable : battlesComparable) {
                    Battle battle = (Battle) comparable;
                    if (battle.getBattleName().toLowerCase().contains(newValue.toLowerCase())) {
                        HBox hBox = new BattleControl(battle);
                        App.getBattleNodes().add(hBox);
                        gridPane.add(hBox, 0, App.getBattleNodes().size());
                    }
                }
                gridPane.add(App.getAddNewBattleControlNode(), 0, App.getBattleNodes().size() + 1);
            });
        }
        return searchField;
    }


    public BattleLabelControl(GridPane gridPane) {
        super(new Battle("Battle Image | Battle Name", null));
        removeImageAndButton(this);
        this.getChildren().add(getSearchField(gridPane));
        this.setOnMouseClicked(e -> {
            gridPane.getChildren().removeAll(App.getBattleNodes());
            App.getBattleNodes().clear();
            gridPane.getChildren().remove(App.getAddNewBattleControlNode());
            HBox hbox = changeSort(gridPane);
            this.getChildren().setAll(hbox.getChildren());
        });
    }

    public BattleLabelControl(GridPane gridPane, String name) {
        super(new Battle(name, null));

        this.setOnMouseClicked(e -> {
            gridPane.getChildren().removeAll(App.getBattleNodes());
            App.getBattleNodes().clear();
            gridPane.getChildren().remove(App.getAddNewBattleControlNode());

            HBox hbox = changeSort(gridPane);
            this.getChildren().setAll(hbox.getChildren());
        });
    }
}
