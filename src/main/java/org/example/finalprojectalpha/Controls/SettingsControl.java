package org.example.finalprojectalpha.Controls;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.finalprojectalpha.Data.Settings;

public class SettingsControl {

    public static VBox getSearchControl() {
        Text searchSettingsLabel = new Text("Search settings");
        searchSettingsLabel.setFont(new Font(18));

        RadioButton radioButtonBinarySearch = new RadioButton("Use binary search - faster algorithm, but requires 1 by 1 matching");
        radioButtonBinarySearch.setFont(new Font(18));
        radioButtonBinarySearch.setOnAction(e ->{
            Settings.useBinarySearch();
        });

        RadioButton radioButtonContainsSearch = new RadioButton("Use search with contains - slower, but much more convenient and can be used with keywords");
        radioButtonContainsSearch.setFont(new Font(18));
        radioButtonContainsSearch.setOnAction(e ->{
            Settings.useContainsSearch();
        });

        radioButtonContainsSearch.setSelected(true);

        ToggleGroup searchToggleGroup = new ToggleGroup();
        radioButtonBinarySearch.setToggleGroup(searchToggleGroup);
        radioButtonContainsSearch.setToggleGroup(searchToggleGroup);

        VBox vBoxToReturn = new VBox();
        vBoxToReturn.getChildren().addAll(searchSettingsLabel, radioButtonBinarySearch, radioButtonContainsSearch);
        return vBoxToReturn;
    }

}
