package org.example.finalprojectalpha.Controls;

import javafx.scene.layout.*;
import org.example.finalprojectalpha.Data.BattleEvent;

public class BattleFlowControl extends VBox {

    public BattleFlowControl() {
        this.getChildren().add(new BattleFlowLabel());
    }

    public void addBattleEvent(String battleEventText){
        this.getChildren().add(new BattleEventControl(new BattleEvent(battleEventText)));
    }

    public void addAddNewBattleEventControl(){
        this.getChildren().add(new AddNewBattleEventControl(this));
    }

    public void removeAddNewBattleEventControl(){
        this.getChildren().removeLast();
    }

}