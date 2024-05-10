package org.example.finalprojectalpha.Controls;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Unit;

public class UnitEditControl {

    public Scene render(Unit unit) {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane);
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
        gridPane.getChildren().add(insigniaView);

        return scene;
    }

}
