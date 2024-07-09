package org.example.finalprojectalpha.Controls;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Settings;

public class SettingsControl {

    public static VBox getSearchControl() {
        Text searchSettingsLabel = new Text("Search settings");
        searchSettingsLabel.setFont(App.defFont);

        RadioButton radioButtonBinarySearch = new RadioButton("Use binary search - faster algorithm, but requires 1 by 1 matching");
        radioButtonBinarySearch.setFont(App.defFont);
        radioButtonBinarySearch.setOnAction(_ -> {
            Settings.useBinarySearch();
        });

        RadioButton radioButtonContainsSearch = new RadioButton("Use search with contains - slower, but much more convenient and can be used with keywords");
        radioButtonContainsSearch.setFont(App.defFont);
        radioButtonContainsSearch.setOnAction(_ -> {
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

    public static VBox getInterfaceControl() {
        Text interfaceSettingsText = new Text("Interface settings");
        interfaceSettingsText.setFont(App.defFont);

        RadioButton barButton = new RadioButton("Use menu bar - a classical File | Edit | View bar ");
        barButton.setFont(App.defFont);
        barButton.setOnAction(_ -> {
            Settings.useMenuBar();
        });

        RadioButton bigButtons = new RadioButton("Use big buttons - modern " +
                "and more appropriate for touch screen devices");
        bigButtons.setFont(App.defFont);
        bigButtons.setOnAction(_ -> {
            Settings.useButtons();
        });

        ToggleGroup interfaceGroup = new ToggleGroup();
        barButton.setToggleGroup(interfaceGroup);
        bigButtons.setToggleGroup(interfaceGroup);

        barButton.setSelected(true);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(interfaceSettingsText, barButton, bigButtons);
        return vbox;
    }

}
