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
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Unit;

import java.io.File;
import java.util.Objects;

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

        ImageView insigniaView;
        if (unit.getInsigniaPath() != null && !unit.getInsigniaPath().equals("null")) {
            Image insigniaImage = new Image(unit.getInsigniaPath());
            insigniaView = new ImageView(insigniaImage);
        } else {
            Image insigniaNotFoundImage = new Image(App.class.getResource("NoImageFound.jpg").toExternalForm());
            insigniaView = new ImageView(insigniaNotFoundImage);
        }
        insigniaView.setFitHeight(150);
        insigniaView.setFitWidth(150);
        hBoxToReturn.getChildren().add(insigniaView);

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
            hBoxToReturn.getChildren().remove(insigniaView);
            hBoxToReturn.getChildren().remove(unitNameLabel);
            hBoxToReturn.getChildren().remove(editButton);

            Button chooseInsigniaButton = getButton(insigniaView, unit);
            hBoxToReturn.getChildren().add(chooseInsigniaButton);

            hBoxToReturn.getChildren().add(editNameField);
            Button saveButton = new Button("Save");
            hBoxToReturn.getChildren().add(saveButton);
            saveButton.setFont(new Font(36));
            saveButton.setOnAction(event1 -> {
                hBoxToReturn.getChildren().remove(chooseInsigniaButton);
                hBoxToReturn.getChildren().remove(editNameField);
                hBoxToReturn.getChildren().remove(saveButton);

                hBoxToReturn.getChildren().add(insigniaView);
                hBoxToReturn.getChildren().add(unitNameLabel);
                hBoxToReturn.getChildren().add(editButton);

                unit.setUnitName(editNameField.getText());

                unitNameLabel.setText(unit.getUnitName());
            });
        });

        hBoxToReturn.getChildren().add(editButton);
        hBoxToReturn.setAlignment(Pos.CENTER_LEFT);
        hBoxToReturn.setSpacing(10);
        return hBoxToReturn;
    }

    private static Button getButton(ImageView insigniaView, Unit unit) {
        Button chooseInsigniaButton = new Button("Choose\nInsignia");
        chooseInsigniaButton.setFont(new Font(31));
        chooseInsigniaButton.setTextAlignment(TextAlignment.CENTER);
        chooseInsigniaButton.setPrefHeight(150);
        chooseInsigniaButton.setPrefWidth(150);
        chooseInsigniaButton.setOnAction(event2 -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Insignia File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                Image insignia = new Image(selectedFile.toURI().toString());
                unit.setInsigniaPath(selectedFile.toURI().toString());
                insigniaView.setImage(insignia);
            }
            chooseInsigniaButton.setText("Change\nInsignia");
        });
        return chooseInsigniaButton;
    }

}
