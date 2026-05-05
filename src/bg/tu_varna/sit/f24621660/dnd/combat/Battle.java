package bg.tu_varna.sit.f24621660.dnd.combat;

import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.entities.monster.Monster;

public class Battle {
    private final Hero hero;
    private final Monster monster;
    private final AttackManager attack;
    private final TurnManager turn;

    public Battle(Hero hero, Monster monster) {
        this.hero = hero;
        this.monster = monster;
        this.turn = new TurnManager();
        this.attack = new AttackManager(hero, monster);

        startBattle();
    }

    private void startBattle() {
        GameState.changeTo(State.COMBAT);
    }

    public void processTurn(AttackType heroChoice) {
        if (turn.isHeroTurn()) {
            attack.processHeroAttack(heroChoice);
        } else {
            attack.processMonsterAttack();
        }

        checkBattleOutcome();

        if (GameState.current() == State.COMBAT) {
            turn.passTurn();
        }
    }

    private void checkBattleOutcome() {
        if (monster.getHealth().getValue() <= 0) {
            hero.handleVictory();
            GameState.changeTo(State.EXPLORATION);
        } else if (hero.getHealth().getValue() <= 0) {
            GameState.changeTo(State.GAME_OVER);
        }
    }
    public String turnState() {
        return turn.isHeroTurn() ? "It's Hero's turn!" : "It's Monster's turn!";
    }

    public String healthState() {
        return String.format("Hero health: %d \nMonster health: %d",
                hero.getHealth().getValue(), monster.getHealth().getValue());
    }
}