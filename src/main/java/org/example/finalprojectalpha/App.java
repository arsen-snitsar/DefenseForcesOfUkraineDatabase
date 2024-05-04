package org.example.finalprojectalpha;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.finalprojectalpha.Controls.UnitControl;
import org.example.finalprojectalpha.Data.Unit;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private final List<Unit> units = new ArrayList<Unit>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane root = new GridPane();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Defense Forces of Ukraine Database");
        primaryStage.show();

        TextField newUnitNameField = new TextField();
        newUnitNameField.setPromptText("Enter new unit name");
        root.add(newUnitNameField, 0, 0);
        Button addNewUnitButton = new Button("Add new unit");
        root.add(addNewUnitButton, 0, 1);

        addNewUnitButton.setOnAction(event -> {
            String newUnitName = newUnitNameField.getText();
            newUnitNameField.clear();
            units.add(new Unit(newUnitName));
            root.add(new UnitControl().render(units.getLast()), 0, units.size());
            root.getChildren().remove(newUnitNameField);
            root.getChildren().remove(addNewUnitButton);
            root.add(newUnitNameField, 0, units.size() + 1);
            root.add(addNewUnitButton, 0, units.size() + 2);
            System.out.println("New unit name: " + newUnitName);
        });
    }
}
