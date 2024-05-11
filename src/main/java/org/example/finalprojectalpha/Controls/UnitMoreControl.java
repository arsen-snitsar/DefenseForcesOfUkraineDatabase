package org.example.finalprojectalpha.Controls;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Unit;

import static org.example.finalprojectalpha.App.primaryStage;

public class UnitMoreControl extends UnitControl {

    private ImageView getInsigniaView(Unit unit){
        ImageView insigniaView;
        if (unit.getInsigniaPath() != null && !unit.getInsigniaPath().equals("null")) {
            Image insigniaImage = new Image(unit.getInsigniaPath());
            insigniaView = new ImageView(insigniaImage);
        } else {
            Image insigniaNotFoundImage = new Image(App.class.getResource("NoImageFound.jpg").toExternalForm());
            insigniaView = new ImageView(insigniaNotFoundImage);
        }
        insigniaView.setFitWidth(100);
        insigniaView.setPreserveRatio(true);
        return insigniaView;
    }

    private Button getBackButton(Stage primaryStage){
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            primaryStage.setMaximized(true);
            primaryStage.setScene(App.getMainScene());
        });
        return backButton;
    }

    public Scene render(Unit unit) {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 1920, 1080);
        gridPane.getChildren().add(getInsigniaView(unit));
        gridPane.getChildren().add(getBackButton(primaryStage));
        return scene;
    }

}
