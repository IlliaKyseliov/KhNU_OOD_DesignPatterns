package org.example.entities;

public class Vampire extends Warrior {
    static final int ATTACK = 4;
    static final int VAMPIRISM = 50;

    private int health = 40;

    public Vampire() {
        super(40);
    }
    @Override
    public void hit(Warrior opponent) {
        int initHealth = opponent.getHealth();
        opponent.receiveDamage(getATTACK());
        this.setHealth(Math.min(40, getHealth() + ((initHealth - opponent.getHealth()) * VAMPIRISM) / 100));
    }

    public int getATTACK() {
        return ATTACK;
    }
}
