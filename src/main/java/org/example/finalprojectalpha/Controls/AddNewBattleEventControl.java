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
import javafx.scene.text.Text;
import org.example.finalprojectalpha.App;

public class AddNewBattleEventControl extends HBox {

    private Button getAddNewEventButton(TextField textField, BattleFlowControl battleFlowBox) {
        Button addNewEventButton = new Button();
        Image image = new Image(App.class.getResource("AddButton.png").toExternalForm());
        ImageView buttonView = new ImageView(image);
        buttonView.setFitHeight(40);
        buttonView.setFitWidth(40);
        addNewEventButton.setGraphic(buttonView);
        addNewEventButton.setPrefHeight(60);
        addNewEventButton.setPrefWidth(60);

        addNewEventButton.setOnAction(e -> {
            if (!textField.getText().isEmpty()) {
                String newEventText = textField.getText();
                battleFlowBox.removeAddNewBattleEventControl();
                battleFlowBox.addBattleEvent(newEventText);
                battleFlowBox.addAddNewBattleEventControl();
            }
            textField.clear();
        });

        return addNewEventButton;
    }

    public AddNewBattleEventControl(BattleFlowControl battleFlowBox) {
        TextField textField = new TextField();
        textField.setPromptText("Enter new event");
        textField.setFont(new Font(18));

        Button addNewEventButton = getAddNewEventButton(textField, battleFlowBox);
        this.getChildren().add(addNewEventButton);
        this.getChildren().add(textField);

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
