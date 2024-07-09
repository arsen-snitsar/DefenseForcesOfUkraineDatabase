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
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Battles;

public class AddNewBattleControl extends HBox {

    private void addNewBattle(TextField field){
        if (!field.getText().isEmpty()) {
            String name = field.getText();
            field.clear();
            Battles.add(name);
        }
    }

    public AddNewBattleControl() {
        Button button = new Button();
        Image image = new Image(App.class.getResource("AddButton.png").toExternalForm());
        ImageView buttonView = new ImageView(image);
        buttonView.setFitHeight(90);
        buttonView.setFitWidth(90);
        button.setGraphic(buttonView);
        button.setPrefHeight(100);
        button.setPrefWidth(100);

        TextField field = new TextField();
        field.setPromptText("Enter new battle name");
        field.setFont(App.defFont);
        field.setAlignment(Pos.CENTER_LEFT);

        button.setOnAction(e -> {
           addNewBattle(field);
        });
        field.setOnAction(e -> {
            addNewBattle(field);
        });

        this.getChildren().addAll(button, field);

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
