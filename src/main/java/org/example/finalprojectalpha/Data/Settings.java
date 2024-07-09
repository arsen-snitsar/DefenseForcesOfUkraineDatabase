package org.example.finalprojectalpha.Data;

import org.example.finalprojectalpha.App;

public class Settings {

    private static boolean useBinarySearch;
    public static void useBinarySearch() {
        useBinarySearch = true;
    }
    public static void useContainsSearch() {
        useBinarySearch = false;
    }
    public static Boolean getUseBinarySearch() {
        return useBinarySearch;
    }

    private static boolean useMenuBar = true;
    public static void useMenuBar() {
        useMenuBar = true;
        App.primaryStage.setScene(App.setMainScene());
    }
    public static void useButtons() {
        useMenuBar = false;
        App.primaryStage.setScene(App.setMainScene());
    }
    public static Boolean getUseMenuBar() {
        return useMenuBar;
    }
}
