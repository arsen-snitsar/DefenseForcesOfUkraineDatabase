package org.example.finalprojectalpha;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.finalprojectalpha.Controls.UnitControl;
import org.example.finalprojectalpha.Data.Unit;
import org.example.finalprojectalpha.files.Output;

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
        root.add(newUnitNameField, 0, 2);
        Button addNewUnitButton = new Button("Add new unit");
        root.add(addNewUnitButton, 0, 3);

        Image coatOfArms = new Image(getClass().getResource("CoatOfArms.png").toExternalForm());
        ImageView coatOfArmsView = new ImageView(coatOfArms);
        coatOfArmsView.setFitHeight(150);
        coatOfArmsView.setFitWidth(150);
        root.add(coatOfArmsView, 0,0);

        addNewUnitButton.setOnAction(event -> {

            String newUnitName = newUnitNameField.getText();
            newUnitNameField.clear();
            units.add(new Unit(newUnitName));
            root.add(new UnitControl().render(units.getLast()), 0, units.size()+1);

            root.getChildren().removeAll(newUnitNameField, addNewUnitButton);
            root.add(newUnitNameField, 0, units.size() + 2);
            root.add(addNewUnitButton, 0, units.size() + 3);

        });

        Button saveToFileButton = new Button("Save to file");
        root.add(saveToFileButton, 0, 1);
        saveToFileButton.setOnAction(event -> {
            Output.saveToFile(units);
        });
    }
}
