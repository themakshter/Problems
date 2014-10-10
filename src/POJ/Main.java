package POJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	public static void main(String[]args) throws NumberFormatException, IOException{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int value = 0;
		int numbers = Integer.parseInt(f.readLine());
		for(int i = 0; i < numbers; i++){
			value = Integer.parseInt(f.readLine());
			numZero(value);
		}
		
	}
	
	public static String reverse(String s) {
	    return new StringBuffer(s).reverse().toString();
	}
	
	public static void numZero(int number){
		int count = 0;
		for(int i = 0; i <=number; i++){
			if(i ==0)
				continue;
//			if(i %10 ==0)
//				count +=1;
			if(i%5 ==0 && i%2 ==0)
				count+=1;
		}
		System.out.println(count);
	}
	
	
//	public static String getFactorial(long number) {
//		BigInteger factorial = BigInteger.ONE;
//		for (BigInteger i = BigInteger.ONE; i.equals(BigInteger.valueOf(number).add(BigInteger.ONE)); i.add(BigInteger.ONE)) {
//			factorial.multiply(i);
//		}
//		System.out.println(factorial);
//		return "" + factorial;
//	}
}
