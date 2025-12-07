package day_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GiftShop {

    public long getSolution1() throws IOException {

        long finalCode = 0L;

        try (BufferedReader br = new BufferedReader(new FileReader("2025/day_2/input.txt"))) {
            String line = br.readLine();
            while (line != null) {
                String[] codes = line.split(",");

                for (String code : codes) {

                    String[] codeIds = code.split("-");

                    long firstId = Long.parseLong(codeIds[0]);
                    long lastId = Long.parseLong(codeIds[1]);

                    // Loop until the first id hits the second id
                    while (firstId <= lastId) {

                        if (isInvalidPattern(String.valueOf(firstId))) {
                            finalCode += firstId;
                        }
                        firstId++;
                    }
                }
                line = br.readLine();
            }
        }
        return finalCode;
    }

    public boolean isInvalidPattern(String pattern) {

        // Odd length patterns cant be equal
        if (pattern.length() % 2 != 0) {
            return false;
        }

        String firstPattern = pattern.substring(0, pattern.length() / 2);
        String secondPattern = pattern.substring(pattern.length() / 2);

        return firstPattern.equals(secondPattern);
    }
}
