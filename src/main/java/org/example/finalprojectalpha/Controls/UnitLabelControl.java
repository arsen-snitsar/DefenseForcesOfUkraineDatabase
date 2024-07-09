package org.example.finalprojectalpha.Controls;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import org.example.finalprojectalpha.Data.*;
import org.example.finalprojectalpha.App;

import java.util.ArrayList;

public class UnitLabelControl extends UnitControl {

    private ArrayList<Comparable> unitsComparable = Units.getComparable();

    private void removeImageAndButton(HBox hBox) {
        hBox.getChildren().remove(0);
        hBox.getChildren().remove(1);
    }

    private String sortOrder = "asc";

    private HBox changeSort(GridPane gridPane) {
        sort(gridPane, unitsComparable);

        if (sortOrder.equals("desc")) {
            sortOrder = "asc";
            HBox labelBox = new UnitControl(new Unit("Unit Insignia | Unit name | ▼"));
            labelBox.getChildren().add(getSearchField(gridPane));
            return labelBox;
        }
        {
            sortOrder = "desc";
            HBox labelBox = new UnitControl(new Unit("Unit Insignia | Unit name | ▲"));
            labelBox.getChildren().add(getSearchField(gridPane));
            return labelBox;
        }
    }

    private void sort(GridPane gridPane, ArrayList<Comparable> unitsComparable) {
        gridPane.getChildren().removeAll(Units.getNodes());
        gridPane.getChildren().removeAll(App.getAddNewUnitControlNode());
        Quicksort.sort(unitsComparable, sortOrder);
        for (Comparable comparable : unitsComparable) {
            Units.addNode(new UnitControl((Unit) comparable));
            App.addUnitToGridpane(Units.getLastNode());
        }
    }

    private TextField getSearchField(GridPane gridPane) {
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setFont(new Font(18));
        if (Settings.getUseBinarySearch()) {
            searchField.setOnAction(event -> {
                gridPane.getChildren().removeAll(Units.getNodes());
                sort(gridPane, unitsComparable);
                for (Comparable comparable : unitsComparable) {
                    HBox hBox = new UnitControl((Unit) comparable);
                    Units.addNode(hBox);
                    gridPane.add(hBox, 0, Units.nodesSize());
                }
                String key = searchField.getText();
                searchField.clear();
                ArrayList<Comparable> unitNames = new ArrayList<>();
                for (Comparable comparable : unitsComparable) {
                    unitNames.add(((Unit) comparable).getName());
                }
                int index = BinarySearch.search(unitNames, key);

                gridPane.getChildren().removeAll(Units.getNodes());
                Units.clearNodes();
                gridPane.getChildren().remove(App.getAddNewUnitControlNode());
                HBox hbox;
                if (index != -1) {
                    hbox = new UnitControl((Unit) unitsComparable.get(index));
                } else {
                    hbox = new UnitControl(new Unit("No such unit"));
                    removeImageAndButton(hbox);
                }
                Units.addNode(hbox);
                gridPane.add(hbox, 0, 1);
                gridPane.add(App.getAddNewUnitControlNode(), 0, 2);
            });
        } else {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                gridPane.getChildren().removeAll(Units.getNodes());
                Units.clearNodes();
                gridPane.getChildren().remove(App.getAddNewUnitControlNode());
                for (Comparable comparable : unitsComparable) {
                    Unit unit = (Unit) comparable;
                    if (unit.getName().toLowerCase().contains(newValue.toLowerCase())) {
                        HBox hBox = new UnitControl(unit);
                        Units.addNode(hBox);
                        gridPane.add(hBox, 0, Units.nodesSize());
                    }
                }
                gridPane.add(App.getAddNewUnitControlNode(), 0, Units.nodesSize() + 1);
            });
        }

        return searchField;
    }

    public UnitLabelControl(GridPane gridPane){
        super(new Unit("Unit Insignia | Unit name"));
        removeImageAndButton(this);
        this.setOnMouseClicked(e -> {
            gridPane.getChildren().removeAll(Units.getNodes());
            Units.clearNodes();
            gridPane.getChildren().remove(App.getAddNewUnitControlNode());

            HBox hbox = changeSort(gridPane);
            removeImageAndButton(hbox);
            this.getChildren().setAll(hbox.getChildren());
        });
        this.getChildren().add(getSearchField(gridPane));
    }
}
