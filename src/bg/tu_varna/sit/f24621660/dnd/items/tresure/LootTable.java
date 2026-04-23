package bg.tu_varna.sit.f24621660.dnd.items.tresure;

import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class LootTable {

    private static LootTable instance = null;
    private final List<LootEntry> dropPool;
    private final Random random;

    private LootTable() {
        this.dropPool = new ArrayList<>();
        this.random = new Random();
    }

    public static LootTable getInstance() {
        if (instance == null) {
            instance = new LootTable();
        }
        return instance;
    }

    public void addLootEntry(Supplier<Item> itemBlueprint, int minLevel, int maxLevel) {
        this.dropPool.add(new LootEntry(itemBlueprint, minLevel, maxLevel));
    }

    public void clearPool() {
        this.dropPool.clear();
    }

    public Item generateLoot(int mapLevel) {
        List<LootEntry> suitableEntries = new ArrayList<>();
        for (LootEntry entry : dropPool) {
            if (entry.isSuitableFor(mapLevel)) {
                suitableEntries.add(entry);
            }
        }

        if (suitableEntries.isEmpty()) {
            return null;//Това не трябва да е така
        }

        int randomIndex = random.nextInt(suitableEntries.size());
        return suitableEntries.get(randomIndex).itemBlueprint.get();
    }

    private static class LootEntry {
        final Supplier<Item> itemBlueprint;
        final int minLevel;
        final int maxLevel;

        LootEntry(Supplier<Item> itemBlueprint, int minLevel, int maxLevel) {
            this.itemBlueprint = itemBlueprint;
            this.minLevel = minLevel;
            this.maxLevel = maxLevel;
        }

        boolean isSuitableFor(int level) {
            return level >= minLevel && level <= maxLevel;
        }
    }
}