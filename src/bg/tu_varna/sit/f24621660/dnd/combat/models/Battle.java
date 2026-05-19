package bg.tu_varna.sit.f24621660.dnd.combat.models;

import bg.tu_varna.sit.f24621660.dnd.combat.logic.AttackManager;
import bg.tu_varna.sit.f24621660.dnd.combat.logic.TurnManager;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.entities.monster.Monster;

public class Battle {

    private final Hero hero;
    private final Monster monster;
    private final AttackManager attackManager;
    private final TurnManager turnManager;

    public Battle(Hero hero, Monster monster, AttackManager attackManager, TurnManager turnManager) {
        this.hero = hero;
        this.monster = monster;
        this.attackManager = attackManager;
        this.turnManager = turnManager;
    }

    public BattleStatus processRound(AttackType heroChoice) {

        if (turnManager.isHeroTurn()) {
            attackManager.processHeroAttack(heroChoice);
            if (isMonsterDead()) return BattleStatus.HERO_WON;

            attackManager.processMonsterAttack();
            if (isHeroDead()) return BattleStatus.MONSTER_WON;
        } else {
            attackManager.processMonsterAttack();
            if (isHeroDead()) return BattleStatus.MONSTER_WON;

            attackManager.processHeroAttack(heroChoice);
            if (isMonsterDead()) return BattleStatus.HERO_WON;
        }

        turnManager.passTurn();
        return BattleStatus.ONGOING;
    }

    private boolean isMonsterDead() {
        return monster.getHealth().getValue() <= 0;
    }

    private boolean isHeroDead() {
        return hero.getHealth().getValue() <= 0;
    }

    public Hero getHero() { return hero; }
    public Monster getMonster() { return monster; }
    public TurnManager getTurnManager() {return turnManager;}
}