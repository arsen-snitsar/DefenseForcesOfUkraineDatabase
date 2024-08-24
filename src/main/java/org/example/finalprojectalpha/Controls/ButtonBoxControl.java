package org.example.finalprojectalpha.Controls;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Files.Input;

public class ButtonBoxControl extends VBox {

    private static final int buttonWidth = 95;
    private static final int buttonHeight = 25;

    public static void setButtonGraphics(Button button) {
        button.setFont(new Font(18));
        button.setPrefHeight(buttonHeight);
        button.setPrefWidth(buttonWidth);
    }

    private static Button getLoadFromFileButton() {
        Button button = new Button("Load");
        setButtonGraphics(button);
        button.setOnAction(_ -> Input.loadFromFile());
        return button;
    }
    private static Button getSaveToFileButton() {
        Button button = new Button("Save");
        setButtonGraphics(button);
        button.setOnAction(_ -> App.save());
        return button;
    }
    private static Button getUnitsViewButton() {
        Button button = new Button("Units");
        setButtonGraphics(button);
        button.setOnAction(_ -> App.viewUnits());
        return button;
    }
    private static Button getBattlesViewButton() {
        Button button = new Button("Battles");
        setButtonGraphics(button);
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
