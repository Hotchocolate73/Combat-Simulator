package dylan.units;

import dylan.other.Army;
import dylan.weapons.Weapon;

/**
 * Created by Dylan on 19/02/2017.
 */
public class Soldier extends Unit {
    public Soldier(Weapon weapon, int ID, Army army) {
        super(100, "Soldier", weapon, ID, army);
    }
}
