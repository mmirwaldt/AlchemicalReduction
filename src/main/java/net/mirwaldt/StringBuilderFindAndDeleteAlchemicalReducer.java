package net.mirwaldt;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * This chemical reducer uses the methods indexOf(...) and delete(...) of the class StringBuilder
 */
public class StringBuilderFindAndDeleteAlchemicalReducer implements AlchemicalReducer {
    @Override
    public String reduce(String polymer) {
        final SortedSet<Integer> lowerCaseLettersWithUpperCaseLetters =
                finLowerCaseLettersWithUpperCaseLetters(polymer);
        final SortedSet<String> patterns = generatePatterns(lowerCaseLettersWithUpperCaseLetters);
        return reduce(polymer, patterns);
    }

    private String reduce(String polymer, SortedSet<String> patterns) {
        final StringBuilder stringBuilder = new StringBuilder(polymer);
        boolean noPatternAnymore = false;
        while (!noPatternAnymore) {
            noPatternAnymore = reduce(patterns, stringBuilder);
        }
        return stringBuilder.toString();
    }

    private boolean reduce(SortedSet<String> patterns, StringBuilder stringBuilder) {
        boolean noPatternAnymore = true;
        for (String pattern : patterns) {
            noPatternAnymore = replace(stringBuilder, noPatternAnymore, pattern);
        }
        return noPatternAnymore;
    }

    private boolean replace(StringBuilder stringBuilder, boolean noPatternAnymore, String pattern) {
        int index = stringBuilder.indexOf(pattern);
        while (-1 < index) {
            noPatternAnymore = false;
            reduce(stringBuilder, pattern, index);
            index = stringBuilder.indexOf(pattern, index);
        }
        return noPatternAnymore;
    }

    private void reduce(StringBuilder stringBuilder, String pattern, int index) {
        stringBuilder.delete(index, index + pattern.length());
    }

    private SortedSet<Integer> finLowerCaseLettersWithUpperCaseLetters(String polymer) {
        final SortedSet<Integer> letters = polymer.chars().boxed().collect(Collectors.toCollection(TreeSet::new));
        return letters.stream()
                .filter(Character::isLowerCase)
                .filter(l -> letters.contains(Character.toUpperCase(l)))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private SortedSet<String> generatePatterns(SortedSet<Integer> lowerCaseLettersWithUpperCaseLetters) {
        final SortedSet<String> patterns = new TreeSet<>();
        for (int l : lowerCaseLettersWithUpperCaseLetters) {
            patterns.add(Character.toString(l) + Character.toString(Character.toUpperCase(l)));
            patterns.add(Character.toString(Character.toUpperCase(l)) + Character.toString(l));
        }
        return patterns;
    }
}
