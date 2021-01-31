package net.mirwaldt;

import static net.mirwaldt.util.AlchemicalReducerUtil.canReduce;

public class RecursiveAlchemicalReducer implements AlchemicalReducer {
    @Override
    public String reduce(String polymer) {
        return recursiveReduce(polymer, "");
    }

    public String recursiveReduce(String restPolymer, String result) {
        if(restPolymer.isEmpty()) {
            return result;
        } else if(restPolymer.length() == 1) {
            return recursiveReduce("", result + restPolymer);
        } else {
            if(canReduce(restPolymer.charAt(0), restPolymer.charAt(1))) {
                if(result.isEmpty()) {
                    return recursiveReduce(restPolymer.substring(2), result);
                } else {
                    return recursiveReduce(result.substring(result.length()-1) + restPolymer.substring(2),
                            result.substring(0, result.length()-1));
                }
            } else {
                return recursiveReduce(restPolymer.substring(1), result + restPolymer.substring(0, 1));
            }
        }
    }
}
