package day_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public long getSolution2() throws IOException {

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

                        String firstIdAsString = String.valueOf(firstId);
                        for (int i = 1; i <= firstIdAsString.length() / 2; i++) {
                            if (isInvalidPattern2(firstIdAsString, i)) {
                                finalCode += firstId;
                                i = (firstIdAsString.length() / 2) + 1;
                            }
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


    public boolean isInvalidPattern2(String pattern, int offset) {

        List<String> sequenceParts = new ArrayList<>();
        int startIndex = 0;
        int endIndex = offset;
        while (endIndex <= pattern.length()) {
            sequenceParts.add(pattern.substring(startIndex, endIndex));
            startIndex = endIndex;
            endIndex = endIndex + offset;
        }
        String possiblePattern = sequenceParts.getFirst();
        for (int i = 1; i < sequenceParts.size(); i++) {
            // The pattern is invalid if sequences are not matching or not everything is included inside the sequence parts array
            if (!possiblePattern.equals(sequenceParts.get(i)) || startIndex != pattern.length()) {
                return false;
            }
        }

        return true;
    }
}
