package org.example.finalprojectalpha.Controls;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Battle;

public class AddNewBattleControl extends HBox {
    public AddNewBattleControl() {
        Button addNewUnitButton = new Button();
        Image image = new Image(App.class.getResource("AddButton.png").toExternalForm());
        ImageView buttonView = new ImageView(image);
        buttonView.setFitHeight(90);
        buttonView.setFitWidth(90);
        addNewUnitButton.setGraphic(buttonView);
        addNewUnitButton.setPrefHeight(100);
        addNewUnitButton.setPrefWidth(100);
        this.getChildren().add(addNewUnitButton);
    }
}
