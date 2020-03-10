package net.mirwaldt;

import net.mirwaldt.util.ReadFileUtils;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class ShortedPolymerFinder {
    public static void main(String[] args) throws IOException {
        final String input = ReadFileUtils.readStringFromInputStream(
                SequentialLoopAlchemicalReducer.class.getResourceAsStream("/input.txt"));
        final Set<String> uppercaseChars =
                input.chars().mapToObj((charAsInt) -> String.valueOf(Character.toUpperCase((char) charAsInt))).collect(Collectors.toSet());

        int minLength = Integer.MAX_VALUE;
        String filteredCharPair = "";
        final SequentialLoopAlchemicalReducer sequentialLoopAlchemicalReducer = new SequentialLoopAlchemicalReducer();
        for (String charAsString : uppercaseChars) {
            String filteredInput = input
                    .replace(charAsString, "")
                    .replace(charAsString.toLowerCase(), "");
            int oldMinLength = minLength;
            minLength = Math.min(minLength, sequentialLoopAlchemicalReducer.reduce(filteredInput).length());
            if (oldMinLength != minLength) {
                filteredCharPair = charAsString + "/" + charAsString.toLowerCase();
            }
        }
        System.out.println("Min length " + minLength + " for " + filteredCharPair);
    }
}
