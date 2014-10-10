package USACO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 ID: mohamma86
 LANG: JAVA
 TASK: milk2
 PROG: milk2.java
 */

public class milk2 {

	private static ArrayList<time> times;

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		times = new ArrayList<time>();
		int start, end, number, noMilk, milk;
		BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
		number = Integer.parseInt(br.readLine());
		for (int i = 0; i < number; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			times.add(new time(start, end));
		}
		Collections.sort(times);
		noMilk = longestCowNotMilked();
		milk = longestCowMilked();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"milk2.out")));
		out.println(milk + " " + noMilk);
		out.close();

	}

	public static int longestCowMilked() {
		int max = 0;
		int interval = 0;
		int i = 0;
		for (time t1 : times) {
			max = Math.max((t1.end - t1.start), max);
			interval = t1.end;
			for (time t2 : times) {
				if (t2.end < interval) // the first one can't be longer than the
										// second one
					continue;
				else if (t2.start > interval) // the time has to overlap at the
												// very least
					break;
				else {
					i = t2.end - t1.start;
					max = Math.max((i), max);
					interval = t2.end;
				}
			}
		}
		return max;
	}

	public static int longestCowNotMilked() {
		int max = 0;
		int max_end = times.get(0).end;
		int min_start = times.get(0).start;
		for(time t: times){
			if(t.end < max_end)
				continue;
			if(t.start <= max_end){
				max_end = Math.max(t.end, max_end);
				min_start = Math.min(min_start, t.start);
			}else{
				max = Math.max(t.start - max_end, max);
			}
			min_start = t.start;
			max_end = t.end;
		}
		return max;
	}
}

class time implements Comparable<time> {
	int start;
	int end;

	public time(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(time t) {
		if (start > t.start) {
			return 1;
		} else if (start < t.start) {
			return -1;
		} else
			return 0;
	}
}
