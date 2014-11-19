package uva;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);

        // while there is some input to read
        while (in.hasNextInt()) {
            int i = in.nextInt(), j = in.nextInt(), from = Math.min(i, j), to = Math
                    .max(i, j), max = 0;

            // loop through all the numbers
            // and find the biggest cycle
            for (int n = from; n <= to; n++) {
                max = Math.max(max, cycleLength(n));
            }

            out.printf("%d %d %d\n", i, j, max);
        }
    }

    // a function that returns the
    // next number in the sequence
    public static long next(long n) {
        if (n % 2 == 0)
            return n / 2; // if n is even
        else
            return 3 * n + 1; // if n is odd
    }

    // cache for already computed cycle lengths
    public static int[] cache = new int[1000000];

    public static int cycleLength(long n) {
        // our base case
        // 1 has a cycle length of 1
        if (n == 1)
            return 1;

        // check if we've already cached the
        // cycle length of the current number
        if (n < 1000000 && cache[(int) n] != 0)
            return cache[(int) n];

        // the cycle length of the current number is 1 greater
        // than the cycle length of the next number
        int length = 1 + cycleLength(next(n));

        // we cache the result if the
        // current number is not too big
        if (n < 1000000)
            cache[(int) n] = length;

        return length;
    }
}