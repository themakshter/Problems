package poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class PrimeSums {
	static boolean[] primes=new boolean[10000000]; 
	static List<Integer> lPrimes;
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		fillSieve();
		lPrimes = Sieve.sieve(1000000);
		boolean left = true;
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int number;
		while (left) {
			number = Integer.parseInt(f.readLine());
			if (number == 0)
				left = false;
			else
				stuff(number);
		}
	}
	
	public static int numPrimes(){
		int count = 2;
		for(int i = 7; i <1000000;i+=2){
			if(i % 3 == 0)
				continue;
			else if(isPrime(i)){
				count++;
			}
		}
		return count;
	}
	
	public static int nextPrime(int n){
		n += 2;
		while(true){
			if(isPrime(n))
				break;
			else
			n +=2;
		}
		return n;
	}
	

public static void fillSieve() {
	    Arrays.fill(primes,true);        // assume all integers are prime.
	    primes[0]=primes[1]=false;       // we know 0 and 1 are not prime.
	    for (int i=2;i<primes.length;i++) {
	        //if the number is prime, 
	        //then go through all its multiples and make their values false.
	        if(primes[i]) {
	            for (int j=2;i*j<primes.length;j++) {
	                primes[i*j]=false;
	            }
	        }
	    }
	}

	 
	public static boolean isPrime(int n) {
	    return primes[n]; //simple, huh?
	}
	
	
	public static void stuff(int n) {
		
		boolean found = false;
		int a = 0;
		int b = 0; 
		for(int i = 0; i < lPrimes.size();i++){
			if(i > n/2)
				break;
			a = (Integer) lPrimes.get(i);
			if(isPrime(n - a)){
				b = n - a;
				found = true;
				break;
			}
		}
		if (found)
			System.out.println(n + " = " + a + " + " + b);
		else
			System.out.println("Goldbach's conjecture is wrong");

	}
}

class Sieve{
    public static List<Integer> sieve(int n){
        List<Integer> primes = new ArrayList<Integer>();
        BitSet nonPrimes = new BitSet(n+1);
 
        for (int p = 3; p * p <= n ; p = nonPrimes.nextClearBit(p+1)) {
            for (int i = p * p; i <= n; i += p)
                nonPrimes.set(i);
            primes.add(p);
        }
        return primes;
    }
}