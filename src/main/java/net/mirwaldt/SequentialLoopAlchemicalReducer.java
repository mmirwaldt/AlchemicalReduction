package net.mirwaldt;

public class SequentialLoopAlchemicalReducer implements AlchemicalReducer {
    public String reduce(String polymer) {
        StringBuilder stringBuilder = new StringBuilder(polymer);
        return stringBuilder.toString();
    }
}
