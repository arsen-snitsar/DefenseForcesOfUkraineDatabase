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
            for (Battle battle : Battles.getList()) {
                String imagePath = battle.getImagePath();
                if (imagePath != null && !imagePath.equals("null")) {
                    printWriter.println(battle.getId() + ". " + battle.getName() + "|" + imagePath);
                } else {
                    printWriter.println(battle.getId() + ". " + battle.getName() + "|null");
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
                     printWriter.print(battle.getName() + ", ");
                }
                printWriter.println();
            }
            printWriter.println("Battleflows:");
            for (Battle battle : battles) {
                if (battle.getFlow().isEmpty()) {
                    continue;
                }
                printWriter.print(battle.getName() + ": ");
                for (int i = 0; i < battle.getFlow().getSize(); i++) {
                    printWriter.print(battle.getFlow().get(i).getEventText() + ", ");
                }
                printWriter.println();
            }
            printWriter.println("End");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}