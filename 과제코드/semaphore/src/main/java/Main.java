import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static void main() {
        Dungeon dungeon = new Dungeon(2);   // 정원 2명짜리 던전 하나!
        String[] names = { "전사", "마법사", "궁수", "도적", "성기사" };

        Map<String, AtomicInteger> goldRanking = new HashMap<>();
        for (String name : names) {
            goldRanking.put(name, new AtomicInteger(0));
        }

        Adventurer[] adventurers = new Adventurer[names.length * 3];
        for (int i = 0; i < adventurers.length; i++) {
            String name = names[i % names.length];

            adventurers[i] = new Adventurer(dungeon, name,  goldRanking);
            adventurers[i].start();
        }
        for (int i = 0; i < adventurers.length; i++) {
            try {
                adventurers[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < adventurers.length; i++) {
            try {
                adventurers[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println();
        Optional<Map.Entry<String, AtomicInteger>> max = goldRanking.entrySet().stream().max(Comparator.comparing(Map.Entry::getKey));
        System.out.println("1등: [" + max.get().getKey() + "] - " + max.get().getValue() + "골드");
//        for (String name : names) {
//            new Adventurer(dungeon, name).start();
//        }
    }
}
