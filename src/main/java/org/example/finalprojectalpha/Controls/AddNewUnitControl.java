package org.example.finalprojectalpha.Controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Unit;
import org.example.finalprojectalpha.Data.Units;

import static org.example.finalprojectalpha.Controls.UnitControl.myGetPrefWidth;

public class AddNewUnitControl extends HBox {

    private void addNewUnit(TextField field) {
        if (!field.getText().isEmpty()) {
            String newUnitName = field.getText();
            field.clear();
            Units.add(newUnitName);
            App.addNewUnitButtonToGridpane();
        }
    }

    public AddNewUnitControl() {
        this.setPrefWidth(myGetPrefWidth());

        Button button = new Button();
        Image image = new Image(App.class.getResource("AddButton.png").toExternalForm());
        ImageView buttonView = new ImageView(image);
        buttonView.setFitHeight(90);
        buttonView.setFitWidth(90);
        button.setGraphic(buttonView);
        button.setPrefHeight(100);
        button.setPrefWidth(100);
        this.getChildren().add(button);

        TextField field = new TextField();
        field.setPromptText("Enter new unit name");
        field.setFont(new Font(18));
        field.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().add(field);

        button.setOnAction(event -> {
            addNewUnit(field);
        });
        field.setOnAction(event -> {
            addNewUnit(field);
        });

        this.setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                new BorderWidths(1)
                        )
                )
        );

        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(5);
    }
}