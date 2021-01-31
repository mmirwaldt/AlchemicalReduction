package net.mirwaldt;

import static net.mirwaldt.util.AlchemicalReducerUtil.canReduce;

public class RecursiveAlchemicalReducer implements AlchemicalReducer {
    @Override
    public String reduce(String polymer) {
        return recursiveReduce(polymer, "");
    }

    public String recursiveReduce(String remainingUnreduced, String reduced) {
        if (remainingUnreduced.isEmpty()) {
            return reduced;
        } else if (remainingUnreduced.length() == 1) {
            return recursiveReduce("", reduced + remainingUnreduced);
        } else {
            return reduceRemaining(remainingUnreduced, reduced);
        }
    }

    private String reduceRemaining(String remainingUnreduced, String reduced) {
        if (canReduce(remainingUnreduced.charAt(0), remainingUnreduced.charAt(1))) {
            return reduce(remainingUnreduced, reduced);
        } else {
            return skipHeadOfRemainingUnreduced(remainingUnreduced, reduced);
        }
    }

    private String skipHeadOfRemainingUnreduced(String remainingUnreduced, String reduced) {
        final String headOfRemainingUnreduced = remainingUnreduced.substring(1);
        final String tailOfRemainingUnreduced = reduced + remainingUnreduced.substring(0, 1);
        return recursiveReduce(headOfRemainingUnreduced, tailOfRemainingUnreduced);
    }

    private String reduce(String remainingUnreduced, String reduced) {
        final String remainingUnreducedAfterReduction = remainingUnreduced.substring(2);
        if (reduced.isEmpty()) {
            return recursiveReduce(remainingUnreducedAfterReduction, reduced);
        } else {
            return reduceAndBacktrackToLastLetter(remainingUnreducedAfterReduction, reduced);
        }
    }

    private String reduceAndBacktrackToLastLetter(String remainingUnreducedAfterReduction, String reducedLetters) {
        final String backtracked = reducedLetters.substring(reducedLetters.length() - 1);
        final String remainingReduced = reducedLetters.substring(0, reducedLetters.length() - 1);
        return recursiveReduce(backtracked + remainingUnreducedAfterReduction, remainingReduced);
    }
}
