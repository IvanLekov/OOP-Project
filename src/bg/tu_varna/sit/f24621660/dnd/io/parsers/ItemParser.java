package bg.tu_varna.sit.f24621660.dnd.io.parsers;

import bg.tu_varna.sit.f24621660.dnd.items.ItemFactory;
import bg.tu_varna.sit.f24621660.dnd.items.base.Item;

public class ItemParser {

    private final ItemFactory itemFactory;

    public ItemParser(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    public ItemConfig parseLine(String line) {
        String[] parts = line.split(",");

        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }

        try {
            int minLevel = Integer.parseInt(parts[0].trim());
            int maxLevel = Integer.parseInt(parts[1].trim());
            String type = parts[2].trim();
            String name = parts[3].trim();
            double bonus = Double.parseDouble(parts[4].trim());

            Item item = itemFactory.createItem(type, name, bonus);

            return new ItemConfig(item, minLevel, maxLevel);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing numeric values in line: " + line, e);
        }
    }
}