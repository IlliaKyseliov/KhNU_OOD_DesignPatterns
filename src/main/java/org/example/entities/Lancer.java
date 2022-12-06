package org.example.entities;

public class Lancer extends Warrior{
    static final int PIERCING_POWER = 50;

    public Lancer() {
        super(60);
    }
    @Override
    public int getATTACK() {
        return 6;
    }

    private int getPiercingPower() {
        return PIERCING_POWER;
    }

    @Override
    public void hit(Warrior opponent) {
        int healthBefore = opponent.getHealth();
        super.hit(opponent);
        if (opponent instanceof HasWarriorBehind unitInArmy) {
            int damageDealt = healthBefore - opponent.getHealth();
            int damageForTheNext = damageDealt * getPiercingPower() / 100;
            Warrior next = opponent.getWarriorBehind();
            next.receiveDamage(damageForTheNext);
        }
    }
}
