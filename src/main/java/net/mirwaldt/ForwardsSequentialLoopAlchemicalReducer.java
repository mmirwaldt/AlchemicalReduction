package net.mirwaldt;

/**
 * This alchemical reducer loops forwards through the string without using StringBuilder.replace()
 */
public class ForwardsSequentialLoopAlchemicalReducer implements AlchemicalReducer {
    public String reduce(String polymer) {
        StringBuilder stringBuilder = new StringBuilder(polymer);
        int indexOfRightChar = 1;
        while(indexOfRightChar < stringBuilder.length()) {
            final char leftChar = stringBuilder.charAt(indexOfRightChar - 1);
            final char rightChar = stringBuilder.charAt(indexOfRightChar);
            if(canEliminate(leftChar, rightChar)) {
                final int inclusiveStartIndexOfLeftChar = indexOfRightChar - 1;
                final int exclusiveEndIndexOfRightChar = indexOfRightChar + 1;
                stringBuilder.delete(inclusiveStartIndexOfLeftChar, exclusiveEndIndexOfRightChar);

                // we must ensure that indexOfRightChar never gets lower 1
                indexOfRightChar = Math.max(1, indexOfRightChar - 1);
            } else {
                indexOfRightChar++;
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
