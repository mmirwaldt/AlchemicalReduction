package net.mirwaldt;

public class SequentialLoopAlchemicalReducer implements AlchemicalReducer {
    public String reduce(String polymer) {
        StringBuilder stringBuilder = new StringBuilder(polymer);
        int indexOfNextChar = 1;
        while(indexOfNextChar < stringBuilder.length()) {

            indexOfNextChar++;
        }
        return stringBuilder.toString();
    }
}
