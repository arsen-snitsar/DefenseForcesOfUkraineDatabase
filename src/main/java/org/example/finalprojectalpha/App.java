package org.example.finalprojectalpha;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import org.example.finalprojectalpha.Controls.*;
import org.example.finalprojectalpha.Data.*;
import org.example.finalprojectalpha.Files.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    private static final Node addNewUnitControlNode = new AddNewUnitControl();
    private static final Node addNewBattleControlNode = new AddNewBattleControl();
    private static final Node[] settingsNodes = new Node[]{SettingsControl.getSearchControl(), SettingsControl.getInterfaceControl()};

    private static final int buttonWidth = 95;
    private static final int buttonHeight = 25;

    public static void setButtonGraphics(Button button) {
        button.setFont(new Font(18));
        button.setPrefHeight(buttonHeight);
        button.setPrefWidth(buttonWidth);
    }


    private static VBox getButtonBox() {

        Button loadButton = getLoadFromFileButton();
        Button saveButton = getSaveToFileButton();
        Button unitsViewButton = getUnitsViewButton();
        Button battlesViewButton = getBattlesViewButton();
        Button settingsButton = getSettingsButton();

        return new VBox(
                10,
                loadButton, saveButton,
                unitsViewButton, battlesViewButton,
                settingsButton
        );
    }

    private static Button getLoadFromFileButton() {
        Button button = new Button("Load");
        setButtonGraphics(button);
        button.setOnAction(_ -> Input.loadFromFile());
        return button;
    }

    private static void save(){
        if (Battles.size() > 0 || Units.size() > 0) {
            Output.saveToFile();
            System.out.println(Battles.size());
            System.out.println(Units.size());
        } else {
            Stage warning = new Stage();
            GridPane gridpane = new GridPane(500, 500);
            gridpane.setPadding(new Insets(10));
            gridpane.setVgap(10);
            gridpane.setHgap(10);
            warning.setScene(new Scene(gridpane));
            Text text = new Text("There is no data in your app. Delete all data in the file?");
            text.setFont(new Font(18));
            gridpane.add(text, 0, 0);

            Button clear = new Button("Delete all data");
            clear.setFont(new Font(18));
            clear.setOnAction(_ -> {
                Output.saveToFile();
                warning.close();
            });
            Button cancel = new Button("Cancel saving");
            cancel.setFont(new Font(18));
            cancel.setOnAction(_ -> warning.close());

            HBox box = new HBox(10);
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(clear, cancel);
            gridpane.add(box, 0, 1);
            warning.show();
        }
    }

    private static Button getSaveToFileButton() {
        Button button = new Button("Save");
        setButtonGraphics(button);
        button.setOnAction(_ -> save());
        return button;
    }

    private static Button getUnitsViewButton() {
        Button button = new Button("Units");
        setButtonGraphics(button);
        button.setOnAction(_ -> viewUnits());
        return button;
    }

    public static void viewBattles() {
        gridPane.getChildren().clear();
        Battles.clearNodes();
        Units.clearNodes();

        Node battleLabelControlNode = new BattleLabelControl(gridPane);
        gridPane.add(battleLabelControlNode, 0, 0);
        gridPane.add(addNewBattleControlNode, 0, 1);
        for (Battle battle : Battles.getList()) {
            HBox hBoxToAdd = new BattleControl(battle);
            Battles.addNode(hBoxToAdd);
            App.addBattleToGridpane(hBoxToAdd);
        }
    }

    private static Button getBattlesViewButton() {
        Button button = new Button("Battles");
        setButtonGraphics(button);
        button.setOnAction(_ -> viewBattles());
        return button;
    }

    private static Button getSettingsButton() {
        Button button = new Button("Settings");
        setButtonGraphics(button);
        button.setOnAction(_ -> {
                    gridPane.getChildren().clear();
                    gridPane.add(settingsNodes[0], 0, 0);
                    gridPane.add(settingsNodes[1], 0, 1);
                }
        );

        return button;
    }

    private static final Scene mainScene = setMainScene();

    public static Scene getMainScene() {
        return mainScene;
    }

    public static void viewUnits() {
        gridPane.getChildren().clear();
        Battles.clearNodes();
        Units.clearNodes();

        Node unitLabelControlNode = new UnitLabelControl(gridPane);
        gridPane.add(unitLabelControlNode, 0, 0);
        gridPane.add(getAddNewUnitControlNode(), 0, 1);
        for (Unit unit : Units.getArrayList()) {
            HBox hbox = new UnitControl(unit);
            Units.addNode(hbox);
            App.addUnitToGridpane(hbox);
        }
    }

    public static MenuBar getMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");

        MenuItem loadItem = new MenuItem("Load | Ctrl + O");
        loadItem.setOnAction(_ -> Input.loadFromFile());
        fileMenu.getItems().add(loadItem);

        MenuItem saveItem = new MenuItem("Save | Ctrl + S");
        saveItem.setOnAction(_ -> save());
        fileMenu.getItems().add(saveItem);

        MenuItem quit = new MenuItem("Quit | Alt + F4");
        quit.setOnAction(_ -> primaryStage.close());
        fileMenu.getItems().add(quit);

        Menu editMenu = new Menu("Edit");
        MenuItem settings = new MenuItem("Settings");
        settings.setOnAction(_ -> {
            gridPane.getChildren().clear();

            gridPane.add(settingsNodes[0], 0, 0);
            gridPane.add(settingsNodes[1], 0, 1);
        });
        editMenu.getItems().add(settings);

        Menu viewMenu = new Menu("View");
        MenuItem units = new MenuItem("Units");
        units.setOnAction(_ -> {
            viewUnits();
        });
        viewMenu.getItems().add(units);

        MenuItem battles = new MenuItem("Battles");
        battles.setOnAction(_ -> viewBattles());
        viewMenu.getItems().add(battles);

        menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu);

        return menuBar;
    }

    public static Scene setMainScene() {
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        if (Settings.getUseMenuBar())
            vBox.getChildren().add(getMenuBar());
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

        if (!Settings.getUseMenuBar())
            lowerBox.getChildren().add(getButtonBox());
        lowerBox.getChildren().add(gridPane);
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

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.S) {
                save();
                event.consume();
            }
        });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.O) {
                Input.loadFromFile();
                event.consume();
            }
        });
        return scene;
    }

    public static Node getAddNewUnitControlNode() {
        return addNewUnitControlNode;
    }

    public static Node getAddNewBattleControlNode() {
        return addNewBattleControlNode;
    }

    public static void addUnitToGridpane(Node unit) {
        gridPane.getChildren().remove(addNewUnitControlNode);
        gridPane.add(unit, 0, Units.nodesSize());
        gridPane.add(addNewUnitControlNode, 0, Units.nodesSize() + 1);
    }

    public static void addBattleToGridpane(Node battle) {
        gridPane.getChildren().remove(addNewBattleControlNode);
        gridPane.add(battle, 0, Battles.nodesSize());
        gridPane.add(addNewBattleControlNode, 0, Battles.nodesSize() + 1);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        App.primaryStage = primaryStage;
        primaryStage.setScene(setMainScene());
        primaryStage.setTitle("Defense Forces of Ukraine Database");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
