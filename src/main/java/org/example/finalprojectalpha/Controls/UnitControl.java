package org.example.finalprojectalpha.Controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Unit;

public class UnitControl extends HBox {

    private static final Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
    private static double prefWidth = visualBounds.getWidth() - 700;

    public static double myGetPrefWidth() {
        return prefWidth;
    }

    public HBox render(Unit unit) {
        HBox hBoxToReturn = new HBox();
        Label unitNameLabel = new Label(unit.getUnitName());
        unitNameLabel.setFont(new Font(36));
        unitNameLabel.setAlignment(Pos.CENTER_LEFT);
        unitNameLabel.setPrefWidth(myGetPrefWidth());

        Image insigniaNotFoundImage = new Image(App.class.getResource("NoImageFound.jpg").toExternalForm());
        ImageView insigniaNotFoundView = new ImageView(insigniaNotFoundImage);
        insigniaNotFoundView.setFitHeight(150);
        insigniaNotFoundView.setFitWidth(150);
        hBoxToReturn.getChildren().add(insigniaNotFoundView);

        hBoxToReturn.getChildren().add(unitNameLabel);
        hBoxToReturn.setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                new BorderWidths(1)
                        )
                )
        );
        hBoxToReturn.setPadding(new Insets(20));
        Button editButton = new Button("Edit");
        editButton.setFont(new Font(36));
        editButton.setAlignment(Pos.CENTER_RIGHT);

        TextField editNameField = new TextField(unit.getUnitName());
        editNameField.setFont(new Font(36));
        editNameField.setAlignment(Pos.CENTER_LEFT);
        editNameField.setPrefWidth(myGetPrefWidth());
        editButton.setOnAction(event -> {
            hBoxToReturn.getChildren().remove(unitNameLabel);
            hBoxToReturn.getChildren().remove(editButton);
            hBoxToReturn.getChildren().add(editNameField);
            Button saveButton = new Button("Save");
            hBoxToReturn.getChildren().add(saveButton);
            saveButton.setFont(new Font(36));
            saveButton.setOnAction(event1 -> {
                unit.setUnitName(editNameField.getText());
                hBoxToReturn.getChildren().remove(editNameField);
                hBoxToReturn.getChildren().add(unitNameLabel);
                hBoxToReturn.getChildren().add(editButton);
                hBoxToReturn.getChildren().remove(saveButton);
                unitNameLabel.setText(unit.getUnitName());
            });
        });

        hBoxToReturn.getChildren().add(editButton);
        hBoxToReturn.setAlignment(Pos.CENTER_LEFT);
        hBoxToReturn.setSpacing(10);
        return hBoxToReturn;
    }

}
