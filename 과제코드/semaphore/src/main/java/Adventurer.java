import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Adventurer extends Thread {
    Dungeon dungeon;
    String name;
    Map<String, AtomicInteger> goldRanking;
    public Adventurer(Dungeon dungeon, String name, Map<String, AtomicInteger> goldRanking) {
        this.dungeon = dungeon;
        this.name = name;
        this.goldRanking = goldRanking;
    }
    @Override
    public void run() {
        try {
            int gold = dungeon.enter(name);
            goldRanking.get(name).addAndGet(gold);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
