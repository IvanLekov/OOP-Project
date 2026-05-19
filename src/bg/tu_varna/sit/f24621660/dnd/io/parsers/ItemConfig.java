package bg.tu_varna.sit.f24621660.dnd.io.parsers;

import bg.tu_varna.sit.f24621660.dnd.items.base.Item;

public record ItemConfig(Item item, int minLevel, int maxLevel) {
}