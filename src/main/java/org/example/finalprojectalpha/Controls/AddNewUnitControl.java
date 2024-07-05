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

    public AddNewUnitControl() {
        this.setPrefWidth(myGetPrefWidth());

        Button addNewUnitButton = new Button();
        Image image = new Image(App.class.getResource("AddButton.png").toExternalForm());
        ImageView buttonView = new ImageView(image);
        buttonView.setFitHeight(90);
        buttonView.setFitWidth(90);
        addNewUnitButton.setGraphic(buttonView);
        addNewUnitButton.setPrefHeight(100);
        addNewUnitButton.setPrefWidth(100);
        this.getChildren().add(addNewUnitButton);

        TextField newUnitNameField = new TextField();
        newUnitNameField.setPromptText("Enter new unit name");
        newUnitNameField.setFont(new Font(18));
        newUnitNameField.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().add(newUnitNameField);

        addNewUnitButton.setOnAction(event -> {
            if (!newUnitNameField.getText().isEmpty()) {
                String newUnitName = newUnitNameField.getText();
                newUnitNameField.clear();
                Units.add(newUnitName);
            }
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