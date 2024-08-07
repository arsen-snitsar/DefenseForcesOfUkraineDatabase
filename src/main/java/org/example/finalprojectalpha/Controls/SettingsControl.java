package org.example.finalprojectalpha.Controls;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.finalprojectalpha.Data.Settings;
import org.example.finalprojectalpha.App;

import java.sql.SQLOutput;

public class SettingsControl {

    public static VBox getSearchControl() {
        Text searchSettingsLabel = new Text("Search settings");
        searchSettingsLabel.setFont(new Font(18));

        RadioButton radioButtonBinarySearch = new RadioButton("Use binary search - faster algorithm, but requires 1 by 1 matching");
        radioButtonBinarySearch.setFont(new Font(18));
        radioButtonBinarySearch.setOnAction(e -> {
            Settings.useBinarySearch();
        });

        RadioButton radioButtonContainsSearch = new RadioButton("Use search with contains - slower, but much more convenient and can be used with keywords");
        radioButtonContainsSearch.setFont(new Font(18));
        radioButtonContainsSearch.setOnAction(e -> {
            Settings.useContainsSearch();
        });

        System.out.println("use binary search: " + Settings.getUseBinarySearch());
        if (Settings.getUseBinarySearch())
            radioButtonBinarySearch.setSelected(true);
        else
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
        interfaceSettingsText.setFont(new Font(18));

        RadioButton barButton = new RadioButton("Use menu bar - a classical File | Edit | View bar ");
        barButton.setFont(new Font(18));
        barButton.setOnAction(_ -> {
            Settings.useMenuBar();
            App.viewSettings();
        });

        RadioButton bigButtons = new RadioButton("Use big buttons - modern " +
                "and more appropriate for touch screen devices");
        bigButtons.setFont(new Font(18));
        bigButtons.setOnAction(_ -> {
            Settings.useButtons();
        });

        ToggleGroup interfaceGroup = new ToggleGroup();
        barButton.setToggleGroup(interfaceGroup);
        bigButtons.setToggleGroup(interfaceGroup);


        System.out.println("use menu bar: " + Settings.getUseMenuBar());
        if (Settings.getUseMenuBar())
            barButton.setSelected(true);
        else
            bigButtons.setSelected(true);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(interfaceSettingsText, barButton, bigButtons);
        return vbox;
    }

}
