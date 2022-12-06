package org.example;

import org.example.entities.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("testBetweenTwoWarriorsCheckWinner")
    void testBetweenTwoWarriorsCheckWinner(Warrior w1, Warrior w2, boolean expected) {
        boolean result = Battle.fight(w1, w2);

        assertEquals(expected, result);
    }

    static Stream<Arguments> testBetweenTwoWarriorsCheckWinner() {
        return Stream.of(
                Arguments.of(new Warrior(), new Knight(), false),//Fight 1
                Arguments.of(new Knight(), new Warrior(), true), //Fight 2
                Arguments.of(new Warrior(), new Warrior(), true),//Fight 3
                Arguments.of(new Knight(), new Warrior(), true), //Fight 4
                Arguments.of(new Warrior(), new Warrior(), true),//Fight 5
                Arguments.of(new Warrior(), new Knight(), false) //Fight 6
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("testBetweenTwoWarriorsCheckHealth")
    void testBetweenTwoWarriorsCheckHealth(Warrior w1, Warrior w2, int expected) {
        boolean result = Battle.fight(w1, w2);

        assertEquals(w1.getHealth(), expected);
    }

    static Stream<Arguments> testBetweenTwoWarriorsCheckHealth() {
        return Stream.of(
                Arguments.of(new Defender(), new Rookie(), 60) //Fight 8
        );
    }


    @ParameterizedTest
    @MethodSource
    @DisplayName("testBetweenThreeWarriorsCheckWinner")
    void testBetweenThreeWarriorsCheckWinner(Warrior w1, Warrior w2, Warrior w3, boolean expected) {
        Battle.fight(w1,w2);
        var result = Battle.fight(w2,w3);

        assertEquals(expected, result);
    }

    static Stream<Arguments> testBetweenThreeWarriorsCheckWinner() {
        return Stream.of(
                Arguments.of(new Warrior(), new Knight(), new Warrior(), false), //Fight 7
                Arguments.of(new Rookie(), new Defender(), new Warrior(), true)  //Fight 9
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Battle")
    void testBattle(Army army1, Army army2, boolean expected) {
        boolean result = Battle.fight(army1, army2);

        assertEquals(result, expected);
    }

    static Stream<Arguments> testBattle() {
        return Stream.of(
                Arguments.of(new Army().addUnits(Warrior::new, 1),//Battle 1
                        new Army().addUnits(Warrior::new, 2),
                        false),

                Arguments.of(new Army().addUnits(Warrior::new, 2),//Battle 2
                        new Army().addUnits(Warrior::new, 3),
                        false),

                Arguments.of(new Army().addUnits(Warrior::new, 5),//Battle 3
                        new Army().addUnits(Warrior::new, 7),
                        false),

                Arguments.of(new Army().addUnits(Warrior::new, 20),//Battle 4
                        new Army().addUnits(Warrior::new, 21),
                        true),

                Arguments.of(new Army().addUnits(Warrior::new, 10),//Battle 5
                        new Army().addUnits(Warrior::new, 11),
                        true),

                Arguments.of(new Army().addUnits(Warrior::new, 11),//Battle 6
                        new Army().addUnits(Warrior::new, 7),
                        true),

                Arguments.of(new Army().addUnits(Warrior::new, 5)//Battle 7
                                .addUnits(Defender::new, 4)
                                .addUnits(Defender::new, 5),
                        new Army().addUnits(Warrior::new, 4),
                        true),

                Arguments.of(new Army().addUnits(Defender::new, 5)//Battle 8
                                .addUnits(Warrior::new, 20)
                                .addUnits(Defender::new, 4),
                        new Army().addUnits(Warrior::new, 21),
                        true),

                Arguments.of(new Army().addUnits(Warrior::new, 10)//Battle 9
                                .addUnits(Defender::new, 5)
                                .addUnits(Defender::new, 10),
                        new Army().addUnits(Warrior::new, 5),
                        true),

                Arguments.of(new Army().addUnits(Defender::new, 2)//Battle 10
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 1),
                        new Army().addUnits(Warrior::new, 5),
                        false),

                Arguments.of(new Army().addUnits(Defender::new, 5)//Battle 11
                                .addUnits(Vampire::new, 6)
                                .addUnits(Warrior::new, 7),
                        new Army().addUnits(Warrior::new, 6)
                                .addUnits(Defender::new, 6)
                                .addUnits(Vampire::new, 6),
                        false),

                Arguments.of(new Army().addUnits(Defender::new, 2)//Battle 12
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 3),
                        false),

                Arguments.of(new Army().addUnits(Defender::new, 11)//Battle 13
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 13),
                        true),

                Arguments.of(new Army().addUnits(Defender::new, 9)//Battle 14
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 8),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 13),
                        true),

                Arguments.of(new Army().addUnits(Lancer::new, 5)//Battle 15
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 5),
                        false),

                Arguments.of(new Army().addUnits(Lancer::new, 7)//Battle 16
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        true),

                Arguments.of(new Army().addUnits(Lancer::new, 7)//Battle 17
                                .addUnits(Vampire::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Defender::new, 2),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        true),

                Arguments.of(new Army().addUnits(Lancer::new, 1)//Battle 18
                                .addUnits(Warrior::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Knight::new, 2),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        false)
        );
    }

//    @ParameterizedTest
//    @MethodSource
//    @DisplayName("Straight Fight")
//    void testStraightFight(Army army1, Army army2, boolean expected) {
//        boolean result = Battle.straightFight(army1, army2);
//
//        assertEquals(result, expected);
//    }
//
//    static Stream<Arguments> testStraightFight() {
//        return Stream.of(
//                Arguments.of(new Army().addUnits(Lancer::new, 5)
//                                .addUnits(Vampire::new, 3)
//                                .addUnits(Warrior::new, 4)
//                                .addUnits(Defender::new, 2),
//                        new Army().addUnits(Warrior::new, 4)
//                                .addUnits(Defender::new, 4)
//                                .addUnits(Vampire::new, 6)
//                                .addUnits(Lancer::new, 5),
//                        false),
//
//                Arguments.of(new Army().addUnits(Lancer::new, 7)
//                                .addUnits(Vampire::new, 3)
//                                .addUnits(Warrior::new, 4)
//                                .addUnits(Defender::new, 2),
//                        new Army().addUnits(Warrior::new, 4)
//                                .addUnits(Defender::new, 4)
//                                .addUnits(Vampire::new, 6)
//                                .addUnits(Lancer::new, 4),
//                        true),
//
//                Arguments.of(new Army().addUnits(Lancer::new, 7)
//                                .addUnits(Vampire::new, 3)
//                                .addUnits(Healer::new, 1)
//                                .addUnits(Warrior::new, 4)
//                                .addUnits(Healer::new, 1)
//                                .addUnits(Defender::new, 2),
//                        new Army().addUnits(Warrior::new, 4)
//                                .addUnits(Defender::new, 4)
//                                .addUnits(Healer::new, 1)
//                                .addUnits(Vampire::new, 6)
//                                .addUnits(Lancer::new, 4),
//                        false),
//
//                Arguments.of(new Army().addUnits(Lancer::new, 1)
//                                .addUnits(Warrior::new, 3)
//                                .addUnits(Healer::new, 1)
//                                .addUnits(Warrior::new, 4)
//                                .addUnits(Healer::new, 1)
//                                .addUnits(Knight::new, 2),
//                        new Army().addUnits(Warrior::new, 4)
//                                .addUnits(Defender::new, 4)
//                                .addUnits(Healer::new, 1)
//                                .addUnits(Vampire::new, 6)
//                                .addUnits(Lancer::new, 4),
//                        true)
//        );
//    }










}