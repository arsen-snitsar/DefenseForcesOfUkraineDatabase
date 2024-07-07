package org.example.finalprojectalpha;

import org.example.finalprojectalpha.Controls.*;
import org.example.finalprojectalpha.Data.*;
import org.example.finalprojectalpha.Files.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class App extends Application {

    public static Stage primaryStage;
    private static final GridPane gridPane = new GridPane();
    private static Node unitLabelControlNode = new UnitLabelControl(gridPane);
    private static Node battleLabelControlNode = new BattleLabelControl(gridPane);
    private static final Node addNewUnitControlNode = new AddNewUnitControl();
    private static final Node addNewBattleControlNode = new AddNewBattleControl();
    private static final Node searchControlNode = SettingsControl.getSearchControl();

    private static VBox getButtonBox() {
        Button loadFromFileButton = getLoadFromFileButton();
        Button saveToFileButton = getSaveToFileButton();
        unitsViewButton = getUnitsViewButton();
        battlesViewButton = getBattlesViewButton();
        Button settingsButton = getSettingsButton();

        return new VBox(10, loadFromFileButton, saveToFileButton, unitsViewButton, battlesViewButton, settingsButton);
    }
    private static Button getLoadFromFileButton() {
        Button button = new Button("Load");
        button.setFont(new Font(18));
        button.setPrefHeight(25);
        button.setPrefWidth(90);
        button.setOnAction(event -> {
            Input.loadFromFile();
            button.setDisable(true);
            button.setStyle("-fx-opacity: 0.5; -fx-background-color: grey;");
        });
        return button;
    }
    private static Button getSaveToFileButton() {
        Button button = new Button("Save");
        button.setFont(new Font(18));
        button.setPrefHeight(25);
        button.setPrefWidth(90);
        button.setOnAction(event -> {
            Output.saveToFile();
            button.setDisable(true);
            button.setStyle("-fx-opacity: 0.5; -fx-background-color: grey;");
        });
        return button;
    }
    private static Button getUnitsViewButton() {
        Button button = new Button("Units");
        button.setFont(new Font(18));
        button.setPrefHeight(25);
        button.setPrefWidth(90);
        button.setOnAction(_ -> {
            gridPane.getChildren().removeAll(searchControlNode);
            gridPane.getChildren().removeAll(
                    battleLabelControlNode, addNewBattleControlNode,
                    unitLabelControlNode, addNewUnitControlNode);
            gridPane.getChildren().removeAll(Battles.getNodes());
            Battles.clearNodes();
            gridPane.getChildren().removeAll(Units.getNodes());
            Units.clearNodes();
            unitLabelControlNode = new UnitLabelControl(gridPane);
            gridPane.add(unitLabelControlNode, 0, 0);
            for (Unit unit : Units.getArrayList()) {
                HBox hbox = new UnitControl(unit);
                Units.addNode(hbox);
                App.addUnitToGridpane(hbox);
            }
            addNewUnitButtonToGridpane();
        });
        return button;
    }
    private static Button getBattlesViewButton() {
        Button buttonToReturn = new Button("Battles");
        buttonToReturn.setFont(new Font(18));
        buttonToReturn.setPrefHeight(25);
        buttonToReturn.setPrefWidth(90);
        buttonToReturn.setOnAction(event -> {
            gridPane.getChildren().removeAll(searchControlNode);
            gridPane.getChildren().removeAll(Units.getNodes());
            Units.clearNodes();
            gridPane.getChildren().removeAll(Battles.getNodes());
            Battles.clearNodes();
            gridPane.getChildren().removeAll(
                    unitLabelControlNode, addNewUnitControlNode,
                    battleLabelControlNode, addNewBattleControlNode);
            battleLabelControlNode = new BattleLabelControl(gridPane);
            gridPane.getChildren().add(battleLabelControlNode);
            for (Battle battle : Battles.getList()) {
                HBox hBoxToAdd = new BattleControl(battle);
                Battles.addNode(hBoxToAdd);
                App.addBattleToGridpane(hBoxToAdd);
            }
            addNewBattleButtonToGridpane();
        });
        return buttonToReturn;
    }
    private static Button getSettingsButton() {
        Button buttonToReturn = new Button("Settings");
        buttonToReturn.setFont(new Font(18));
        buttonToReturn.setPrefHeight(25);
        buttonToReturn.setPrefWidth(95);
        buttonToReturn.setOnAction(e -> {
                    gridPane.getChildren().removeAll(
                            unitLabelControlNode, addNewUnitControlNode,
                            battleLabelControlNode, addNewBattleControlNode);
                    gridPane.getChildren().removeAll(Units.getNodes());
                    gridPane.getChildren().removeAll(Battles.getNodes());
                    gridPane.add(searchControlNode, 0, 0);
                }
        );

        return buttonToReturn;
    }

    private static final Scene mainScene = setMainScene();
    public static Scene getMainScene() {
        return mainScene;
    }
    public static Scene setMainScene() {
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        Scene scene = new Scene(vBox, 1920, 1080);
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        HBox topBox = new HBox(10);
        topBox.setPadding(new Insets(10));
        HBox lowerBox = new HBox(10);
        lowerBox.setPadding(new Insets(10));

        ImageView coatOfArmsView = new ImageView(
                new Image(
                        App.class.getResource("CoatOfArms.png").toExternalForm()
                )
        );
        coatOfArmsView.setFitHeight(75);
        coatOfArmsView.setFitWidth(75);
        topBox.getChildren().add(coatOfArmsView);
        Label defenseForcesOfUkraineLabel = new Label("Defense Forces of Ukraine");
        defenseForcesOfUkraineLabel.setFont(new Font(18));
        defenseForcesOfUkraineLabel.setAlignment(Pos.CENTER);
        topBox.setBackground(
                new Background(
                        new BackgroundFill(Color.web("#D9D9D9"), CornerRadii.EMPTY, Insets.EMPTY)
                ));
        topBox.setAlignment(Pos.CENTER_LEFT);

        topBox.getChildren().add(defenseForcesOfUkraineLabel);

        vBox.getChildren().add(topBox);

        lowerBox.getChildren().addAll(getButtonBox(), gridPane);
        vBox.getChildren().add(lowerBox);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        lowerBox.getChildren().add(scrollPane);

        class DragContext {
            double mouseAnchorY;
            double initialScrollY;
        }
        final DragContext dragContext = new DragContext();
        scrollPane.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                dragContext.mouseAnchorY = event.getSceneY();
                dragContext.initialScrollY = scrollPane.getVvalue();
                event.consume();
            }
        });
        scrollPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                scrollPane.setVvalue(dragContext.initialScrollY + (dragContext.mouseAnchorY - event.getSceneY()) / scrollPane.getHeight());
                event.consume();
            }
        });
        return scene;
    }
    private static Button unitsViewButton;
    public static void fireUnitsViewButton() {
        unitsViewButton.fire();
    }
    private static Button battlesViewButton;
    public static void fireBattlesViewButton() {
        battlesViewButton.fire();
    }

    public static Node getAddNewUnitControlNode() {
        return addNewUnitControlNode;
    }
    public static Node getAddNewBattleControlNode() {
        return addNewBattleControlNode;
    }

    public static void addUnitToGridpane(Node unit) {
        gridPane.add(unit, 0, Units.nodesSize());
    }
    public static void addNewUnitButtonToGridpane(){
        gridPane.add(addNewUnitControlNode, 0, Units.nodesSize() + 1);
    }
    public static void addBattleToGridpane(Node battle) {
        gridPane.add(battle, 0, Battles.nodesSize());
    }
    public static void addNewBattleButtonToGridpane(){
        gridPane.add(addNewBattleControlNode, 0, Battles.nodesSize() + 1);
    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        App.primaryStage = primaryStage;
        primaryStage.setScene(getMainScene());
        primaryStage.setTitle("Defense Forces of Ukraine Database");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
