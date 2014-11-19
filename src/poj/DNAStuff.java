package poj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DNAStuff {
	
	public static void main(String[] args) {
		ArrayList<Sata> peps = new ArrayList<Sata>();
		Scanner sc = new Scanner(System.in);
		String input, stuff;
		int count, pTwo, result;
		int length = sc.nextInt();
		int problems = sc.nextInt();
		for (int i = 0; i < problems; i++) {
			input = sc.next();
			//System.out.println(input);
			stuff = doStuff(input);
			//System.out.println(stuff);
			count = doMoreStuff(stuff);
			//System.out.println(count);
			Sata d = new Sata(count, input);
			peps.add(d);
		}
		Collections.sort(peps);
		
		for(Sata s:peps){
			System.out.println(s.thing);
		}
	}

	public static String reverseMe(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; --i)
			sb.append(s.charAt(i));
		return sb.toString();
	}

	public static String doStuff(String s) {
		String nu = "";
		for (int i = 0; i < s.length(); i++) {
			nu = nu + ((int) s.charAt(i) - 65);
		}
		return nu;
	}
	
	public static int doMoreStuff(String s){
		int count = 0;
		for(int i = 0;i < s.length(); i++){
			count += doEvenMoreStuff((int) s.charAt(i), s.substring(i + 1));
		}
		return count;
	}
	public static int doEvenMoreStuff(int n, String s){
		int count = 0;
		for(int i = 0; i < s.length(); i++){
			if((int) s.charAt(i) < n)
				count++;
		}
		return count;
	}
}

class Sata implements Comparable<Sata>{
	int number;
	String thing;
	
	public Sata(int num,String s){
		number = num;
		thing = s;
	}
	public int getNum(){
		return number;
	}
	public int compareTo(Sata o2) {
		  if (number > o2.number ){
		   return 1;
		  }
		  else if (number < o2.number){
		   return -1;
		  }
		  else
		   return 0;
		 }
	}

