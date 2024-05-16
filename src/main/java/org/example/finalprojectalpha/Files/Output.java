package org.example.finalprojectalpha.Files;

import org.example.finalprojectalpha.Data.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Output {

    public static void saveToFile(List<Unit> units, List<Battle> battles){
        try {
            FileWriter fileWriter = new FileWriter("data.txt", false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Battles:");
            for (Battle battle : battles) {
                String imagePath = battle.getImagePath();
                if (imagePath != null && !imagePath.equals("null")) {
                    printWriter.println(battle.getBattleId() + ". " + battle.getBattleName() + "|" + imagePath);
                } else {
                    printWriter.println(battle.getBattleId() + ". " + battle.getBattleName() + "|null");
                }
            }
            printWriter.println("Units:");
            for (Unit unit : units) {
                String insigniaPath = unit.getInsigniaPath();
                if (insigniaPath != null && !insigniaPath.equals("null")) {
                    printWriter.println(unit.getUnitId() + ". " + unit.getUnitName() + "|" + insigniaPath);
                } else {
                    printWriter.println(unit.getUnitId() + ". " + unit.getUnitName() + "|null");
                }
            }
            printWriter.println("Relationships:");
            for (Unit unit : units) {
                if (unit.getBattlesParticipated().isEmpty()) {
                    continue;
                }
                printWriter.print(unit.getUnitName() + ": ");
                for (Battle battle : unit.getBattlesParticipated()) {
                     printWriter.print(battle.getBattleName() + ", ");
                }
                printWriter.println();
            }
            printWriter.println("Battleflows:");
            for (Battle battle : battles) {
                if (battle.getBattleFlow().isEmpty()) {
                    continue;
                }
                printWriter.print(battle.getBattleName() + ": ");
                while (!battle.getBattleFlow().isEmpty()) {
                    printWriter.print(battle.dequeueBattleEvent().getEventText());
                }
                printWriter.println();
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}