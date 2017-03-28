package dylan.other;

import java.util.List;

/**
 * Created by Dylan on 19/02/2017.
 */
public class Battle {
    private List<Army> armies;
    private Army currentArmy;
    private Army armyToAttack;

    public Battle(List<Army> armies) {
        this.armies = armies;
        currentArmy = armies.get(0);
        armyToAttack = armies.get(1);
    }

    public void simulate() {
        printCompetitors();
        processBattleLogic();
        printEndOfBattle();
    }

    private void printCompetitors() {
        System.out.print("*** Beginning battle simulation...\n*** ");
        boolean isFirstIteration = true;
        for(Army army : armies) {
            if(isFirstIteration) {
                System.out.print(army.getScreenName());
                isFirstIteration = false;
            }
            else {
                System.out.print(" vs " + army.getScreenName());
            }
        }

        System.out.println();
    }

    private void processBattleLogic() {
        while(!checkForWin()){
            currentArmy.attack(armyToAttack);
            determineNextArmyToAttack();
        }
    }

    private void determineNextArmyToAttack() {
        if(armies.indexOf(currentArmy) == armies.size() - 1) {
            currentArmy = armies.get(0);
            armyToAttack = armies.get(armies.indexOf(armyToAttack) + 1);
        }
        else if(armies.indexOf(armyToAttack) == armies.size() - 1) {
            armyToAttack = armies.get(0);
            currentArmy = armies.get(armies.indexOf(currentArmy) + 1);
        }
        else {
            currentArmy = armies.get(armies.indexOf(currentArmy) + 1);
            armyToAttack = armies.get(armies.indexOf(armyToAttack) + 1);
        }
    }

    private boolean checkForWin() {
        return !(getVictor() == null);
    }

    private Army getVictor(){
        Army armyToReturn = null;
        int defeatedCount = 0;
        for (Army army : armies){
            if(!army.isDefeated()){
                armyToReturn = army;
            }
            else {
                defeatedCount++;
            }
        }

        if(defeatedCount != armies.size() - 1){
            armyToReturn = null;
        }

        return armyToReturn;
    }

    private void printEndOfBattle() {
        Army victor = getVictor();
        System.out.println("*** Battle over!");
        System.out.println("*** " + victor.getScreenName() + " wins with " + victor.calculateAlive() + " left alive!");
    }
}
