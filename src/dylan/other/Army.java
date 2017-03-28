package dylan.other;

import dylan.units.Soldier;
import dylan.units.Unit;
import dylan.weapons.AK_73;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Dylan on 19/02/2017.
 */
public class Army {
    private List<Unit> formation;
    private String screenName;
    private Unit currentUnit;

    public Unit getCurrentUnit() {
        currentUnit = formation.get(formation.size() - calculateAlive());
        return currentUnit;
    }

    public List<Unit> getFormation() {
        return formation;
    }

    public String getScreenName() {
        return screenName;
    }


    public Army(String screenName, int numberOfUnits, int ammoPerUnit) {
        this.screenName = screenName;
        createFormation(numberOfUnits, ammoPerUnit);
    }

    public Army(String screenName) {
        this.screenName = screenName;
        this.formation = new ArrayList<>();
    }

    public static Army createArmyFromUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** Creating a new army...");
        System.out.print("Enter the name of your new army:\n>");
        String name = scanner.nextLine();

        Army createdArmy = new Army(name);
        createdArmy.formation = createdArmy.createFormationFromUser();
        System.out.println("*** Army (" + name + ") created successfully!");
        return createdArmy;
    }

    private List<Unit> createFormationFromUser(){
        //WAS WORKING ON CREATNG FORMATION FROM USER
        System.out.println("--- Formation creation...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of units the army will have:\n>");
        int numberOfUnits = scanner.nextInt();
        int unitsCreated = 0;
        int userInput = 0, unitSelection = 0, weaponSelection = 0, numberToCreate;
        List<Unit> formation = new ArrayList<>();
        while(unitsCreated < numberOfUnits){
            while(userInput == 0) {
                System.out.println(unitsCreated + "/" + numberOfUnits + " units created.");
                System.out.println("Enter the number of the unit you would like to place:");
                System.out.println("(1) Soldier");
                System.out.println("(2) Commando");
                System.out.print(">");
                userInput = scanner.nextInt();
                unitSelection = userInput;
//                if (userInput == 1) {
//                    unitSelection = 1;
//                } else if(userInput == 2) {
//                    unitSelection = 2;
//                }
//                else{
//                    userInput = 0;
//                }
            }

            System.out.println("Enter the number of the weapon would you like to give them:");
            System.out.println("(1) AK-73");
            System.out.println("(2) N4");
            System.out.print(">");
            userInput = scanner.nextInt();
//            weaponSelection = userInput;
            if(userInput == 1) {
                weaponSelection = 1;
            } else if(userInput == 2) {
                weaponSelection = 2;
            }
            else{
                weaponSelection = 0;
            }

            System.out.print("Enter how many of this configuration you would like to place:\n>");
            numberToCreate = scanner.nextInt();

            for(int unit = 0; unit < numberToCreate; unit++){
                if(unitSelection == 1){
                    if(weaponSelection == 1){
                        formation.add(new Soldier(new AK_73(300), 0, this));
                    }
                }

                unitsCreated++;
            }

            userInput = 0;
        }
//        System.out.print("Enter the number of bullets each unit will have: ");
//        int ammoPerUnit = scanner.nextInt();

        return formation;
    }

    private void createFormation(int numberOfUnits, int ammoPerUnit) {
        formation = new ArrayList<>();

        //Hard code army size for now
        for(int unit = 0; unit < numberOfUnits; unit++){
            formation.add(new Soldier(new AK_73(ammoPerUnit), unit, this));
        }

        currentUnit = formation.get(formation.size() - calculateAlive());
    }

    public void attack(Army armyToAttack) {
        Unit enemyCurrentUnit = armyToAttack.getCurrentUnit();
        double enemyHealthBefore = enemyCurrentUnit.getHealthPoints();
        this.getCurrentUnit().dealDamage(enemyCurrentUnit);
        printAttack(currentUnit, enemyCurrentUnit, enemyHealthBefore);
    }

    private void printAttack(Unit currentUnit, Unit enemyCurrentUnit, double enemyHealthBefore){
        System.out.println(currentUnit.getWeapon().getAttackMessage());

        if(!enemyCurrentUnit.isAlive()){
            System.out.println("*** " + enemyCurrentUnit.getScreenName() + "(" + enemyCurrentUnit.getArmy().screenName +
                    ") has died, leaving (" + enemyCurrentUnit.getArmy().calculateAlive() +
                    " of " + formation.size() + ") alive!");
        }
    }

    public boolean isDefeated() {
        return calculateAlive() <= 0;
    }

    public int calculateAlive() {
        int alive = 0;
        for(Unit unit : formation){
            if(unit.isAlive()){
                alive++;
            }
        }

        return alive;
    }
}
