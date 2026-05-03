package bg.tu_varna.sit.f24621660.dnd.combat;

import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.entities.monster.Monster;

public class Battle {
    private Hero hero;
    private Monster monster;

    private AttackManager attack;
    private TurnManager turn;

    public Battle(Hero hero, Monster monster) {
        this.hero = hero;
        this.monster = monster;

        this.turn  = new TurnManager();
        this.attack = new AttackManager(hero, monster);

        startBattle();
    }

    private void startBattle() {
        GameState.getInstance().setState(State.COMBAT);
    }

    public void processTurn() {
        if (turn.isHeroTurn()) {
            attack.processHeroAttack();
        }
        else {
            attack.processMonsterAttack();
        }

        turn.passTurn();
    }


    public String turnState() {
        if (turn.isHeroTurn()) {
            return "It's Hero's turn!";
        }
        else
            return "It's Monster's turn!";
    }

    public String healthState() {
        int heroHealth = hero.getHealth().getValue();
        int monsterHealth = monster.getHealth().getValue();

        return String.format("Hero health: %d \n Monster health: %d", heroHealth, monsterHealth);
    }

}
