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
            int percent = (int) Math.round(hero.getArmor().getDamageReduction() * 100);
            sb.append(String.format("Armor:  [%s] | %d%% Damage Reduction\n", hero.getArmor().getName(), percent));
        } else {
            sb.append("Armor:  [None]\n");
        }

        sb.append("----------------------\n");

        // Обработваме магията
        if (hero.getSpell() != null) {
            int percent = (int) Math.round(hero.getSpell().getDamageIncrease() * 100);
            sb.append(String.format("Spell:  [%s] | %d%% Damage Increase\n", hero.getSpell().getName(), percent));
        } else {
            sb.append("Spell:  [None]\n");
        }

        sb.append("----------------------\n");

        if (hero.getWeapon() != null) {
            int percent = (int) Math.round(hero.getWeapon().getDamageIncrease() * 100);
            sb.append(String.format("Weapon: [%s] | %d%% Damage Increase\n", hero.getWeapon().getName(), percent));
        } else {
            sb.append("Weapon: [None]\n");
        }

        sb.append("======================");

        return sb.toString();
    }
}