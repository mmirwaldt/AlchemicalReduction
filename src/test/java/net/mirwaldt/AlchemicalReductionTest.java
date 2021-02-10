package net.mirwaldt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlchemicalReductionTest {
    private final static AlchemicalReducer sequentialLoopAlchemicalReducer =
            new ForwardsLoopAlchemicalReducer();
    private final static AlchemicalReducer stringBuilderFindAndDeleteAlchemicalReducer =
            new IndexOfAndDeleteAlchemicalReducer();
    private final static AlchemicalReducer backwardsSequentialLoopAlchemicalReducer =
            new BackwardsLoopAlchemicalReducer();
    private final static AlchemicalReducer recursiveAlchemicalReducer =
            new RecursiveAlchemicalReducer();

    private static Stream<Arguments> alchemicalReducer() {
        return Stream.of(Arguments.of(sequentialLoopAlchemicalReducer),
                Arguments.of(stringBuilderFindAndDeleteAlchemicalReducer),
                Arguments.of(backwardsSequentialLoopAlchemicalReducer),
                Arguments.of(recursiveAlchemicalReducer));
    }

    @ParameterizedTest
    @MethodSource("alchemicalReducer")
    void test_noElimination(AlchemicalReducer alchemicalReducer) {
        assertEquals("", alchemicalReducer.reduce(""));
        assertEquals("a", alchemicalReducer.reduce("a"));
        assertEquals("AA", alchemicalReducer.reduce("AA"));
        assertEquals("aB", alchemicalReducer.reduce("aB"));
    }

    @ParameterizedTest
    @MethodSource("alchemicalReducer")
    void test_oneElimination(AlchemicalReducer alchemicalReducer) {
        assertEquals("", alchemicalReducer.reduce("aA"));
        assertEquals("", alchemicalReducer.reduce("Aa"));
        assertEquals("a", alchemicalReducer.reduce("aaA"));
        assertEquals("A", alchemicalReducer.reduce("AaA"));
        assertEquals("b", alchemicalReducer.reduce("baA"));
        assertEquals("B", alchemicalReducer.reduce("AaB"));
    }

    @ParameterizedTest
    @MethodSource("alchemicalReducer")
    void test_twoEliminations_nonRecursive(AlchemicalReducer alchemicalReducer) {
        assertEquals("", alchemicalReducer.reduce("aAAa"));
        assertEquals("", alchemicalReducer.reduce("AabB"));
        assertEquals("a", alchemicalReducer.reduce("aAabB"));
        assertEquals("A", alchemicalReducer.reduce("AaAbB"));
        assertEquals("b", alchemicalReducer.reduce("bBaAb"));
    }

    @ParameterizedTest
    @MethodSource("alchemicalReducer")
    void test_threeEliminations_nonRecursive(AlchemicalReducer alchemicalReducer) {
        assertEquals("", alchemicalReducer.reduce("aAAaAa"));
        assertEquals("", alchemicalReducer.reduce("AabBAa"));
        assertEquals("aC", alchemicalReducer.reduce("aAabBCcC"));
        assertEquals("AC", alchemicalReducer.reduce("AaAbBbBC"));
    }

    @ParameterizedTest
    @MethodSource("alchemicalReducer")
    void test_twoEliminations_oneRecursion(AlchemicalReducer alchemicalReducer) {
        assertEquals("", alchemicalReducer.reduce("abBA"));
        assertEquals("B", alchemicalReducer.reduce("BabBA"));
        assertEquals("A", alchemicalReducer.reduce("abBAA"));
        assertEquals("BA", alchemicalReducer.reduce("BabBAaAA"));
    }

    @ParameterizedTest
    @MethodSource("alchemicalReducer")
    void test_twoEliminations_twoRecursions(AlchemicalReducer alchemicalReducer) {
        assertEquals("", alchemicalReducer.reduce("CabBAc"));
        assertEquals("B", alchemicalReducer.reduce("BCabBAc"));
        assertEquals("A", alchemicalReducer.reduce("CabBAcA"));
        assertEquals("BA", alchemicalReducer.reduce("BCabBAaAcA"));
    }

    @ParameterizedTest
    @MethodSource("alchemicalReducer")
    void test_complexExample(AlchemicalReducer alchemicalReducer) {
        // Copied from puzzle
        assertEquals("dabCBAcaDA", alchemicalReducer.reduce("dabAcCaCBAcCcaDA"));
    }
}
