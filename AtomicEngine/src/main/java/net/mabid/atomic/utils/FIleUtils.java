package net.mabid.atomic.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FIleUtils {

    private FIleUtils() {
    }

    public static String loadFile(String filepath) {
        StringBuilder result = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String buffer = "";
            while ((buffer = reader.readLine()) != null) {
                result.append(buffer + '\n');
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

}
