package org.example.finalprojectalpha.Files;

import org.example.finalprojectalpha.Data.Unit;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Output {

    public static void saveToFile(List<Unit> units){
        try {
            FileWriter fileWriter = new FileWriter("units.txt", false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Unit unit : units) {
                printWriter.println(unit.getUnitId() + ". " + unit.getUnitName());
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}