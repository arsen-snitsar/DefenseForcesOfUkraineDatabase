package org.example.finalprojectalpha.Data;

public class Settings {

    private static boolean useBinarySearch = false;

    public static void useBinarySearch(){
        useBinarySearch = true;
    }
    public static void useContainsSearch(){
        useBinarySearch = false;
    }

    public static Boolean getUseBinarySearch(){
        return useBinarySearch;
    }

}
