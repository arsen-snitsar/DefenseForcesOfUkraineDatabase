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
import javafx.stage.Screen;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Unit;

public class UnitControl extends HBox {

    private static final Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
    private static double prefWidth = visualBounds.getWidth() - 400;

    public static double myGetPrefWidth() {
        return prefWidth;
    }

    private Button getButtonMore(Unit unit) {
        Button moreButton = new Button("More");
        moreButton.setFont(App.defFont);
        moreButton.setAlignment(Pos.CENTER_RIGHT);
        TextField editNameField = new TextField(unit.getName());
        editNameField.setFont(App.defFont);
        editNameField.setAlignment(Pos.CENTER_LEFT);
        editNameField.setPrefWidth(myGetPrefWidth());
        moreButton.setOnAction(_ -> {
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
        Label unitNameLabel = new Label(unit.getName());
        unitNameLabel.setFont(App.defFont);
        unitNameLabel.setAlignment(Pos.CENTER_LEFT);
        unitNameLabel.setPrefWidth(myGetPrefWidth());
        return unitNameLabel;
    }

    public UnitControl(Unit unit){
        this.getChildren().addAll(
                getInsigniaView(unit),
                getUnitNameLabel(unit),
                getButtonMore(unit)
        );
        this.setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                new BorderWidths(1)
                        )
                )
        );
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(5);


    }



}
