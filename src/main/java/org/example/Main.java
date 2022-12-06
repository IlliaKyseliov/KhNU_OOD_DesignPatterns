package org.example;

import org.example.entities.*;

public class Main {
    public static void main(String[] args) {
        Army army1 = new Army().addUnits(Lancer::new, 7)
                .addUnits(Vampire::new, 3)
                .addUnits(Healer::new, 1)
                .addUnits(Warrior::new, 4)
                .addUnits(Healer::new, 1)
                .addUnits(Defender::new, 2);

        Army army2 = new Army().addUnits(Warrior::new, 4)
                .addUnits(Defender::new, 4)
                .addUnits(Healer::new, 1)
                .addUnits(Vampire::new, 6)
                .addUnits(Lancer::new, 4);

        System.out.println(Battle.straightFight(army1, army2));
    }
}