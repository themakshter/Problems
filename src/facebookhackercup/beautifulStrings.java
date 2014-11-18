package facebookhackercup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

public class beautifulStrings {
	public static void main(String [] args) throws NumberFormatException, IOException{
		String beauty;
		int mxmBeauty;
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		int cases = Integer.parseInt(f.readLine());
//		for(int i=0; i < cases; i++){
//			beauty = f.readLine();
//			mxmBeauty = findBeauty(beauty);
//			System.out.println("Case #" + (i + 1) + ": " + mxmBeauty);
//		}
		
		BufferedReader f = new BufferedReader(new FileReader("files/in/beauty.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"files/out/beauty.out")));
		int cases = Integer.parseInt(f.readLine());
		for(int i=0; i < cases; i++){
			beauty = f.readLine();
			mxmBeauty = findBeauty(beauty);
			out.println("Case #" + (i + 1) + ": " + mxmBeauty);
		}
		out.close();
	}

	public static int findBeauty(String s){
		int value = 0;
		Integer[] list = new Integer[26];
		Arrays.fill(list, 0);
		s = s.toUpperCase();
		s = s.replaceAll("\\W", "");
		for(int i=0; i < s.length(); i++){
			list[(int) s.charAt(i) - 65] += 1;		
		}
		Arrays.sort(list, Collections.reverseOrder());
		for(int i = 0; i < list.length; i++){
			if(list[i] == 0)
				break;
			else
			value += list[i] * (26 - i);
		}
		return value;
	}
}
