package org.example.entities;

public class Warrior {
    static final int ATTACK = 5;
    private final int initialHealth;
    private Warrior nextBehind;
    private int health = 50;

    public Warrior() {
        this(50);
    }

    protected Warrior(int initHealth) {
        initialHealth = health = initHealth;
    }
    public int getATTACK() {
        return ATTACK;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.min(getInitialHealth(), health);
    }

    private int getInitialHealth() {
        return initialHealth;
    }
    protected void healBy(int healthPoints) {
        setHealth(getHealth() + healthPoints);
    }
    public boolean isAlive() {
        return getHealth() > 0;
    }

    public void hit(Warrior opponent) {
        opponent.receiveDamage(getATTACK());
        processCommand(OurChampionHasHit.INSTANCE, null);
    }

    public void receiveDamage(int damage) {
        setHealth(getHealth() - damage);
    }

    protected void processCommand(Command command, Warrior sender) {
        var next = getWarriorBehind();
        if (next != null) {
            next.processCommand(command, this);
        }
    }
    protected Warrior getWarriorBehind() {
        return nextBehind;
    }

    public void setWarriorBehind(Warrior nextBehind) {
        this.nextBehind = nextBehind;
    }
}
