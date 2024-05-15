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

    private HBox changeSort(GridPane gridPane) {
        sort(gridPane, unitsComparable);

        if (sortOrder.equals("desc")) {
            sortOrder = "asc";
            return render(new Unit("Unit Insignia | Unit name | ▼"));
        }
        {
            sortOrder = "desc";
            return render(new Unit("Unit Insignia | Unit name | ▲"));
        }
    }

    private void sort(GridPane gridPane, ArrayList<Comparable> unitsComparable) {
        Quicksort.sort(unitsComparable, sortOrder);
        for (Comparable comparable : unitsComparable) {
            App.getUnitNodes().add(new UnitControl().render((Unit) comparable));
            gridPane.add((Node) App.getUnitNodes().getLast(), 0, App.getUnitNodes().size());
        }
    }

    private TextField getSearchField(GridPane gridPane){
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setFont(new Font(18));
        searchField.setOnAction(event -> {
            gridPane.getChildren().removeAll(App.getUnitNodes());
            App.getUnitNodes().clear();
            gridPane.getChildren().remove(App.getAddNewUnitControlNode());

            HBox newHbox = changeSort(gridPane);
            removeImageAndButton(newHbox);
            this.getChildren().setAll(newHbox.getChildren());

            String search = searchField.getText();
            App.getUnitNodes().add(App.getUnitNodes().get(BinarySearch.search(unitsComparable, new Unit(search))));
            gridPane.add((Node) App.getUnitNodes().getLast(), 0, App.getUnitNodes().size());
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
