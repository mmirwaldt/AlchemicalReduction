package net.mirwaldt;

import static net.mirwaldt.util.AlchemicalReducerUtil.canReduce;

public class RecursiveAlchemicalReducer implements AlchemicalReducer {
    @Override
    public String reduce(String polymer) {
        return recursiveReduce(polymer, "");
    }

    public String recursiveReduce(String remainingUnreducedLetters, String reducedLetters) {
        if (remainingUnreducedLetters.isEmpty()) {
            return reducedLetters;
        } else if (remainingUnreducedLetters.length() == 1) {
            return recursiveReduce("", reducedLetters + remainingUnreducedLetters);
        } else {
            if (canReduce(remainingUnreducedLetters.charAt(0), remainingUnreducedLetters.charAt(1))) {
                return reduce(remainingUnreducedLetters, reducedLetters);
            } else {
                return skipOneLetter(remainingUnreducedLetters, reducedLetters);
            }
        }
    }

    private String skipOneLetter(String remainingUnreducedLetters, String reducedLetters) {
        final String headOfRemainingUnreducedLetters =
                remainingUnreducedLetters.substring(1);
        final String tailOfRemainingUnreducedLetters =
                reducedLetters + remainingUnreducedLetters.substring(0, 1);
        return recursiveReduce(headOfRemainingUnreducedLetters, tailOfRemainingUnreducedLetters);
    }

    private String reduce(String remainingUnreducedLetters, String reducedLetters) {
        final String remainingLettersAfterReduction = remainingUnreducedLetters.substring(2);
        if (reducedLetters.isEmpty()) {
            return recursiveReduce(remainingLettersAfterReduction, reducedLetters);
        } else {
            return reduceAndBacktrackToLastLetter(remainingLettersAfterReduction, reducedLetters);
        }
    }

    private String reduceAndBacktrackToLastLetter(
            String remainingUnreducedLettersAfterReduction, String reducedLetters) {
        final String backtrackedLetter = reducedLetters.substring(reducedLetters.length() - 1);
        final String remainingLettersOfReducedLetters =
                reducedLetters.substring(0, reducedLetters.length() - 1);
        return recursiveReduce(
                backtrackedLetter + remainingUnreducedLettersAfterReduction,
                remainingLettersOfReducedLetters);
    }
}
