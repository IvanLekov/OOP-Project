package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.loot;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.items.base.DefensiveItem;
import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Spell;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Weapon;

public class LootCommand implements Command {

    @Override
    public String execute(GameContext context, String[] args) {

        if (context.getStateManager().getCurrent() != State.LOOTING) {
            return "You are not looting.";
        }

        Item loot = context.getCurrentLoot();
        if (loot == null) {
            context.getStateManager().changeTo(State.EXPLORATION);
            return "Error: No loot found. Returning to exploration.";
        }

        if (args.length == 0) {
            return "Please specify an action (equip, discard).";
        }

        String action = args[0].toLowerCase();
        String resultMessage;

        switch (action) {
            case "equip" -> {
                switch (loot) {
                    case DefensiveItem armor -> context.getHero().equipArmor(armor);
                    case Weapon weapon -> context.getHero().equipWeapon(weapon);
                    case Spell spell -> context.getHero().equipSpell(spell);
                    default -> {
                        return "Error: Unknown item type.";
                    }
                }
                resultMessage = "You successfully equipped [" + loot.getName() + "].";
            }
            case "discard" -> {
                resultMessage = "You decided to leave the [" + loot.getName() + "] behind.";
            }
            default -> {
                return "Invalid loot action: '" + action + "'. Use 'equip' or 'discard'.";
            }
        }

        // 4. Почистваме и връщаме нормалното състояние (DRY - Don't Repeat Yourself)
        context.clearLoot(); // Използваме метода от обновения GameContext
        context.getStateManager().changeTo(State.EXPLORATION);

        return resultMessage + "\nYou are now exploring.";
    }
}