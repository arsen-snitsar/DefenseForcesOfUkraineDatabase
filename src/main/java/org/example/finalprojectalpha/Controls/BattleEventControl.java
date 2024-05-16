package org.example.finalprojectalpha.Controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.finalprojectalpha.Data.BattleEvent;

public class BattleEventControl extends HBox {

    public BattleEventControl(BattleEvent battleEvent) {
        Text text = new Text(battleEvent.getEventText());
        text.setFont(new Font(18));
        this.getChildren().add(text);
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
