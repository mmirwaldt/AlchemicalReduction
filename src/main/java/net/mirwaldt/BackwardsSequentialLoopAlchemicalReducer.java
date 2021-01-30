package net.mirwaldt;

/**
 * This alchemical reducer loops backwards through the string and
 * therefore reduces the number of chars being moved left when two characters are removed from the stream.
 */
public class BackwardsSequentialLoopAlchemicalReducer implements AlchemicalReducer {
    public String reduce(String polymer) {
        final StringBuilder stringBuilder = new StringBuilder(polymer);
        int indexOfRightChar = stringBuilder.length() - 1;
        while(0 < indexOfRightChar && indexOfRightChar < stringBuilder.length()) {
            final char leftChar = stringBuilder.charAt(indexOfRightChar - 1);
            final char rightChar = stringBuilder.charAt(indexOfRightChar);
            if(canEliminate(leftChar, rightChar)) {
                final int inclusiveStartIndexOfLeftChar = indexOfRightChar - 1;
                final int exclusiveEndIndexOfRightChar = indexOfRightChar + 1;
                stringBuilder.delete(inclusiveStartIndexOfLeftChar, exclusiveEndIndexOfRightChar);

                // we must ensure that indexOfRightChar never gets the same value as stringBuilder.length()
                indexOfRightChar = Math.min(stringBuilder.length() - 1, indexOfRightChar - 1);
            } else {
                indexOfRightChar--;
            }
        }
        return stringBuilder.toString();
    }

    private boolean canEliminate(char leftChar, char rightChar) {
        return isSameChar(leftChar, rightChar) && isOneLowerAndTheOtherUpperCase(leftChar, rightChar);
    }

    private boolean isOneLowerAndTheOtherUpperCase(char leftChar, char rightChar) {
        return isLeftLowerAndRightUpperCase(leftChar, rightChar)
                || isLeftLowerAndRightUpperCase(rightChar, leftChar);
    }

    private boolean isLeftLowerAndRightUpperCase(char leftChar, char rightChar) {
        return Character.isLowerCase(leftChar) && Character.isUpperCase(rightChar);
    }

    private boolean isSameChar(char leftChar, char rightChar) {
        return Character.compare(Character.toUpperCase(leftChar), Character.toUpperCase(rightChar)) == 0;
    }
}
