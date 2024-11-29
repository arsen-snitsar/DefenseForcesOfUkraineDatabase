package org.example.finalprojectalpha.Data;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Styles {

    private static final int buttonWidth = 95;
    private static final int buttonHeight = 25;

    public static void setButtonGraphics(Button button, boolean isLong) {
        button.setFont(new Font(18));
        button.setPrefHeight(buttonHeight);
        if (isLong) button.setPrefWidth(buttonWidth + 100);
        else button.setPrefWidth(buttonWidth);
        button.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web(Colors.getDark()),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );
        button.setTextFill(Color.web(Colors.getNeon()));
        button.setBorder(
                new Border(
                        new BorderStroke(
                                Color.web(Colors.getNeon()),
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                new BorderWidths(2)
                        )
                )
        );
        button.setOnMouseEntered(_ -> {
            button.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.web(Colors.getNeon()),
                                    CornerRadii.EMPTY,
                                    Insets.EMPTY
                            )
                    )
            );
            button.setTextFill(Color.web(Colors.getDark()));
        });
        button.setOnMouseExited(_ -> {
            button.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.web(Colors.getDark()),
                                    CornerRadii.EMPTY,
                                    Insets.EMPTY
                            )
                    )
            );
            button.setTextFill(Color.web(Colors.getNeon()));
        });
    }

}
