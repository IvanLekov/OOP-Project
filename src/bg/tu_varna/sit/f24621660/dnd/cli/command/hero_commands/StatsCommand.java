package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;

public class StatsCommand implements Command {

    @Override
    public String execute(GameContext context, String[] args) {
        Hero hero = context.getHero();

        if (hero == null) {
            return "No active game.";
        }

        int heroX = context.getMapManager().getHeroPosition().getX();
        int heroY = context.getMapManager().getHeroPosition().getY();
        StringBuilder sb = new StringBuilder();

        sb.append("\n=== Hero Status ===\n");

        sb.append("Race: ").append(hero.getClass().getSimpleName()).append("\n");
        sb.append("Level: ").append(hero.getLevel().getValue()).append("\n");

        sb.append("----------------------\n");

        sb.append("Health: ").append(hero.getHealth().getValue()).append(" / ").append(hero.getHealth().getMaxValue()).append("\n");

        sb.append("----------------------\n");

        sb.append("Strength: ").append(hero.getStrength().getValue()).append("\n");
        sb.append("Mana: ").append(hero.getMana().getValue()).append("\n");

        sb.append("----------------------\n");

        sb.append("Position on the map (row/column): (").append(heroX + 1).append("/").append(heroY + 1).append(")").append("\n");

        sb.append("===================");

        return sb.toString();
    }
}