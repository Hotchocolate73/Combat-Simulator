package dylan.units;

import dylan.other.Army;
import dylan.weapons.Weapon;

/**
 * Created by Dylan on 05/03/2017.
 */
public class Commando extends Unit {
    public Commando(Weapon weapon, int ID, Army army) {
        super(120, "Commando", weapon, ID, army);
    }
}
