package bg.tu_varna.sit.f24621660.dnd.cli.command.hero_commands.attack;

import bg.tu_varna.sit.f24621660.dnd.combat.AttackType;

public class AttackSpellCommand extends BaseAttackCommand {
    public AttackSpellCommand() {
        super(AttackType.SPELL);
    }
}