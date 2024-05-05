package org.example.finalprojectalpha;

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
import org.example.finalprojectalpha.Controls.AddNewUnitControl;
import org.example.finalprojectalpha.Controls.UnitControl;
import org.example.finalprojectalpha.Data.Unit;
import org.example.finalprojectalpha.files.Input;
import org.example.finalprojectalpha.files.Output;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private static final List<Unit> units = new ArrayList<Unit>();
    private static final List<Node> unitNodes = new ArrayList<>();
    private static final GridPane gridPane = new GridPane();
    private static final Node addNewUnitControlNode = new AddNewUnitControl().render();

    public static void addNewUnit(String newUnitName) {
        units.add(new Unit(newUnitName));
        Node unitNode = new UnitControl().render(units.getLast());
        gridPane.add(unitNode, 0, units.size());
        unitNodes.add(unitNode);

        gridPane.getChildren().removeAll(addNewUnitControlNode);
        gridPane.add(addNewUnitControlNode, 0, units.size() + 1);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));

        Scene scene = new Scene(vBox, 1280, 720);

        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Defense Forces of Ukraine Database");
        primaryStage.setMaximized(true);
        primaryStage.show();


        HBox topBox = new HBox(10);
        topBox.setPadding(new Insets(10));
        HBox lowerBox = new HBox(10);
        lowerBox.setPadding(new Insets(10));

        ImageView coatOfArmsView = new ImageView(
                new Image(
                        getClass().getResource("CoatOfArms.png").toExternalForm()
                )
        );
        coatOfArmsView.setFitHeight(150);
        coatOfArmsView.setFitWidth(150);
        topBox.getChildren().add(coatOfArmsView);
        Label defenseForcesOfUkraineLabel = new Label("Defense Forces of Ukraine");
        defenseForcesOfUkraineLabel.setFont(new Font(36));
        defenseForcesOfUkraineLabel.setAlignment(Pos.CENTER);
        topBox.setBackground(
                new Background(
                        new BackgroundFill(Color.web("#D9D9D9"), CornerRadii.EMPTY, Insets.EMPTY)
                ));
        topBox.setAlignment(Pos.CENTER_LEFT);

        topBox.getChildren().add(defenseForcesOfUkraineLabel);

        vBox.getChildren().add(topBox);

        HBox unitsLabel = new UnitControl().render(new Unit("Unit Insignia | Unit name"));
        unitsLabel.getChildren().remove(0);
        unitsLabel.getChildren().remove(1);
        gridPane.add(unitsLabel, 0, 0);
        gridPane.add(addNewUnitControlNode, 0, 1);

        Button loadFromFileButton = new Button("Load");
        loadFromFileButton.setFont(new Font(36));
        loadFromFileButton.setPrefHeight(50);
        loadFromFileButton.setPrefWidth(140);

        Button saveToFileButton = new Button("Save");
        saveToFileButton.setFont(new Font(36));
        saveToFileButton.setPrefHeight(50);
        saveToFileButton.setPrefWidth(140);
        saveToFileButton.setOnAction(event -> {
            Output.saveToFile(units);
        });

        VBox buttonBox = new VBox(10, loadFromFileButton, saveToFileButton);

        lowerBox.getChildren().addAll(buttonBox, gridPane);
        vBox.getChildren().add(lowerBox);

        loadFromFileButton.setOnAction(event -> {

            if (!units.isEmpty()) {
                for (Node unitNode : unitNodes) {
                    gridPane.getChildren().remove(unitNode);
                }
            }
            units.clear();
            units.addAll(Input.loadFromFile());

            for (Unit unit : units) {
                gridPane.add(new UnitControl().render(unit), 0, units.indexOf(unit) + 1);
                gridPane.getChildren().removeAll(addNewUnitControlNode);
                gridPane.add(addNewUnitControlNode, 0, units.size() + 1);
            }
        });

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
    }
}
