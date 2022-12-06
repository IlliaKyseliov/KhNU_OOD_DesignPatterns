package org.example;

import org.example.entities.HasWarriorBehind;
import org.example.entities.Warrior;

import java.util.*;
import java.util.function.Supplier;

public class Army {
    private List<Warrior> troops = new LinkedList<>();

    static class UnitInArmy extends Warrior implements HasWarriorBehind {
        Warrior warrior;
        Warrior behind;
        public UnitInArmy(Warrior warrior) {
            this.warrior = warrior;
        }

        @Override
        public int getATTACK() {
            return warrior.getATTACK();
        }

        @Override
        public Warrior getWarriorBehind() {
            return behind;
        }

        @Override
        public void hit(Warrior opponent) {
            warrior.hit(opponent);
        }

        @Override
        public boolean isAlive() {
            return warrior.isAlive();
        }

        @Override
        public int getHealth() {
            return warrior.getHealth();
        }
    }

    public Iterator<Warrior> firstAlive() {
        return new FirstAliveIterator();
    }

    private class FirstAliveIterator implements Iterator<Warrior> {

        @Override
        public boolean hasNext() {
            while (peekFirst() != null && !peekFirst().isAlive()) {
                removeFirst();
            }
            return peekFirst() != null;
        }

        @Override
        public Warrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return peekFirst();
        }
    }

    public Army addUnits(Warrior warrior) {
        if (!troops.isEmpty()) {
            troops.get(troops.size() - 1).setWarriorBehind(warrior);
        }
        troops.add(warrior);
        return this;
    }

    public Army addUnits(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(factory.get());
        }
        return this;
    }

    public Warrior peekFirst() {
        return troops.isEmpty() ? null : troops.get(0);
    }

    public void removeFirst() {
        troops.remove(0);
    }
}
