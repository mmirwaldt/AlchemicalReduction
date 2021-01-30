package net.mirwaldt.util;

public class AlchemicalReducerUtil {
    public static boolean canReduce(char leftChar, char rightChar) {
        return isSameChar(leftChar, rightChar) && isOneLowerAndTheOtherUpperCase(leftChar, rightChar);
    }

    public static void reduce(StringBuilder stringBuilder, int indexOfRightChar) {
        final int inclusiveStartIndexOfLeftChar = indexOfRightChar - 1;
        final int exclusiveEndIndexOfRightChar = indexOfRightChar + 1;
        stringBuilder.delete(inclusiveStartIndexOfLeftChar, exclusiveEndIndexOfRightChar);
    }

    private static boolean isOneLowerAndTheOtherUpperCase(char leftChar, char rightChar) {
        return isLeftLowerAndRightUpperCase(leftChar, rightChar)
                || isLeftLowerAndRightUpperCase(rightChar, leftChar);
    }

    private static boolean isLeftLowerAndRightUpperCase(char leftChar, char rightChar) {
        return Character.isLowerCase(leftChar) && Character.isUpperCase(rightChar);
    }

    private static boolean isSameChar(char leftChar, char rightChar) {
        return Character.compare(Character.toUpperCase(leftChar), Character.toUpperCase(rightChar)) == 0;
    }

}
