package org.example.finalprojectalpha.Files;

import org.example.finalprojectalpha.Data.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input {

    public static void loadFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null && (!line.equals("Units:"))) {
                int dotIndex = line.indexOf(". ");
                if (dotIndex != -1) {
                    String battleName = line.substring(dotIndex + 2, line.indexOf("|"));
                    if (!Battles.contains(battleName)) {
                        String imagePath = line.substring(line.indexOf("|") + 1);
                        Battles.add(new Battle(battleName, imagePath));
                    }
                }
            }
            while ((line = reader.readLine()) != null && (!line.equals("Relationships:"))) {
                int dotIndex = line.indexOf(". ");
                if (dotIndex != -1) {
                    String name = line.substring(dotIndex + 2, line.indexOf("|"));
                    if (!Units.contains(name)) {
                        String insigniaPath = line.substring(line.indexOf("|") + 1);
                        Units.add(new Unit(name, insigniaPath));
                    }
                }
            }
            while ((line = reader.readLine()) != null && (!line.equals("Battle flows:"))) {
                String unitName = line.substring(0, line.indexOf(":"));
                Unit unit = Units.get(unitName);
                if (unit != null) {
                    String[] battles = line.substring(line.indexOf(":") + 2).split(", ");
                    for (String battleName : battles) {
                        if (Battles.findIndex(battleName) == -1) {
                            continue;
                        }
                        Battle battle = Battles.getArrayList().get(Battles.findIndex(battleName));
                        if (battle != null
                                && !unit.getBattlesParticipated().contains(battle))
                            unit.addBattleParticipated(battle);
                        if (battle != null
                                && !battle.getUnitsInvolved().contains(unit))
                            battle.addUnitInvolved(unit);
                    }
                }
            }
            while ((line = reader.readLine()) != null && (!line.equals("Settings:"))) {
                String battleName = line.substring(0, line.indexOf(":"));
                Battle battle = Battles.getArrayList().get(Battles.findIndex(battleName));
                if (battle != null) {
                    String[] events = line.substring(line.indexOf(":") + 2).split(", ");
                    for (String event : events) {
                        if (!battle.containsEvent(event))
                            battle.addEvent(event);
                    }
                }
            }
            while ((line = reader.readLine()) != null && (!line.equals("End"))) {
                if (line.substring(0, line.indexOf(" - ")).equals("Use binary search")) {
                    if (line.substring(line.indexOf("- ") + 2).equals("true")) {
                        // Settings.useBinarySearch();
                    } else {
                        // Settings.useContainsSearch();
                    }
                }
                if (line.substring(0, line.indexOf(" - ")).equals("Use menu bar"))
                    if (line.substring(line.indexOf("- ") + 2).equals("true")) {
                        // Settings.useMenuBar();
                    } else {
                        // Settings.useButtons();
                    }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
