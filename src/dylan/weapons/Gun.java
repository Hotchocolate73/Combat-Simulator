package dylan.weapons;

import dylan.units.Unit;

/**
 * Created by Dylan on 19/02/2017.
 */
public abstract class Gun extends Weapon {
    private int bulletCount;
    private double damagePerBullet;
    private int maxBulletsPerTurn;

    public void setBulletCount(int bulletCount) {
        this.bulletCount = bulletCount;
    }

    public void setDamagePerBullet(double damagePerBullet) {
        this.damagePerBullet = damagePerBullet;
    }

    public void setMaxBulletsPerTurn(int maxBulletsPerTurn) {
        this.maxBulletsPerTurn = maxBulletsPerTurn;
    }

    public double getDamagePerBullet() {
        return damagePerBullet;
    }

    public int getBulletCount() {
        return bulletCount;
    }

    public int getMaxBulletsPerTurn() {
        return maxBulletsPerTurn;
    }

    public Gun(String gunScreenName, int bulletCount, int damagePerBullet, int maxBulletsPerTurn) {
        super(gunScreenName);
        setBulletCount(bulletCount);
        setDamagePerBullet(damagePerBullet);
        setMaxBulletsPerTurn(maxBulletsPerTurn);
    }

    @Override
    public void dealDamage(Unit damageDealer, Unit damageReceiver) {
        super.setAttackMessage("*** " + damageDealer.getNameID() + "(" + damageDealer.getArmy().getScreenName() + ")(" +
                damageDealer.getHealthPoints() + "/" + damageDealer.getMaxHealthPoints() + "HP)");
        double enemyHealthBefore = damageReceiver.getHealthPoints();

        if(bulletCount <= 0){
            super.setAttackMessage(super.getAttackMessage() + " melee'd");
            damageReceiver.setHealthPoints(damageReceiver.getHealthPoints() - 1);
        }
        else {
            int bulletsShot = 0;
            for (int currentBullet = 0; currentBullet < maxBulletsPerTurn && bulletCount > 0
                    && damageReceiver.getHealthPoints() > 0; currentBullet++) {
                damageReceiver.setHealthPoints(damageReceiver.getHealthPoints() - damagePerBullet);
                bulletCount--;
                bulletsShot++;
            }

            super.setAttackMessage(super.getAttackMessage() + " shot " +
                    bulletsShot + " bullets with an " + getScreenName() + " into");
        }
        double damageDone = enemyHealthBefore - damageReceiver.getHealthPoints();
        super.setAttackMessage(super.getAttackMessage() + " " + damageReceiver.getNameID() + "(" +
                damageReceiver.getArmy().getScreenName() + ")(" + damageReceiver.getHealthPoints() + "/" +
                damageReceiver.getMaxHealthPoints() + "HP) for " + damageDone + "HP!");
    }
}
