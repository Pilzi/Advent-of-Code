package day_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lobby {
    int[] DECIMAL_NUMBERS_DESC = {9, 8, 7, 6, 5, 4, 3, 2, 1};

    public long getSolution() throws IOException {

        long sum = 0L;
        try (BufferedReader br = new BufferedReader(new FileReader("2025/day_3/input.txt"))) {
            String line = br.readLine();

            while (line != null) {
                boolean solutionFound = false;
                int i = 0;
                while (i < DECIMAL_NUMBERS_DESC.length && !solutionFound) {
                    int rightNumberFoundIndex = findNumber(DECIMAL_NUMBERS_DESC[i], line, line.length(), true);
                    if (rightNumberFoundIndex != -1) {
                        int j = 0;
                        while (j < DECIMAL_NUMBERS_DESC.length && !solutionFound) {
                            int leftNumberFoundIndex = findNumber(DECIMAL_NUMBERS_DESC[j], line, rightNumberFoundIndex, false);
                            if (leftNumberFoundIndex != -1) {
                                sum += Long.parseLong(DECIMAL_NUMBERS_DESC[i] + "" + DECIMAL_NUMBERS_DESC[j]);
                                solutionFound = true;
                            }
                            j++;
                        }
                    }
                    i++;
                }
                line = br.readLine();
            }
            return sum;
        }

    }

    public int findNumber(int numberToFind, String line, int index, boolean isStartDirectionRight) {

        if (isStartDirectionRight) {
            for (int i = 0; i < index; i++) {
                if (line.charAt(i) == ((char) (numberToFind + '0'))) {
                    return i;
                }
            }
        } else {
            for (int i = line.length() - 1; i > index; i--) {
                if (line.charAt(i) == ((char) (numberToFind + '0'))) {
                    return i;
                }
            }
        }
        return -1;

    }

}
