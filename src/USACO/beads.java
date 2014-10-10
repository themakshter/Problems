package USACO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 ID: mohammad86
 LANG: JAVA
 TASK: beads
 PROG: beads.java
 */
public class beads {
	private int[] numbers;
	private String beads;

	public static void main(String[] args) throws IOException {
		beads b = new beads();
		b.initiate();
	}

	public void initiate() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("files/in/beads.in"));
		int number = Integer.parseInt(f.readLine());
		beads = f.readLine();
		calculate();
	}

	public void calculate() throws IOException {
		int i;
		int length = beads.length();
		numbers = new int[length];
		for (int s = 0; s < numbers.length; s++) {
			numbers[s] = 0;
		}
		for (i = 0; i < length; i++) {
			numbers[i] += Math.max(goForward(i, "b"), goForward(i, "r"));
			// System.out.println("First " + numbers[i]);
			numbers[i] += Math.max(goBackward(i, "b"), goBackward(i, "r"));
			// System.out.println("Final " +numbers[i]);
			if (numbers[i] > length)
				numbers[i] = length;
		}
		findMxm();
	}

	public int goForward(int i, String letter) {
		// for forward
		int j, count = 0;
		j = i;
		if (j + 1 > numbers.length)
			j = 0;
		while ((beads.substring(j, j + 1).equals("w"))
				|| (letter.equals(beads.substring(j, j + 1)))) {

			j++;
			if (j + 1 > beads.length()) {
				j = 0;

			}
			count++;
			if (count > beads.length()) {
				count = beads.length();
				break;
			}
		}
		return count;
	}

	public int goBackward(int i, String letter) {
		int j, count = 0;
		// start backwards
		j = i - 1;
		if (j < 0)
			j = beads.length() - 1;
		while ((beads.substring(j, j + 1).equals("w"))
				|| (letter.equals(beads.substring(j, j + 1)))) {
			j--;
			if (j < 0) {
				j = beads.length() - 1;

			}

			count++;
			if (count > beads.length()) {
				count = beads.length();
				break;
			}
		}

		return count;
	}

	public void findMxm() throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"files/out/beads.out")));
		int max = 0;
		String s = "";
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] > max)
				max = numbers[i];
			s = "" + i;
		}
		// System.out.println("Max = " + max + " at index = " + s);
		out.println(max);
		out.close();

	}
}
