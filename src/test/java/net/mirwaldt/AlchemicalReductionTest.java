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
}
