package org.example.finalprojectalpha.Controls;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Battle;
import org.example.finalprojectalpha.Data.Battles;
import org.example.finalprojectalpha.Data.Unit;

import java.io.File;

import static org.example.finalprojectalpha.App.primaryStage;

public class UnitMoreControl {

    private ImageView getInsigniaView(Unit unit) {
        ImageView insigniaView;
        if (unit.getInsigniaPath() != null && !unit.getInsigniaPath().equals("null")) {
            Image insigniaImage = new Image(unit.getInsigniaPath());
            insigniaView = new ImageView(insigniaImage);
        } else {
            Image insigniaNotFoundImage = new Image(App.class.getResource("NoImageFound.jpg").toExternalForm());
            insigniaView = new ImageView(insigniaNotFoundImage);
        }
        insigniaView.setFitHeight(300);
        insigniaView.setPreserveRatio(true);
        return insigniaView;
    }

    private Button getBackButton(Stage primaryStage) {
        Button backButton = new Button("Back");
        backButton.setFont(new Font(18));
        backButton.setOnAction(event -> {
            primaryStage.setMaximized(true);
            primaryStage.setScene(App.getMainScene());
            App.fireUnitsViewButton();
        });
        return backButton;
    }

    private static Button getChooseInsigniaButton(ImageView insigniaView, Unit unit) {
        Button chooseInsigniaButton = new Button("Choose\nInsignia");
        chooseInsigniaButton.setFont(new Font(18));
        chooseInsigniaButton.setTextAlignment(TextAlignment.CENTER);
        chooseInsigniaButton.setPrefHeight(100);
        chooseInsigniaButton.setPrefWidth(100);
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

    private Button getEditButton(
            GridPane gridPane,
            ImageView insigniaView,
            Unit unit,
            Text unitNameText,
            ListView<String> battlesListView
    ) {
        Button editButton = new Button("Edit");
        editButton.setFont(new Font(18));
        editButton.setOnAction(event -> {
            buttonbox.getChildren().removeLast();

            gridPane.getChildren().remove(insigniaView);

            gridPane.getChildren().remove(unitNameText);
            TextField editNameField = new TextField(unit.getUnitName());
            editNameField.setFont(new Font(18));
            gridPane.add(editNameField, 1, 1);
            Button chooseInsigniaButton = getChooseInsigniaButton(insigniaView, unit);
            gridPane.add(chooseInsigniaButton, 3, 1);

            battlesListView.setItems(Battles.getObservableList());
            battlesListView.setCellFactory(lv -> new ListCell<String>() {
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
                        if (unit.getBattlesParticipated().stream().anyMatch(battle -> battle.getBattleName().equals(item))) {
                            text.setFill(Color.GRAY);
                        } else {
                            text.setFill(Color.BLACK);
                        }
                    }
                }
            });

            Button addBattleButton = getAddBattleButton(unit, battlesListView);
            gridPane.add(addBattleButton, 1, 4);

            buttonbox.getChildren().remove(1);
            Button saveButton = new Button("Save");
            saveButton.setFont(new Font(18));
            saveButton.setOnAction(e -> {
                gridPane.getChildren().remove(chooseInsigniaButton);
                gridPane.add(insigniaView, 3, 1);

                unit.setUnitName(editNameField.getText());
                gridPane.getChildren().remove(editNameField);
                unitNameText.setText(unit.getUnitName());
                unitNameText.setFont(new Font(18));
                gridPane.add(unitNameText, 1, 1);

                battlesListView.setItems(unit.getBattlesObsList());
                battlesListView.setCellFactory(lv -> new ListCell<String>() {
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
                gridPane.getChildren().remove(addBattleButton);

                buttonbox.getChildren().add(editButton);
                buttonbox.getChildren().remove(saveButton);
                buttonbox.getChildren().add(getDeleteButton(unit));
            });
            buttonbox.getChildren().add(saveButton);
        });
        return editButton;
    }

    private static Button getAddBattleButton(Unit unit, ListView<String> battlesListView) {
        Button addBattleButton = new Button("Add Battle");
        addBattleButton.setFont(new Font(18));
        addBattleButton.setOnAction(e -> {
            String selectedBattleName = battlesListView.getSelectionModel().getSelectedItem();
            if (selectedBattleName != null) {
                Battle selectedBattle = Battles.getArrayList().stream()
                        .filter(battle -> battle.getBattleName().equals(selectedBattleName))
                        .findFirst()
                        .orElse(null);
                if (selectedBattle != null && !unit.getBattlesParticipated().contains(selectedBattle)) {
                    unit.getBattlesParticipated().add(selectedBattle);
                }
            }
        });
        return addBattleButton;
    }

    private GridPane getGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        return gridPane;
    }

    private HBox buttonBox(Button backButton, Button editButton, Button deleteButton) {
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(backButton, editButton, deleteButton);
        return buttonBox;
    }

    private HBox buttonbox;

    public Scene render(Unit unit) {
        GridPane gridPane = getGridPane();
        Scene scene = new Scene(gridPane, 1920, 1080);

        ImageView insigniaView = getInsigniaView(unit);
        Text unitNameText = new Text(unit.getUnitName());
        unitNameText.setFont(new Font(18));

        Text battlesParticipatedText = new Text("Battles participated:");
        battlesParticipatedText.setFont(new Font(18));
        ListView<String> battlesListView = getBattlesListView(unit);
        gridPane.add(battlesParticipatedText, 1, 2);
        gridPane.add(battlesListView, 1, 3);

        gridPane.add(getBackButton(primaryStage), 0, 0);

        Button deleteButton = getDeleteButton(unit);
        Button editButton = getEditButton(gridPane, insigniaView, unit, unitNameText, battlesListView);
        buttonbox = buttonBox(
                getBackButton(primaryStage),
                editButton,
                deleteButton
        );
        gridPane.add(buttonbox, 0, 0);

        gridPane.add(insigniaView, 3, 1);

        Text unitInsigniaText = new Text("Unit Insignia");
        unitInsigniaText.setFont(new Font(18));
        gridPane.add(unitInsigniaText, 3, 2);

        return scene;
    }

    private Button getDeleteButton(Unit unit) {
        Button deleteButton = new Button("Delete");
        deleteButton.setFont(new Font(18));
        deleteButton.setOnAction(event -> {
            App.removeUnit(unit);
            App.fireUnitsViewButton();
            primaryStage.setScene(App.getMainScene());
        });
        return deleteButton;
    }

    private static ListView<String> getBattlesListView(Unit unit) {
        ListView<String> battlesListView = new ListView<>();
        battlesListView.setPrefWidth(300);
        battlesListView.setItems(unit.getBattlesObsList());
        battlesListView.setCellFactory(lv -> new ListCell<String>() {
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
        return battlesListView;
    }

}
