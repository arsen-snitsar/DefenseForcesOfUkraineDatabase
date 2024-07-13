package org.example.finalprojectalpha.Controls;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.example.finalprojectalpha.Files.Input;
import org.example.finalprojectalpha.App;

import static org.example.finalprojectalpha.App.*;

public class BarControl extends MenuBar {

    public BarControl() {
        Menu fileMenu = new Menu("File");

        MenuItem loadItem = new MenuItem("Load | Ctrl + O");
        loadItem.setOnAction(_ -> Input.loadFromFile());
        fileMenu.getItems().add(loadItem);

        MenuItem saveItem = new MenuItem("Save | Ctrl + S");
        saveItem.setOnAction(_ -> App.save());
        fileMenu.getItems().add(saveItem);

        MenuItem quit = new MenuItem("Quit | Alt + F4");
        quit.setOnAction(_ -> primaryStage.close());
        fileMenu.getItems().add(quit);

        Menu editMenu = new Menu("Edit");
        MenuItem settings = new MenuItem("Settings | Ctrl + E");
        settings.setOnAction(_ -> {
            App.viewSettings();
        });
        editMenu.getItems().add(settings);

        Menu viewMenu = new Menu("View");
        MenuItem units = new MenuItem("Units | Ctrl + U");
        units.setOnAction(_ -> viewUnits());
        viewMenu.getItems().add(units);

        MenuItem battles = new MenuItem("Battles | Ctrl + B");
        battles.setOnAction(_ -> viewBattles());
        viewMenu.getItems().add(battles);

        this.getMenus().addAll(fileMenu, editMenu, viewMenu);
    }

}
