package dylan.weapons;

import dylan.units.Unit;

/**
 * Created by Dylan on 19/02/2017.
 */
public abstract class Weapon {
    private final String screenName;
    private String attackMessage;

    public String getAttackMessage() {
        return attackMessage;
    }

    public void setAttackMessage(String attackMessage) {
        this.attackMessage = attackMessage;
    }

    public Weapon(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenName() {
        return screenName;
    }

    public abstract void dealDamage(Unit damageDealer, Unit damageReceiver);
}
