package google.codejam.y16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class CountingSheep {

	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader f = new BufferedReader(new FileReader("files/in/countingSheep.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"files/out/countingSheep.out")));
		int cases = Integer.parseInt(f.readLine());
		for (int i = 0; i < cases; i++) {
			int n = Integer.parseInt(f.readLine());
			out.println("Case #"+ (i+1) + ": " + findSleepNumber(n));
		}
		out.close();
	}
	
	public static String findSleepNumber(int n){
		if(n == 0)
			return "INSOMNIA";
		HashSet<Integer> seenNumbers = new HashSet<Integer>();
		int multiplier = 1;
		Integer nMultiplier = n;
		boolean numbersFound = false;
		while(!numbersFound){
			nMultiplier = n * multiplier;
			String[] numbers = nMultiplier.toString().split("");
			for(String number:numbers){
				seenNumbers.add(Integer.parseInt(number));
			}
			if(seenNumbers.size() == 10)
				numbersFound = true; 
			multiplier++;
		}
		return nMultiplier.toString();		
	}
}
