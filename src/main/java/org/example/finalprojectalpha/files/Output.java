package org.example.finalprojectalpha.files;

import org.example.finalprojectalpha.Data.Unit;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Output {

    public static void saveToFile(List<Unit> units){
        try {
            FileWriter fileWriter = new FileWriter("units.txt", true);
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