package org.example.finalprojectalpha.Files;

import org.example.finalprojectalpha.App;
import org.example.finalprojectalpha.Data.Battle;
import org.example.finalprojectalpha.Data.Battles;
import org.example.finalprojectalpha.Data.Unit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.example.finalprojectalpha.App.findUnitIndex;

public class Input {

    public static void loadFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
            String line = reader.readLine();

            Set<String> uniqueBattleNames = new HashSet<>();
            Set<String> uniqueUnitNames = new HashSet<>();

            while ((line = reader.readLine()) != null && (!line.equals("Units:"))) {
                int dotIndex = line.indexOf(". ");
                if (dotIndex != -1) {
                    String battleName = line.substring(dotIndex + 2, line.indexOf("|"));
                    Battle newBattle;
                    if (!uniqueBattleNames.contains(battleName)) {
                        uniqueBattleNames.add(battleName);
                        String imagePath = line.substring(line.indexOf("|") + 1);
                        newBattle = new Battle(battleName, imagePath);
                        Battles.add(newBattle);
                    }
                }
            }
            while ((line = reader.readLine()) != null && (!line.equals("Relationships:"))) {
                int dotIndex = line.indexOf(". ");
                if (dotIndex != -1) {
                    Unit newUnit;
                    String unitName = line.substring(dotIndex + 2, line.indexOf("|"));
                    if (!uniqueUnitNames.contains(unitName)) {
                        uniqueUnitNames.add(unitName);
                        String insigniaPath = line.substring(line.indexOf("|") + 1);
                        newUnit = new Unit(unitName, insigniaPath);
                        App.addNewUnit(newUnit);
                    }
                }
            }
            while ((line = reader.readLine()) != null && (!line.equals("Battleflows:"))) {
                String unitName = line.substring(0, line.indexOf(":"));
                Unit unit = App.getUnitsArrayList().get(findUnitIndex(unitName));
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
            while ((line = reader.readLine()) != null && (!line.equals("End"))) {
                String battleName = line.substring(0, line.indexOf(":"));
                Battle battle = Battles.getArrayList().get(Battles.findIndex(battleName));
                if (battle != null) {
                    String[] events = line.substring(line.indexOf(":") + 2).split(", ");
                    for (String event : events) {
                        battle.addEvent(event);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
