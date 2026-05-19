package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;

public class StatsCommand implements Command {

    @Override
    public String execute(GameContext context, String[] args) {
        Hero hero = context.getHero();

        if (hero == null || context.getCurrentLevel() == null) {
            return "No active game.";
        }

        int heroX = context.getCurrentLevel().mapManager().getHeroPosition().x();
        int heroY = context.getCurrentLevel().mapManager().getHeroPosition().y();

        return String.format(
                """
                        === Hero Status ===
                        Level: %d
                        ----------------------
                        Health: %d / %d
                        ----------------------
                        Strength: %d
                        Mana: %d
                        ----------------------
                        Position on map (row/col): (%d/%d)
                        ===================
                        """,
                hero.getLevel().getValue(),
                hero.getHealth().getValue(), hero.getHealth().getMaxValue(),
                hero.getStrength().getValue(),
                hero.getMana().getValue(),
                heroX + 1, heroY + 1
        );
    }
}