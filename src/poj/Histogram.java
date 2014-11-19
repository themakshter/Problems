package poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Histogram {
	private static int[] alphabets;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		alphabets = new int[26];
		for (int i : alphabets) {
			alphabets[i] = 0;
		}
		String input;
		for (int i = 0; i < 4; i++) {
			input = f.readLine();
			doStuff(input);
		}
		printStuff();
	}

	public static void doStuff(String input) {
		input.toUpperCase();
		for (int i = 0; i < input.length(); i++) {
			if ((int) input.charAt(i) - 65 > 25
					|| (int) input.charAt(i) - 65 < 0)
				continue;
			int letter = ((int) input.charAt(i)) - 65;
			alphabets[letter] += 1;
		}
	}

	public static void printStuff() {
		int max = 0;
		int currentValue;
		for (int i = 0; i <= 25; i++) {
			if (alphabets[i] > max)
				max = alphabets[i];
		}
		currentValue = max;
		for (int i = 0; i <= max; i++) {
			for (int j = 0; j <= 25; j++) {
				if (j == 25) {
					if (alphabets[j] <= currentValue)
						System.out.print(" ");
					else
						System.out.print("*");
				} else {
					if (alphabets[j] <= currentValue)
						System.out.print("  ");
					else
						System.out.print("* ");
				}
			}
			System.out.println();
			currentValue--;
		}
		System.out
				.println("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
	}

}