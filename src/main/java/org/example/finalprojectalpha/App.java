package org.example.finalprojectalpha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.util.*;

public class App extends Application {

    public static Stage primaryStage;
    private static final List<Unit> units = new ArrayList<>();
    private static final List<Battle> battles = new ArrayList<>();
    private static final List<Node> unitNodes = new ArrayList<>();
    private static final GridPane gridPane = new GridPane();
    private static Node unitLabelControlNode = new UnitLabelControl().render(gridPane);
    private static final Node addNewUnitControlNode = new AddNewUnitControl().render();
    private static final Node addNewBattleControlNode = new AddNewBattleControl();
    private static final Scene mainScene = setMainScene();
    private static final List<Node> battleNodes = new ArrayList<>();
    private static Button unitsViewButton;
    private static Button battlesViewButton;

    public static int findBattleIndex(String battleName) {
        for (Battle battle : battles) {
            if (battle.getBattleName().equals(battleName)) {
                return battles.indexOf(battle);
            }
        }
        return -1;
    }

    public static ObservableList<String> getBattlesObsList() {
        ObservableList<String> battleNames = FXCollections.observableArrayList();
        for (Battle battle : battles) {
            battleNames.add(battle.getBattleName());
        }
        return battleNames;
    }
    public static ObservableList<String> getUnitsObsList() {
        ObservableList<String> unitNames = FXCollections.observableArrayList();
        for (Unit unit : units) {
            unitNames.add(unit.getUnitName());
        }
        return unitNames;
    }

    public static void fireUnitsViewButton() {
        unitsViewButton.fire();
    }
    public static void fireBattlesViewButton() {
        battlesViewButton.fire();
    }

    public static void addNewUnit(String newUnitName, Stage primaryStage) {
        units.add(new Unit(newUnitName));
        Node unitNode = new UnitControl().render(units.getLast());
        gridPane.add(unitNode, 0, units.size());
        unitNodes.add(unitNode);

        gridPane.getChildren().removeAll(addNewUnitControlNode);
        gridPane.add(addNewUnitControlNode, 0, units.size() + 1);
    }

    public static void addNewUnit(Unit newUnit) {
        if (!units.contains(newUnit))
            units.add(newUnit);
    }

    public static void addNewBattle(String newBattleName) {
        battles.add(new Battle(newBattleName, "null"));
        Node battleNode = new BattleControl(battles.getLast());
        gridPane.add(battleNode, 0, battles.size());
        battleNodes.add(battleNode);

        gridPane.getChildren().removeAll(addNewBattleControlNode);
        gridPane.add(addNewBattleControlNode, 0, battles.size() + 1);
    }

    public static void addNewBattle(Battle newBattle) {
        if (!battles.contains(newBattle))
            battles.add(newBattle);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    private static VBox getButtonBox() {


        Button saveToFileButton = new Button("Save");
        saveToFileButton.setFont(new Font(18));
        saveToFileButton.setPrefHeight(25);
        saveToFileButton.setPrefWidth(90);
        saveToFileButton.setOnAction(event -> {
            Output.saveToFile(units, battles);
        });

        final BattleLabelControl[] battleLabelControl = {new BattleLabelControl(gridPane)};

        unitsViewButton = new Button("Units");
        unitsViewButton.setFont(new Font(18));
        unitsViewButton.setPrefHeight(25);
        unitsViewButton.setPrefWidth(90);
        unitsViewButton.setOnAction(event -> {
            gridPane.getChildren().removeAll(
                    battleLabelControl[0], addNewBattleControlNode,
                    unitLabelControlNode, addNewUnitControlNode);
            gridPane.getChildren().removeAll(battleNodes);
            gridPane.getChildren().removeAll(unitNodes);
            unitLabelControlNode = new UnitLabelControl().render(gridPane);
            gridPane.add(unitLabelControlNode, 0, 0);
            for (Unit unit : units) {
                HBox hBoxToAdd = new UnitControl().render(unit);
                unitNodes.add(hBoxToAdd);
                gridPane.add(hBoxToAdd, 0, units.indexOf(unit) + 1);
            }
            gridPane.add(addNewUnitControlNode, 0, units.size() + 1);
        });

        battlesViewButton = new Button("Battles");
        battlesViewButton.setFont(new Font(18));
        battlesViewButton.setPrefHeight(25);
        battlesViewButton.setPrefWidth(90);
        battlesViewButton.setOnAction(event -> {
            gridPane.getChildren().removeAll(unitNodes);
            gridPane.getChildren().removeAll(battleNodes);
            gridPane.getChildren().removeAll(
                    unitLabelControlNode, addNewUnitControlNode,
                    battleLabelControl[0], addNewBattleControlNode);
            battleLabelControl[0] = new BattleLabelControl(gridPane);
            gridPane.getChildren().add(battleLabelControl[0]);
            for (Battle battle : battles) {
                HBox hBoxToAdd = new BattleControl(battle);
                battleNodes.add(hBoxToAdd);
                gridPane.add(hBoxToAdd, 0, battles.indexOf(battle) + 1);
            }
            gridPane.add(addNewBattleControlNode, 0, battles.size() + 1);
        });

        Button loadFromFileButton = getLoadFromFileButton();
        return new VBox(10, loadFromFileButton, saveToFileButton, unitsViewButton, battlesViewButton);
    }

    private static Button getLoadFromFileButton() {
        Button loadFromFileButton = new Button("Load");
        loadFromFileButton.setFont(new Font(18));
        loadFromFileButton.setPrefHeight(25);
        loadFromFileButton.setPrefWidth(90);
        loadFromFileButton.setOnAction(event -> {
            Input.loadFromFile();
        });
        return loadFromFileButton;
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

    public static Collection<Object> getUnits() {
        return Collections.singleton(units);
    }

    public static Collection<Object> getBattles() {
        return Collections.singleton(battles);
    }

    public static ArrayList<Battle> getBattlesArrayList() {
        return new ArrayList<>(battles);
    }
    public static ArrayList<Unit> getUnitsArrayList() {
        return new ArrayList<>(units);
    }

    public static ArrayList<Comparable> getUnitsComparable(){
        return new ArrayList<Comparable>(units);
    }
    public static ArrayList<Comparable> getBattlesComparable() {
        return new ArrayList<Comparable>(battles);
    }

    public static ArrayList getUnitNodes() {
        return (ArrayList<Node>) unitNodes;
    }

    public static Node getAddNewUnitControlNode() {
        return addNewUnitControlNode;
    }

    public static ArrayList getBattleNodes() {
        return (ArrayList<Node>) battleNodes;
    }

    public static Node getAddNewBattleControlNode() {
        return addNewBattleControlNode;
    }

    public static int findUnitIndex(String unitName) {
        for (Unit unit : units) {
            if (unit.getUnitName().equals(unitName)) {
                return units.indexOf(unit);
            }
        }
        return -1;
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
