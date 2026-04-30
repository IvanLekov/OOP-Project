package bg.tu_varna.sit.f24621660.dnd.utills.parsers;

import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Armor;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Spell;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Weapon;

public class ItemParser {

    public Item parseLine(String line, int mapLevel) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }

        try {
            int minLevel = Integer.parseInt(parts[0].trim());
            int maxLevel = Integer.parseInt(parts[1].trim());

            if (mapLevel >= minLevel && mapLevel <= maxLevel) {
                String type = parts[2].trim().toUpperCase();
                String name = parts[3].trim();
                double bonus = Double.parseDouble(parts[4].trim());

                return createItem(type, name, bonus);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing numeric values in line: " + line, e);
        }

        return null;
    }

    private Item createItem(String type, String name, double bonus) {
        return switch (type) {
            case "WEAPON" -> new Weapon(name, bonus);
            case "SPELL" -> new Spell(name, bonus);
            case "ARMOR" -> new Armor(name, bonus);
            default -> throw new IllegalArgumentException("Unknown item type: " + type);
        };
    }
}