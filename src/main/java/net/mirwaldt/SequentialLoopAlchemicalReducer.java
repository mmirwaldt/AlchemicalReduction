package net.mirwaldt;

public class SequentialLoopAlchemicalReducer implements AlchemicalReducer {
    public String reduce(String polymer) {
        StringBuilder stringBuilder = new StringBuilder(polymer);
        int indexOfRightChar = 1;
        while(indexOfRightChar < stringBuilder.length()) {
            final char leftChar = stringBuilder.charAt(indexOfRightChar - 1);
            final char rightChar = stringBuilder.charAt(indexOfRightChar);
            if(canEliminate(leftChar, rightChar)) {
                stringBuilder.delete(indexOfRightChar - 1, indexOfRightChar + 1);
            }
            indexOfRightChar++;
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
