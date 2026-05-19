package bg.tu_varna.sit.f24621660.dnd.items;

import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Armor;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Spell;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Weapon;

public class ItemFactory {

    public Item createItem(String type, String name, double bonus) {
        return switch (type.toUpperCase()) {
            case "WEAPON" -> new Weapon(name, bonus);
            case "SPELL" -> new Spell(name, bonus);
            case "ARMOR" -> new Armor(name, bonus);
            default -> throw new IllegalArgumentException("Unknown item type: " + type);
        };
    }
}