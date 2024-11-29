package org.example.finalprojectalpha.Controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Colors;
import org.example.finalprojectalpha.Data.Styles;
import org.example.finalprojectalpha.Data.Unit;

public class UnitControl extends HBox {

    private static final Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
    private static double prefWidth = visualBounds.getWidth() - 400;

    public static double myGetPrefWidth() {
        return prefWidth;
    }

    private Button getButtonMore(Unit unit) {
        Button button = new Button("More");
        Styles.setButtonGraphics(button, false);
        button.setAlignment(Pos.CENTER);
        TextField editNameField = new TextField(unit.getName());
        editNameField.setFont(new Font(18));
        editNameField.setAlignment(Pos.CENTER_LEFT);
        editNameField.setPrefWidth(myGetPrefWidth());
        button.setOnAction(_ -> {
            UnitMoreControl unitMoreControl = new UnitMoreControl();
            Scene scene = unitMoreControl.render(unit);
            App.primaryStage.setScene(scene);
        });
        return button;
    }

    private ImageView getInsigniaView(Unit unit) {
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

    private Node getUnitNameText(Unit unit) {
        Text name = new Text(unit.getName());
        name.setFont(new Font(18));
        name.setTextAlignment(TextAlignment.LEFT);
        name.setFill(Color.web(Colors.getNeon()));

        name.setWrappingWidth(myGetPrefWidth());
        name.setWrappingWidth(myGetPrefWidth());
        VBox box = new VBox(name);
        box.setAlignment(Pos.CENTER_LEFT);

        return box;
    }

    public UnitControl(Unit unit) {
        this.getChildren().addAll(
                getInsigniaView(unit),
                getUnitNameText(unit),
                getButtonMore(unit)
        );
        this.setBorder(
                new Border(
                        new BorderStroke(
                                Color.web(Colors.getNeon()),
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                new BorderWidths(2)
                        )
                )
        );
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(5);
        this.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web(Colors.getDark()),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );
    }
}