package bg.tu_varna.sit.f24621660.dnd.items.tresure;

import bg.tu_varna.sit.f24621660.dnd.items.base.Item;

public class Treasure {
    private final Item treasureItem;

    public Treasure(int mapLevel) {
        this.treasureItem = LootTable.getInstance().generateLoot(mapLevel);
    }

    public Item getTreasureItem() {
        return treasureItem;
    }
}