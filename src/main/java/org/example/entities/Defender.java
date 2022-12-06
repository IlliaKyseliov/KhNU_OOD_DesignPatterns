package org.example.entities;

public class Defender extends Warrior{
    static final int ATTACK = 3;

    static final int DEFENCE = 2;

    private int health = 60;

    public Defender() {
        super(60);
    }
    @Override
    public void receiveDamage(int damage) {
        setHealth(getHealth() - Math.max(0, damage - DEFENCE));
    }

    public int getATTACK() {
        return ATTACK;
    }
}
