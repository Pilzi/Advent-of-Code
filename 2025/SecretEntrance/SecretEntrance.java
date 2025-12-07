package SecretEntrance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SecretEntrance {

    public static int INITIAL_INDEX = 50;
    public static int OVERFLOW_INDEX = 99;

    public int getSolution() throws IOException {

        int index = INITIAL_INDEX;
        int overflowCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("2025/SecretEntrance/input1.txt"))) {
            String line = br.readLine();

            while (line != null) {
                boolean positive = line.trim().charAt(0) == 'R';
                int numberToRotate = Integer.parseInt(line.substring(1));
                int initialIndex = index;

                index = index + (positive ? numberToRotate : -numberToRotate);

                // To get the solution for the first part of the challenge just move this line of code after the while loop
                    if (index == 0) {
                        overflowCount++;
                    }

                while (index > OVERFLOW_INDEX || index < 0) {
                    // If we start on 0 and don't do an entire rotation is the only edge case where we don't have to count up again
                    if (initialIndex != 0 || index <= -(OVERFLOW_INDEX + 1) || index >= (OVERFLOW_INDEX + 1)) {
                        overflowCount++;
                    }
                    index = index + (positive ? -(OVERFLOW_INDEX + 1) : (OVERFLOW_INDEX + 1));
                    initialIndex = index;
                }
                line = br.readLine();
            }
        }
        return overflowCount;
    }
}
