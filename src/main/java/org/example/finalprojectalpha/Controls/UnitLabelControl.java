package org.example.finalprojectalpha.Controls;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.finalprojectalpha.Data.Unit;

public class UnitLabelControl extends UnitControl {

    public HBox render(){
        Stage stage = new Stage();
        HBox hBoxToReturn = new UnitControl().render(new Unit("Unit Insignia | Unit name"), stage);
        hBoxToReturn.getChildren().remove(0);
        hBoxToReturn.getChildren().remove(1);
        return hBoxToReturn;
    }
}
