package USACO;
/*
ID: mohamma86
LANG: JAVA
TASK: ride
PROG: ride.java
*/
import java.io.*;

class Ride {
  public static void main (String [] args) throws IOException {
	Ride t = new Ride();
	//   Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("files/in/ride.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("files/out/ride.out")));
    String comet = f.readLine();    
    String group = f.readLine();     
    String answer = "";
	  
    if(t.getProduct(group) == t.getProduct(comet)) answer = "GO";
	else answer = "STAY";
    
    out.println(answer);                           // output result
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
  
  public int getProduct(String s){
	  s.toUpperCase();
//	  String[] letters = s.split("");
//	  
	  int i = 1;
	  for(int j = 0; j < s.length(); j++){
		  int value = s.charAt(j) - 'A' + 1;
		  i = (i * value) % 47;
	  }
	  
	  return i;
  }
  
}
