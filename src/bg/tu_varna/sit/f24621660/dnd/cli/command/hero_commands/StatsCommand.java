package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands;

import bg.tu_varna.sit.f24621660.dnd.cli.command.Command;
import bg.tu_varna.sit.f24621660.dnd.core.GameContext;

public class StatsCommand implements Command {
    @Override
    public String execute(GameContext context, String[] args) {
        return "Level: " + context.getHero().getLevel() + '\n' +
                "Race: " + context.getHero().getClass().toString() + '\n' +
               "Strength: " + context.getHero().getStrength().getValue() + '\n' +
               "Mana: " + context.getHero().getMana().getValue() + '\n' +
               "Health: " + context.getHero().getHealth().getValue() + '\n' +
               "Position: " + context.getMapManager().getHeroPosition() + '\n';

    }
}
