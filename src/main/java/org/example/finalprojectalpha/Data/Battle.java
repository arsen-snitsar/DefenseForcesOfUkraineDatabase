package org.example.finalprojectalpha.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Battle {

    private String battleName;
    private int battleId;
    private static int battleCount = 0;
    private MyQueue<BattleEvent> battleFlow = new MyQueue<BattleEvent>();
    private ArrayList<Unit> units = new ArrayList<Unit>();

    public Battle(String battleName) {
        this.battleName = battleName;
        this.battleId = battleCount;
        battleCount++;
    }
}