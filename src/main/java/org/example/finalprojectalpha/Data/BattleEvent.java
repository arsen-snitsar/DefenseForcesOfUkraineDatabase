package org.example.finalprojectalpha.Data;

import javafx.scene.text.Text;

public class BattleEvent {
    private int battleEventId;
    private String eventText;
    private static int battleEventCount = 0;
    private int day;
    private int month;
    private int year;

    public BattleEvent(String newBattleEventText) {
        this.eventText = newBattleEventText;
        this.battleEventId = battleEventCount;
        battleEventCount++;
    }

    public String getEventText() {
        return eventText;
    }

}
