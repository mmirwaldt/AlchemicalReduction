package net.mirwaldt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputFileReducerMain {
    public static void main(String[] args) throws IOException {
        String input = readStringFromInputStream(
                SequentialLoopAlchemicalReducer.class.getResourceAsStream("/input.txt"));
        System.out.println("Number of units: " + new SequentialLoopAlchemicalReducer().reduce(input).length());
    }

    static String readStringFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}
