package org.example.finalprojectalpha.Controls;

import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
        searchField.setStyle("-fx-background-color: " + Colors.getDark() + "; -fx-text-fill: " + Colors.getNeon() + ";");
        searchField.setBorder(
                new Border(
                        new BorderStroke(
                                Color.web(Colors.getNeon()),
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                new BorderWidths(2)
                        )
                )
        );

        searchField.textProperty().addListener((_, _, newValue) -> {
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

        return searchField;
    }

    public UnitLabelControl(GridPane gridPane) {
        super(new Unit("Unit Insignia | Unit name"));
        removeImageAndButton(this);
        this.setOnMouseClicked(_ -> {
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
