package org.example.finalprojectalpha.Controls;

import javafx.scene.layout.*;
import org.example.finalprojectalpha.Data.Battle;
import org.example.finalprojectalpha.Data.BattleEvent;

public class BattleFlowControl extends VBox {

    public BattleFlowControl(Battle battle) {
        this.getChildren().add(new BattleFlowLabel());
        while (!battle.getBattleFlow().isEmpty()) {
            this.getChildren().add(new BattleEventControl(battle.dequeueBattleEvent()));
        }
    }

    public void addBattleEvent(String battleEventText, Battle battle){
        BattleEvent battleEvent = new BattleEvent(battleEventText);
        battle.enqueueBattleEvent(battleEvent);
        this.getChildren().add(new BattleEventControl(battleEvent));
    }

    public void addAddNewBattleEventControl(Battle battle){
        this.getChildren().add(new AddNewBattleEventControl(this, battle));
    }

    public void removeAddNewBattleEventControl(){
        this.getChildren().removeLast();
    }

}