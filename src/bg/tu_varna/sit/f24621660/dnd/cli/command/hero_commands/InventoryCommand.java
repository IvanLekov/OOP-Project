package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;

public class InventoryCommand implements Command {

    @Override
    public String execute(GameContext context, String[] args) {
        Hero hero = context.getHero();

        if (hero == null) {
            return "No active game.";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("\n=== Hero Inventory ===\n");

        if (hero.getArmor() != null) {
            sb.append("Armor: ").append(hero.getArmor().getName()).append("\n");
            sb.append("Damage Reduction: ").append(hero.getArmor().getDamageReduction()).append("\n");
        } else {
            sb.append("Armor: None").append("\n");
        }

        sb.append("----------------------\n");

        if (hero.getSpell() != null) {
            sb.append("Spell: ").append(hero.getSpell().getName()).append("\n");
            sb.append("Damage Increase: ").append(hero.getSpell().getDamageIncrease()).append("\n");
        } else {
            sb.append("Spell: None").append("\n");
        }

        sb.append("----------------------\n");

        if (hero.getWeapon() != null) {
            sb.append("Weapon: ").append(hero.getWeapon().getName()).append("\n");
            sb.append("Damage Increase: ").append(hero.getWeapon().getDamageIncrease()).append("\n");
        } else {
            sb.append("Weapon: None").append("\n");
        }

        sb.append("======================");

        return sb.toString();
    }
}
