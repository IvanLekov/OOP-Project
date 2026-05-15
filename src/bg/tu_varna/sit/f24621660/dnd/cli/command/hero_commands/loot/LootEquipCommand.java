package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.loot;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.items.base.DefensiveItem;
import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Spell;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Weapon;

public class LootEquipCommand implements Command {
    @Override
    public String execute(GameContext context, String[] args) {
        if (GameState.current() != State.LOOTING) {
            return "You are not looting.";
        }

        Item loot = context.getCurrentLoot();

        switch (loot) {
            case DefensiveItem armor -> context.getHero().equipArmor(armor);
            case Weapon weapon -> context.getHero().equipWeapon(weapon);
            case Spell spell -> context.getHero().equipSpell(spell);
            case null, default -> {
                return "Error: Unknown item type.";
            }
        }

        context.clearCurrentLoot();
        GameState.changeTo(State.EXPLORATION);

        return "You successfully equipped[" + loot.getName() + "].";
    }
}
