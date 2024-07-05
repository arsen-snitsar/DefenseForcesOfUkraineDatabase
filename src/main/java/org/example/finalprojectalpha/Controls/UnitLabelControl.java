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
            HBox labelBox = new UnitControl().render(new Unit("Unit Insignia | Unit name | ▼"));
            labelBox.getChildren().add(getSearchField(gridPane));
            return labelBox;
        }
        {
            sortOrder = "desc";
            HBox labelBox = new UnitControl().render(new Unit("Unit Insignia | Unit name | ▲"));
            labelBox.getChildren().add(getSearchField(gridPane));
            return labelBox;
        }
    }

    private void sort(GridPane gridPane, ArrayList<Comparable> unitsComparable) {
        gridPane.getChildren().removeAll(Units.getNodes());
        gridPane.getChildren().remove(App.getAddNewUnitControlNode());
        Quicksort.sort(unitsComparable, sortOrder);
        for (Comparable comparable : unitsComparable) {
            Units.addNode(new UnitControl().render((Unit) comparable));
            App.addUnitToGridpane(Units.getLastNode());
//            gridPane.add((Node) App.getUnitNodes().getLast(), 0, App.getUnitNodes().size());
        }
        gridPane.add(App.getAddNewUnitControlNode(), 0, Units.nodesSize() + 1);
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
                    HBox hBox = new UnitControl().render((Unit) comparable);
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
                    hbox = new UnitControl().render((Unit) unitsComparable.get(index));
                } else {
                    hbox = new UnitControl().render(new Unit("No such unit"));
                }
                Units.addNode(hbox);
                gridPane.add(hbox, 0, 1);
                gridPane.add(App.getAddNewUnitControlNode(), 0, 2);
            });
        }
        else {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                gridPane.getChildren().removeAll(Units.getNodes());
                Units.clearNodes();
                gridPane.getChildren().remove(App.getAddNewUnitControlNode());
                for (Comparable comparable : unitsComparable) {
                    Unit unit = (Unit) comparable;
                    if (unit.getName().toLowerCase().contains(newValue.toLowerCase())) {
                        HBox hBox = new UnitControl().render(unit);
                        Units.addNode(hBox);
                        gridPane.add(hBox, 0, Units.nodesSize());
                    }
                }
                gridPane.add(App.getAddNewUnitControlNode(), 0,  Units.nodesSize() + 1);
            });
        }

        return searchField;
    }

    public HBox render(GridPane gridPane) {
        Unit labelUnit = new Unit("Unit Insignia | Unit name");
        final HBox[] hBoxToReturn = {new UnitControl().render(labelUnit)};
        removeImageAndButton(hBoxToReturn[0]);

        hBoxToReturn[0].setOnMouseClicked(e -> {
            gridPane.getChildren().removeAll(Units.getNodes());
            Units.clearNodes();
            gridPane.getChildren().remove(App.getAddNewUnitControlNode());

            HBox newHbox = changeSort(gridPane);
            removeImageAndButton(newHbox);
            hBoxToReturn[0].getChildren().setAll(newHbox.getChildren());
        });

        hBoxToReturn[0].getChildren().add(getSearchField(gridPane));

        return hBoxToReturn[0];
    }
}
