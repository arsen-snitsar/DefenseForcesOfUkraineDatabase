package org.example.finalprojectalpha.Files;

import org.example.finalprojectalpha.Data.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Output {

    private static final String data = "data.txt";

    public static void clearFile(){
        try {
            FileWriter fileWriter = new FileWriter(data, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void saveBattles() {
        try {
            FileWriter fileWriter = new FileWriter(data, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Battles:");
            for (Battle battle : Battles.getArrayList()) {
                String imagePath = battle.getImagePath();
                if (imagePath != null && !imagePath.equals("null")) {
                    printWriter.println(battle.getId() + ". " + battle.getName() + "|" + imagePath);
                } else {
                    printWriter.println(battle.getId() + ". " + battle.getName() + "|null");
                }
            }
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveUnits() {
        try {
            FileWriter fileWriter = new FileWriter(data, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Units:");
            for (Unit unit : Units.getArrayList()) {
                String insigniaPath = unit.getInsigniaPath();
                if (insigniaPath != null && !insigniaPath.equals("null")) {
                    printWriter.println(unit.getUnitId() + ". " + unit.getName() + "|" + insigniaPath);
                } else {
                    printWriter.println(unit.getUnitId() + ". " + unit.getName() + "|null");
                }
            }
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveRelationships() {
        try {
            FileWriter fileWriter = new FileWriter(data, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Relationships:");
            for (Unit unit : Units.getArrayList()) {
                if (unit.getBattlesParticipated().isEmpty()) {
                    continue;
                }
                printWriter.print(unit.getName() + ": ");
                for (Battle battle : unit.getBattlesParticipated()) {
                    printWriter.print(battle.getName() + ", ");
                }
                printWriter.println();
            }
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveFlows() {
        try {
            FileWriter fileWriter = new FileWriter(data, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Battleflows:");
            for (Battle battle : Battles.getArrayList()) {
                if (battle.getFlow().isEmpty()) {
                    continue;
                }
                printWriter.print(battle.getName() + ": ");
                for (int i = 0; i < battle.getFlow().getSize(); i++) {
                    printWriter.print(battle.getFlow().get(i).getEventText() + ", ");
                }
                printWriter.println();
            }
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeEnd(){
        try{
            FileWriter fileWriter = new FileWriter(data, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("End");
            printWriter.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void saveToFile() {
        clearFile();
        saveBattles();
        saveUnits();
        saveRelationships();
        saveFlows();
        writeEnd();
    }
}