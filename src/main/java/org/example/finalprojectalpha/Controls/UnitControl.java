package org.example.finalprojectalpha.Controls;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.example.finalprojectalpha.Data.Unit;

public class UnitControl extends HBox {

    public HBox render(Unit unit) {
        HBox hBoxToReturn = new HBox();
        hBoxToReturn.getChildren().add(
                new Label(unit.getUnitName())
        );
        return hBoxToReturn;
    }

}
