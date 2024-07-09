package org.example.finalprojectalpha.Controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Battle;

public class BattleControl extends HBox {

    public BattleControl(Battle battle){
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

        this.getChildren().addAll(
                getInsigniaView(battle),
                getBattleNameText(battle),
                getButtonMore(battle)
        );
    }

    private Node getBattleNameText(Battle battle) {
        Text battleNameText = new Text(battle.getName());
        battleNameText.setFont(App.defFont);
        battleNameText.setTextAlignment(TextAlignment.LEFT);

        battleNameText.setWrappingWidth(myGetPrefWidth());
        VBox container = new VBox(battleNameText);
        container.setAlignment(Pos.CENTER_LEFT);

        return container;
    }

    public static double myGetPrefWidth() {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        return visualBounds.getWidth() - 400;
    }

    private ImageView getInsigniaView(Battle battle) {
        ImageView battleImageInsignia;
        if (battle.getImagePath() != null && !battle.getImagePath().equals("null")) {
            Image battleImage = new Image(battle.getImagePath());
            battleImageInsignia = new ImageView(battleImage);
        } else {
            Image battleImageNotFound = new Image(App.class.getResource("noBattleImageFound.jpg").toExternalForm());
            battleImageInsignia = new ImageView(battleImageNotFound);
        }
        battleImageInsignia.setFitWidth(100);
        battleImageInsignia.setPreserveRatio(true);
        return battleImageInsignia;
    }

    private Button getButtonMore(Battle battle) {
        Button moreButton = new Button("More");
        moreButton.setFont(App.defFont);
        moreButton.setOnAction(_ -> {
            BattleMoreControl battleMoreControl = new BattleMoreControl();
            Scene scene = battleMoreControl.render(battle);
            App.primaryStage.setScene(scene);
        });
        return moreButton;
    }
}
