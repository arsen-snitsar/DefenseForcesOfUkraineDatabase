package org.example.finalprojectalpha.Controls;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Battle;
import org.example.finalprojectalpha.Data.Unit;

import java.io.File;

import static org.example.finalprojectalpha.App.primaryStage;

public class BattleMoreControl {

    private ImageView getImageView(Battle battle) {
        ImageView imageView;
        if (battle.getImagePath() != null && !battle.getImagePath().equals("null")) {
            Image battleImage = new Image(battle.getImagePath());
            imageView = new ImageView(battleImage);
        } else {
            Image imageBattleNotFound = new Image(App.class.getResource("noBattleImageFound.jpg").toExternalForm());
            imageView = new ImageView(imageBattleNotFound);
        }
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    private Button getBackButton(Stage primaryStage) {
        Button backButton = new Button("Back");
        backButton.setFont(new Font(18));
        backButton.setOnAction(event -> {
            primaryStage.setMaximized(true);
            primaryStage.setScene(App.getMainScene());
            App.fireBattlesViewButton();
        });
        return backButton;
    }

    private static Button getChooseImageButton(ImageView imageView, Battle battle) {
        Button chooseImageButton = new Button("Choose\nImage");
        chooseImageButton.setFont(new Font(18));
        chooseImageButton.setTextAlignment(TextAlignment.CENTER);
        chooseImageButton.setPrefHeight(100);
        chooseImageButton.setPrefWidth(100);
        chooseImageButton.setOnAction(event2 -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                Image battleImage = new Image(selectedFile.toURI().toString());
                battle.setImagePath(selectedFile.toURI().toString());
                imageView.setImage(battleImage);
            }
            chooseImageButton.setText("Change\nImage");
        });
        return chooseImageButton;
    }

    private Button getEditButton(
            GridPane gridPane,
            ImageView imageView,
            Battle battle,
            Text battleNameText,
            ListView<String> unitsListView
    ) {
        Button editButton = new Button("Edit");
        editButton.setFont(new Font(18));
        editButton.setOnAction(event -> {
            gridPane.getChildren().remove(imageView);

            gridPane.getChildren().remove(battleNameText);
            TextField editNameField = new TextField(battle.getBattleName());
            editNameField.setFont(new Font(18));
            gridPane.add(editNameField, 1, 1);
            Button chooseImageButton = getChooseImageButton(imageView, battle);
            gridPane.add(chooseImageButton, 2, 1);

            unitsListView.setItems(App.getUnitsObsList());
            unitsListView.setCellFactory(lv -> new ListCell<String>() {
                private Text text;

                {
                    text = new Text();
                    text.setFont(new Font(18)); // Set the font size here
                    setGraphic(text);
                }

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        text.setText(null);
                    } else {
                        text.setText(item);
                        if (battle.getUnitsInvolved().stream().anyMatch(unit -> unit.getUnitName().equals(item))) {
                            text.setFill(Color.GRAY);
                        } else {
                            text.setFill(Color.BLACK);
                        }
                    }
                }
            });

            Button addUnitButton = new Button("Add Unit");
            addUnitButton.setFont(new Font(18));
            addUnitButton.setOnAction(e -> {
                String selectedUnitName = unitsListView.getSelectionModel().getSelectedItem();
                if (selectedUnitName != null) {
                    Unit selectedUnit = App.getUnitsArrayList().stream()
                            .filter(unit -> unit.getUnitName().equals(selectedUnitName))
                            .findFirst()
                            .orElse(null);
                    if (selectedUnit != null && !battle.getUnitsInvolved().contains(selectedUnit)) {
                        battle.getUnitsInvolved().add(selectedUnit);
                        selectedUnit.getBattlesParticipated().add(battle);
                    }
                }
            });
            gridPane.add(addUnitButton, 1, 4);

            gridPane.getChildren().remove(editButton);
            Button saveButton = new Button("Save");
            saveButton.setFont(new Font(18));
            saveButton.setOnAction(e -> {
                gridPane.getChildren().remove(chooseImageButton);
                gridPane.add(imageView, 2, 1);

                battle.setName(editNameField.getText());
                gridPane.getChildren().remove(editNameField);
                battleNameText.setText(battle.getBattleName());
                battleNameText.setFont(new Font(18));
                gridPane.add(battleNameText, 1, 1);

                unitsListView.setItems(battle.getUnitsObsList());
                unitsListView.setCellFactory(lv -> new ListCell<String>() {
                    private Text text;
                    {
                        text = new Text();
                        text.setFont(new Font(18)); // Set the font size here
                        setGraphic(text);
                    }
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            text.setText(null);
                        } else {
                            text.setText(item);
                            text.setFill(Color.BLACK);
                        }
                    }
                });
                gridPane.getChildren().remove(addUnitButton);

                gridPane.add(editButton, 1, 0);
                gridPane.getChildren().remove(saveButton);
            });
            gridPane.add(saveButton, 1, 0);
        });
        return editButton;
    }

    private GridPane getGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        return gridPane;
    }


    public Scene render(Battle battle) {
        GridPane gridPane = getGridPane();
        Scene scene = new Scene(gridPane, 1920, 1080);

        ImageView imageView = getImageView(battle);
        Text battleNameText = new Text(battle.getBattleName());
        battleNameText.setFont(new Font(18));

        Text unitsInvolvedText = new Text("Units involved:");
        unitsInvolvedText.setFont(new Font(18));
        ListView<String> unitsListView = getUnitsListView(battle);
        gridPane.add(unitsInvolvedText, 1, 2);
        gridPane.add(unitsListView, 1, 3);

        gridPane.add(getBackButton(primaryStage), 0, 0);
        gridPane.add(getEditButton(gridPane, imageView, battle, battleNameText, unitsListView), 1, 0);
        gridPane.add(battleNameText, 1, 1);

        gridPane.add(imageView, 2, 1);

        Text battleImageText = new Text("Battle Image");
        battleNameText.setFont(new Font(18));
        gridPane.add(battleImageText, 2, 2);

        return scene;
    }

    private static ListView<String> getUnitsListView(Battle battle) {
        ListView<String> unitsListView = new ListView<>();
        unitsListView.setPrefWidth(300);
        unitsListView.setItems(battle.getUnitsObsList());
        unitsListView.setCellFactory(lv -> new ListCell<String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item);
                    setFont(new Font(18)); // Set the font size here
                }
            }
        });
        return unitsListView;
    }

}
