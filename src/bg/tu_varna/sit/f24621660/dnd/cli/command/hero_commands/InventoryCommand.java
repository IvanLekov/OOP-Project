package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;

public class InventoryCommand implements Command {
    @Override
    public String execute(GameContext context, String[] args) {
        return "Armor: " + context.getHero().getArmor().getName() + '\n' +
                "Spell: " + context.getHero().getSpell().getName() + '\n' +
                "Weapon: " + context.getHero().getWeapon().getName() + '\n';
    }
}
