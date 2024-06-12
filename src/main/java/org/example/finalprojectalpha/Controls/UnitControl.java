package org.example.finalprojectalpha.Controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Unit;

import java.io.File;

public class UnitControl extends HBox {

    private static final Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
    private static double prefWidth = visualBounds.getWidth() - 400;

    public static double myGetPrefWidth() {
        return prefWidth;
    }

    private Button getButtonMore(Unit unit) {
        Button moreButton = new Button("More");
        moreButton.setFont(new Font(18));
        moreButton.setAlignment(Pos.CENTER_RIGHT);
        TextField editNameField = new TextField(unit.getUnitName());
        editNameField.setFont(new Font(18));
        editNameField.setAlignment(Pos.CENTER_LEFT);
        editNameField.setPrefWidth(myGetPrefWidth());
        moreButton.setOnAction(event -> {
            UnitMoreControl unitMoreControl = new UnitMoreControl();
            Scene scene = unitMoreControl.render(unit);
            App.primaryStage.setScene(scene);
        });
        return moreButton;
    }

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

    private Label getUnitNameLabel (Unit unit){
        Label unitNameLabel = new Label(unit.getUnitName());
        unitNameLabel.setFont(new Font(18));
        unitNameLabel.setAlignment(Pos.CENTER_LEFT);
        unitNameLabel.setPrefWidth(myGetPrefWidth());
        return unitNameLabel;
    }

    public HBox render(Unit unit) {
        HBox hBoxToReturn = new HBox();

        hBoxToReturn.getChildren().addAll(
                getInsigniaView(unit),
                getUnitNameLabel(unit),
                getButtonMore(unit)
        );

        hBoxToReturn.setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                new BorderWidths(1)
                        )
                )
        );
        hBoxToReturn.setPadding(new Insets(10));
        hBoxToReturn.setAlignment(Pos.CENTER_LEFT);
        hBoxToReturn.setSpacing(5);
        return hBoxToReturn;
    }



}
