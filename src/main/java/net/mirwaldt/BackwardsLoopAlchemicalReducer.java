package net.mirwaldt;

import net.mirwaldt.util.AlchemicalReducerUtil;

import static net.mirwaldt.util.AlchemicalReducerUtil.*;

/**
 * This alchemical reducer loops backwards through the string and
 * therefore reduces the number of chars being moved left when two characters are removed from the stream.
 */
public class BackwardsLoopAlchemicalReducer implements AlchemicalReducer {
    public String reduce(String polymer) {
        final StringBuilder stringBuilder = new StringBuilder(polymer);
        int indexOfRightChar = stringBuilder.length() - 1;
        while(0 < indexOfRightChar && indexOfRightChar < stringBuilder.length()) {
            indexOfRightChar = handleChars(stringBuilder, indexOfRightChar);
        }
        return stringBuilder.toString();
    }

    private int handleChars(StringBuilder stringBuilder, int indexOfRightChar) {
        final char leftChar = stringBuilder.charAt(indexOfRightChar - 1);
        final char rightChar = stringBuilder.charAt(indexOfRightChar);
        if(canReduce(leftChar, rightChar)) {
            AlchemicalReducerUtil.reduce(stringBuilder, indexOfRightChar);

            // we must ensure that indexOfRightChar never gets the same value as stringBuilder.length()
            indexOfRightChar = Math.min(stringBuilder.length() - 1, indexOfRightChar - 1);
        } else {
            indexOfRightChar--;
        }
        return indexOfRightChar;
    }
}
