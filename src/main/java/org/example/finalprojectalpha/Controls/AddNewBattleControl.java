package org.example.finalprojectalpha.Controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Battle;

public class AddNewBattleControl extends HBox {
    public AddNewBattleControl() {
        Button addNewBattleButton = new Button();
        Image image = new Image(App.class.getResource("AddButton.png").toExternalForm());
        ImageView buttonView = new ImageView(image);
        buttonView.setFitHeight(90);
        buttonView.setFitWidth(90);
        addNewBattleButton.setGraphic(buttonView);
        addNewBattleButton.setPrefHeight(100);
        addNewBattleButton.setPrefWidth(100);

        TextField newBattleNameField = new TextField();
        newBattleNameField.setPromptText("Enter new battle name");
        newBattleNameField.setFont(new Font(18));
        newBattleNameField.setAlignment(Pos.CENTER_LEFT);

        addNewBattleButton.setOnAction(event -> {
            if (!newBattleNameField.getText().isEmpty()) {
                String newBattleName = newBattleNameField.getText();
                newBattleNameField.clear();
                App.addNewBattle(newBattleName);
            }
        });


        this.getChildren().addAll(addNewBattleButton, newBattleNameField);

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
