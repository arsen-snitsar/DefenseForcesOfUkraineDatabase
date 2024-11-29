package org.example.finalprojectalpha.Controls;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Colors;
import org.example.finalprojectalpha.Data.Styles;
import org.example.finalprojectalpha.Files.Input;

public class ButtonBoxControl extends VBox {

    private static Button getLoadFromFileButton() {
        Button button = new Button("Load");
        Styles.setButtonGraphics(button, false);
        button.setOnAction(_ -> Input.loadFromFile());
        return button;
    }
    private static Button getSaveToFileButton() {
        Button button = new Button("Save");
        Styles.setButtonGraphics(button, false);
        button.setOnAction(_ -> App.save());
        return button;
    }
    private static Button getUnitsViewButton() {
        Button button = new Button("Units");
        Styles.setButtonGraphics(button, false);
        button.setOnAction(_ -> App.viewUnits());
        return button;
    }
    private static Button getBattlesViewButton() {
        Button button = new Button("Battles");
        Styles.setButtonGraphics(button, false);
        button.setOnAction(_ -> App.viewBattles());
        return button;
    }

    public ButtonBoxControl(){
        this.setSpacing(10);
        this.getChildren().addAll(
                getLoadFromFileButton(),
                getSaveToFileButton(),
                getUnitsViewButton(),
                getBattlesViewButton()
        );
    }
}
