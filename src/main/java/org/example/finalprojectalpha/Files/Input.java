package org.example.finalprojectalpha.Files;

import org.example.finalprojectalpha.Data.Unit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Input {

    public static List<Unit> loadFromFile() {
        List<Unit> units = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("units.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                int dotIndex = line.indexOf(". ");
                if (dotIndex != -1) {
                    String unitName = line.substring(dotIndex + 2, line.indexOf("|"));
                    String insigniaPath = line.substring(line.indexOf("|") + 1);
                    units.add(new Unit(unitName, insigniaPath));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return units;
    }
}
