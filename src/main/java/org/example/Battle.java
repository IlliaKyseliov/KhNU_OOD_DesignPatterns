package org.example;

import org.example.entities.PairOfWarriors;
import org.example.entities.Warrior;

import java.util.LinkedList;
import java.util.Queue;


public class Battle {
    static boolean fight (Warrior warrior1, Warrior warrior2) {
        while (warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hit(warrior2);
            if (warrior2.isAlive()) {
                warrior2.hit(warrior1);
            }
        }
        return warrior1.isAlive();
    }

    static boolean fight (Army army1, Army army2) {
        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();

        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }
        return it1.hasNext();
    }

    //This implementation don't work with lancer and healer
    static boolean straightFight(Army army1, Army army2) {
        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();

        while (it1.hasNext() && it2.hasNext()) {
            Queue<PairOfWarriors> pairs = new LinkedList<>();
            while (it1.hasNext() && it2.hasNext()) {
                PairOfWarriors pair = new PairOfWarriors();
                pair.setFirstWarrior(army1.peekFirst());
                army1.removeFirst();
                pair.setSecondWarrior(army2.peekFirst());
                army2.removeFirst();
                pairs.add(pair);
            }
            while (!pairs.isEmpty()) {
                PairOfWarriors pair = pairs.poll();
                if (fight(pair.getFirstWarrior(), pair.getSecondWarrior())) {
                    army1.addUnits(pair.getFirstWarrior());
                }
                else {
                    army2.addUnits(pair.getSecondWarrior());
                }
            }
        }
        return it1.hasNext();
    }
}
