package org.example.finalprojectalpha.Controls;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Battle;
import org.example.finalprojectalpha.Data.Unit;

public class BattleControl extends HBox {

    public BattleControl(Battle battle, Stage primaryStage){
        this.getChildren().addAll(
                getInsigniaView(battle));
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
}
