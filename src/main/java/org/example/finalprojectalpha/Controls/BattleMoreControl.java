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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.*;

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
        imageView.setFitHeight(300);
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
            Text battleImageText,
            Battle battle,
            Text battleNameText,
            ListView<String> unitsListView,
            BattleFlowControl battleFlowBox
    ) {
        Button editButton = new Button("Edit");
        editButton.setFont(new Font(18));
        editButton.setOnAction(event -> {
            gridPane.getChildren().remove(imageView);

            gridPane.getChildren().remove(battleNameText);
            TextField editNameField = new TextField(battle.getName());
            editNameField.setFont(new Font(18));
            gridPane.add(editNameField, 1, 1);
            Button chooseImageButton = getChooseImageButton(imageView, battle);
            gridPane.add(chooseImageButton, 2, 1);
            gridPane.getChildren().remove(battleImageText);
            gridPane.add(battleImageText, 2, 2);
            gridPane.getChildren().remove(battleFlowBox);
            gridPane.add(battleFlowBox, 2, 3);
            battleFlowBox.addAddNewBattleEventControl(battle);

            unitsListView.setItems(App.getUnitsObsList());
            unitsListView.setCellFactory(lv -> new ListCell<String>() {
                private Text text;

                {
                    text = new Text();
                    text.setFont(new Font(18));
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

            Button addUnitButton = getAddUnitButton(battle, unitsListView);
            gridPane.add(addUnitButton, 1, 4);

            gridPane.getChildren().remove(editButton);
            Button saveButton = new Button("Save");
            saveButton.setFont(new Font(18));
            saveButton.setOnAction(e -> {
                gridPane.getChildren().remove(chooseImageButton);
                gridPane.add(imageView, 2, 1);
                gridPane.getChildren().remove(battleImageText);
                gridPane.add(battleImageText, 2, 2);

                battle.setName(editNameField.getText());
                gridPane.getChildren().remove(editNameField);
                battleNameText.setText(battle.getName());
                battleNameText.setFont(new Font(18));
                gridPane.add(battleNameText, 1, 1);

                unitsListView.setItems(battle.getUnitsObsList());
                unitsListView.setCellFactory(lv -> new ListCell<String>() {
                    private Text text;

                    {
                        text = new Text();
                        text.setFont(new Font(18));
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

                battleFlowBox.removeAddNewBattleEventControl();

                gridPane.add(editButton, 1, 0);
                gridPane.getChildren().remove(saveButton);
            });
            gridPane.add(saveButton, 1, 0);
        });
        return editButton;
    }

    private static Button getAddUnitButton(Battle battle, ListView<String> unitsListView) {
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
        return addUnitButton;
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

        Text unitsInvolvedText = new Text("Units involved:");
        unitsInvolvedText.setFont(new Font(18));
        ListView<String> unitsListView = getUnitsListView(battle);
        gridPane.add(unitsInvolvedText, 1, 2);
        gridPane.add(unitsListView, 1, 3);

        BattleFlowControl battleFlowBox = new BattleFlowControl(battle);
        gridPane.add(battleFlowBox, 3, 3);

        ImageView imageView = getImageView(battle);
        Text battleNameText = new Text(battle.getName());
        battleNameText.setFont(new Font(18));
        gridPane.add(imageView, 3, 1);

        Text battleImageText = new Text("Battle Image");
        battleNameText.setFont(new Font(18));
        gridPane.add(battleImageText, 3, 2);

        gridPane.add(
                getButtonBox(
                        gridPane,
                        imageView,
                        battleImageText,
                        battle,
                        battleNameText,
                        unitsListView,
                        battleFlowBox
                ),
                0, 0
        );

//        gridPane.add(getBackButton(primaryStage), 0, 0);
//        gridPane.add(
//                getEditButton(gridPane, imageView, battleImageText, battle, battleNameText, unitsListView, battleFlowBox),
//                1, 0
//        );
//        gridPane.add(getDeleteButton(battle), 2, 0);
        gridPane.add(battleNameText, 1, 1);


        return scene;
    }

    private static ListView<String> getUnitsListView(Battle battle) {
        ListView<String> unitsListView = new ListView<>();
        unitsListView.setPrefWidth(450);
        unitsListView.setItems(battle.getUnitsObsList());
        unitsListView.setCellFactory(lv -> new ListCell<String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item);
                    setFont(new Font(18));
                }
            }
        });
        return unitsListView;
    }

    private Button getDeleteButton(Battle battle) {
        Button deleteButton = new Button("Delete");
        deleteButton.setFont(new Font(18));
        deleteButton.setOnAction(event -> {
            Battles.remove(battle);
            App.fireBattlesViewButton();
            primaryStage.setScene(App.getMainScene());
        });
        return deleteButton;
    }

    private HBox getButtonBox(
            GridPane gridPane,
            ImageView imageView,
            Text battleImageText,
            Battle battle,
            Text battleNameText,
            ListView unitsListView,
            BattleFlowControl battleFlowBox
            ){
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(
                getBackButton(primaryStage),
                getEditButton(
                        gridPane,
                        imageView,
                        battleImageText,
                        battle,
                        battleNameText,
                        unitsListView,
                        battleFlowBox
                ),
                getDeleteButton(battle)
        );
        return hbox;
    }
}
