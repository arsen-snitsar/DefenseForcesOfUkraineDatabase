package org.example.finalprojectalpha.Controls;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import org.example.finalprojectalpha.Data.BinarySearch;
import org.example.finalprojectalpha.Data.Quicksort;
import org.example.finalprojectalpha.Data.Unit;
import org.example.finalprojectalpha.App;

import java.util.ArrayList;

public class UnitLabelControl extends UnitControl {

    private ArrayList<Comparable> unitsComparable = App.getUnitsComparable();

    private void removeImageAndButton(HBox hBox) {
        hBox.getChildren().remove(0);
        hBox.getChildren().remove(1);
    }

    private String sortOrder = "asc";

    private HBox changeSort(GridPane gridPane){
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
        gridPane.getChildren().removeAll(App.getUnitNodes());
        gridPane.getChildren().remove(App.getAddNewUnitControlNode());
        Quicksort.sort(unitsComparable, sortOrder);
        for (Comparable comparable : unitsComparable) {
            App.getUnitNodes().add(new UnitControl().render((Unit) comparable));
            gridPane.add((Node) App.getUnitNodes().getLast(), 0, App.getUnitNodes().size());
        }
        gridPane.add(App.getAddNewUnitControlNode(), 0, App.getUnitNodes().size() + 1);
    }

    private TextField getSearchField(GridPane gridPane) {
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setFont(new Font(18));
        searchField.setOnAction(event -> {
            gridPane.getChildren().removeAll(App.getUnitNodes());
            sort(gridPane, unitsComparable);
            for (Comparable comparable : unitsComparable) {
                HBox hBox = new UnitControl().render((Unit) comparable);
                App.getUnitNodes().add(hBox);
                gridPane.add(hBox, 0, App.getUnitNodes().size());
            }
            String key = searchField.getText();
            searchField.clear();
            ArrayList<Comparable> unitNames = new ArrayList<>();
            for (Comparable comparable : unitsComparable) {
                unitNames.add(((Unit) comparable).getUnitName());
            }
            int index = BinarySearch.search(unitNames, key);
            if (index != -1) {
                gridPane.getChildren().removeAll(App.getUnitNodes());
                App.getUnitNodes().clear();
                HBox hBox = new UnitControl().render((Unit) unitsComparable.get(index));
                App.getUnitNodes().add(hBox);
                gridPane.add(hBox, 0, 1);
                gridPane.getChildren().remove(App.getAddNewUnitControlNode());
                gridPane.add(App.getAddNewUnitControlNode(), 0, 2);
            }
        });
        return searchField;
    }

    public HBox render(GridPane gridPane) {
        Unit labelUnit = new Unit("Unit Insignia | Unit name");
        final HBox[] hBoxToReturn = {new UnitControl().render(labelUnit)};
        removeImageAndButton(hBoxToReturn[0]);

        hBoxToReturn[0].setOnMouseClicked(e -> {
            gridPane.getChildren().removeAll(App.getUnitNodes());
            App.getUnitNodes().clear();
            gridPane.getChildren().remove(App.getAddNewUnitControlNode());

            HBox newHbox = changeSort(gridPane);
            removeImageAndButton(newHbox);
            hBoxToReturn[0].getChildren().setAll(newHbox.getChildren());
        });

        hBoxToReturn[0].getChildren().add(getSearchField(gridPane));

        return hBoxToReturn[0];
    }
}
