package dylan.units;

import dylan.other.Army;
import dylan.weapons.Weapon;

/**
 * Created by Dylan on 19/02/2017.
 */
public abstract class Unit {
    private double healthPoints;
    private final double maxHealthPoints;
    private boolean isAlive;
    private final String screenName;
    private Weapon weapon;
    private Army army;
    private final int ID;
    private final String nameID;

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getScreenName() {
        return screenName;
    }

    public Army getArmy() {
        return army;
    }

    public double getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getID() {
        return ID;
    }

    public String getNameID() {
        return nameID;
    }

    public double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
        if(this.healthPoints <= 0) {
            setAlive(false);
        }
        else {
            setAlive(true);
        }
    }

    public Unit(double maxHealthPoints, String screenName, Weapon weapon, int ID, Army army) {
        this.maxHealthPoints = maxHealthPoints;
        this.screenName = screenName;
        this.setHealthPoints(maxHealthPoints);
        this.weapon = weapon;
        this.ID = ID;
        nameID = screenName + "#" + ID;
        this.army = army;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void dealDamage(Unit damageReceiver){
        this.getWeapon().dealDamage(this, damageReceiver);
    }
}
