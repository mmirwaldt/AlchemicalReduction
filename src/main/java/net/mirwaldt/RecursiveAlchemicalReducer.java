package net.mirwaldt;

import static net.mirwaldt.util.AlchemicalReducerUtil.canReduce;

public class RecursiveAlchemicalReducer implements AlchemicalReducer {
    @Override
    public String reduce(String polymer) {
        return reduce(polymer, "");
    }

    public String reduce(String left, String right) {
         if (left.length() == 1 && right.length() == 1) {
            if (canReduce(left.charAt(0), right.charAt(0))) {
                return "";
            } else {
                return left + right;
            }
        } else if(1 < left.length() || 1 < right.length()) {
            return reduce(left.substring(0, 1), left.substring(1))
                    + reduce(right.substring(0, 1), right.substring(1));
        } else {
            return left + right;
        }
    }
}
