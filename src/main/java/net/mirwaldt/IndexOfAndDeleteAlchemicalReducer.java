package net.mirwaldt;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * This chemical reducer uses the methods indexOf(...) and delete(...) of the class StringBuilder
 */
public class IndexOfAndDeleteAlchemicalReducer implements AlchemicalReducer {
    @Override
    public String reduce(String polymer) {
        final SortedSet<Integer> lowerCaseLettersWithUpperCaseLetters =
                findLowerCaseLettersWithUpperCaseLetters(polymer);
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
            noPatternAnymore = noPatternAnymore & replace(stringBuilder, pattern);
        }
        return noPatternAnymore;
    }

    private boolean replace(StringBuilder stringBuilder, String pattern) {
        int index = stringBuilder.indexOf(pattern);
        boolean noPatternAnymore = true;
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

    private SortedSet<Integer> findLowerCaseLettersWithUpperCaseLetters(String polymer) {
        final SortedSet<Integer> letters = polymer.chars().boxed().collect(Collectors.toCollection(TreeSet::new));
        return letters.stream()
                .filter(Character::isLowerCase)
                .filter(l -> letters.contains(Character.toUpperCase(l)))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private SortedSet<String> generatePatterns(SortedSet<Integer> lowerCaseLettersWithUpperCaseLetters) {
        final SortedSet<String> patterns = new TreeSet<>();
        for (int l : lowerCaseLettersWithUpperCaseLetters) {
            final String lowerCaseLetter = Character.toString((char)l);
            final String upperCaseLetter = Character.toString(Character.toUpperCase((char)l));
            patterns.add(lowerCaseLetter + upperCaseLetter);
            patterns.add(upperCaseLetter + lowerCaseLetter);
        }
        return patterns;
    }
}
