package org.example.finalprojectalpha.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Battle {

    private String battleName;
    private int battleId;
    private String imagePath;
    private static int battleCount = 0;
    private MyQueue<BattleEvent> battleFlow = new MyQueue<BattleEvent>();
    private ArrayList<Unit> unitsInvolved = new ArrayList<Unit>();

    public String getImagePath() {
        return imagePath;
    }

    public Battle(String battleName) {
        this.battleName = battleName;
        this.battleId = battleCount;
        battleCount++;
    }
}