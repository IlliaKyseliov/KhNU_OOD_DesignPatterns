package org.example.entities;

public class Healer extends Warrior{

    private static final int HealPower = 2;

    public Healer() {
        super(60);
    }
    @Override
    protected void processCommand(Command command, Warrior sender) {
        heal(sender);
        super.processCommand(command, sender);
    }

    @Override
    public int getATTACK() {
        return 0;
    }

    @Override
    public void hit(Warrior opponent) {}

    public int getHealPower() {
        return HealPower;
    }
    private void heal(Warrior warrior) {
        warrior.healBy(getHealPower());
    }
}
