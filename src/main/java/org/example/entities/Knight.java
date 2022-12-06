package org.example.entities;

import java.util.HashMap;
import java.util.HashSet;

public class Knight extends Warrior{
    static final int ATTACK = 7;
    public Knight() {
        super(50);
    }
    @Override
    public int getATTACK() {
        return ATTACK;
    }
}


