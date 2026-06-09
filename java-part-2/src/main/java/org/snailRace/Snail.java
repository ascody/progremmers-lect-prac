package org.snailRace;

import java.util.Random;

public class Snail extends Thread {
    private Race race;
    private String name;
    private int position = 0;
    private final int FINISH = 30;
    private Random rand = new Random();

    public Snail(String name, Race race) { this.name = name; this.race = race; }

    private void printProgress() {
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < position; i++) bar.append("=");
        bar.append(">");
        System.out.println(name + ": " + bar);
    }

    public void run() {
        while (position < FINISH && !race.isOver()) {
            position += rand.nextInt(3) + 1;   // 1~3칸
            printProgress();
            try { Thread.sleep(300); } catch (InterruptedException e) {}
        }
        if (position >= FINISH) race.finish(name);
    }
}
