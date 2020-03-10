package net.mirwaldt;

import net.mirwaldt.util.ReadFileUtils;

import java.io.IOException;

public class InputFileReducerMain {
    public static void main(String[] args) throws IOException {
        String input = ReadFileUtils.readStringFromInputStream(
                SequentialLoopAlchemicalReducer.class.getResourceAsStream("/input.txt"));
        System.out.println("Number of units: " + new SequentialLoopAlchemicalReducer().reduce(input).length());
    }
}
