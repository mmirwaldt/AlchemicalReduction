package net.mirwaldt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlchemicalReductionTest {
    private final AlchemicalReducer alchemicalReducer = new SequentialLoopAlchemicalReducer();

    @Test
    void test() {
        assertEquals("", alchemicalReducer.reduce(""));
        assertEquals("a", alchemicalReducer.reduce("a"));
    }
}
