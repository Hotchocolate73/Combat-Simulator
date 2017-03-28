package dylan.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dylan on 19/02/2017.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("*** Welcome to Combat Simulator! (2017) ***");
//        Army firstArmy = new Army("Army1", 10, 300);
//        Army secondArmy = new Army("Army2", 10, 300);

        Army firstArmy = Army.createArmyFromUser();
        Army secondArmy = Army.createArmyFromUser();

        List<Army> armies = new ArrayList<>();
        armies.add(firstArmy);
        armies.add(secondArmy);

        Battle battle = new Battle(armies);
        battle.simulate();
    }
}