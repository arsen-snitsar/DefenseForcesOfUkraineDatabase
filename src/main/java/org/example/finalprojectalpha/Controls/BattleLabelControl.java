package org.example.finalprojectalpha.Controls;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.finalprojectalpha.Data.Battle;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Quicksort;
import org.example.finalprojectalpha.Data.Unit;

import java.util.ArrayList;

public class BattleLabelControl extends BattleControl {

    private String sortOrder = "asc";

    private HBox changeSort(GridPane gridPane) {
        sort(gridPane);

        if (sortOrder.equals("desc")) {
            sortOrder = "asc";
            return removeImageAndButton(new BattleLabelControl(gridPane, "Battle Image | Battle Name | ▼"));
        } else {
            sortOrder = "desc";
            return removeImageAndButton(new BattleLabelControl(gridPane, "Battle Image | Battle Name | ▲"));
        }
    }

    private void sort(GridPane gridPane) {
        ArrayList<Comparable> battlesComparable = App.getBattlesComparable();
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

    public BattleLabelControl(GridPane gridPane) {
        super(new Battle("Battle Image | Battle Name", null));
        removeImageAndButton(this);

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
