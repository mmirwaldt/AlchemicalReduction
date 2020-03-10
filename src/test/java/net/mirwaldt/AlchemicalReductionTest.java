package net.mirwaldt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlchemicalReductionTest {
    private final AlchemicalReducer alchemicalReducer = new SequentialLoopAlchemicalReducer();

    @Test
    void test_noElimination() {
        assertEquals("", alchemicalReducer.reduce(""));
        assertEquals("a", alchemicalReducer.reduce("a"));
        assertEquals("AA", alchemicalReducer.reduce("AA"));
        assertEquals("aB", alchemicalReducer.reduce("aB"));
    }

    @Test
    void test_oneElimination() {
        assertEquals("", alchemicalReducer.reduce("aA"));
        assertEquals("", alchemicalReducer.reduce("Aa"));
        assertEquals("a", alchemicalReducer.reduce("aaA"));
        assertEquals("A", alchemicalReducer.reduce("AaA"));
        assertEquals("b", alchemicalReducer.reduce("baA"));
        assertEquals("B", alchemicalReducer.reduce("AaB"));
    }

    @Test
    void test_twoEliminations_simple() {
        assertEquals("", alchemicalReducer.reduce("aAAa"));
        assertEquals("", alchemicalReducer.reduce("AabB"));
        assertEquals("a", alchemicalReducer.reduce("aAabB"));
        assertEquals("A", alchemicalReducer.reduce("AaAbB"));
        assertEquals("b", alchemicalReducer.reduce("bBaAb"));
    }

    @Test
    void test_threeEliminations_simple() {
        assertEquals("", alchemicalReducer.reduce("aAAaAa"));
        assertEquals("", alchemicalReducer.reduce("AabBAa"));
        assertEquals("aC", alchemicalReducer.reduce("aAabBCcC"));
        assertEquals("AC", alchemicalReducer.reduce("AaAbBbBC"));
    }
}
