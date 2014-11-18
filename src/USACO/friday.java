package usaco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 ID: mohammad86
 LANG: JAVA
 TASK: friday
 PROG: friday.java
 */

public class friday {
	private int[] days = { 0, 0, 0, 0, 0, 0, 0 };
	private boolean isLeap;

	public static void main(String[] args) throws IOException {
		friday f = new friday();
		f.initiate();
		f.print();
	}

	public void initiate() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("files/in/friday.in"));
		int years = Integer.parseInt(f.readLine());
		// int years = 20;
		int day = 2;
		for (int i = 0; i < years; i++) {
			day = doYear(i, day);
		}
		// System.out.println("done");
	}

	public int doYear(int i, int d) {
		isLeap = false;
		int day = d;
		int year = 1900;
		year = 1900 + i;
		// day = (2 + i) % 7;
		// System.out.println(year + " "+day);
		if ((year) % 100 == 0) {
			if ((year) % 400 == 0) {
				isLeap = true;
				// day = (day + (i - 1)/4) % 7;
				// day = (day + 2)%7;
				d = (d + 366) % 7;
			} else {
				d = (d + 365) % 7;
			}
		} else if ((year) % 4 == 0) {
			isLeap = true;
			// day = (day + ((i - 1)/4)) % 7;
			// day = (day + 2)%7;
			d = (d + 366) % 7;
		} else {
			// day = (day + 1)%7;
			d = (d + 365) % 7;
		}
		// System.out.println(d);
		for (int j = 0; j < 12; j++) {
			day = doMonth(day, j);
		}
		return d;
	}

	public int doMonth(int startingDay, int month) {
		int day;
		days[(startingDay + 12) % 7]++;
		if ((month == 8) || (month == 3) || (month == 5) || (month == 10)) {
			day = (startingDay + 30) % 7;
		} else if (month == 1) {
			if (isLeap) {
				day = (startingDay + 29) % 7;
			} else {
				day = (startingDay + 28) % 7;
			}
		} else {
			day = (startingDay + 31) % 7;
		}
		return day;
	}

	public void print() throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"files/out/friday.out")));
		String x = days[0] + " " + days[1] + " " + days[2] + " " + days[3]
				+ " " + days[4] + " " + days[5] + " " + days[6];
		out.println(x);
		out.close();
	}
}