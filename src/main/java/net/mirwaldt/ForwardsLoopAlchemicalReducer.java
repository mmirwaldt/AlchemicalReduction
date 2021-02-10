package net.mirwaldt;

import net.mirwaldt.util.AlchemicalReducerUtil;

import static net.mirwaldt.util.AlchemicalReducerUtil.canReduce;

/**
 * This alchemical reducer loops forwards through the string without using StringBuilder.replace()
 */
public class ForwardsLoopAlchemicalReducer implements AlchemicalReducer {
    public String reduce(String polymer) {
        final StringBuilder stringBuilder = new StringBuilder(polymer);
        int indexOfRightChar = 1;
        while(indexOfRightChar < stringBuilder.length()) {
            indexOfRightChar = handleChars(stringBuilder, indexOfRightChar);
        }
        return stringBuilder.toString();
    }

    private int handleChars(StringBuilder stringBuilder, int indexOfRightChar) {
        final char leftChar = stringBuilder.charAt(indexOfRightChar - 1);
        final char rightChar = stringBuilder.charAt(indexOfRightChar);
        if(canReduce(leftChar, rightChar)) {
            AlchemicalReducerUtil.reduce(stringBuilder, indexOfRightChar);

            // we must ensure that indexOfRightChar never gets lower 1
            indexOfRightChar = Math.max(1, indexOfRightChar - 1);
        } else {
            indexOfRightChar++;
        }
        return indexOfRightChar;
    }
}
