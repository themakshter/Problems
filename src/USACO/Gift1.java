package USACO;
/*
ID: mohamma86
LANG: JAVA
TASK: gift1
PROG: gift1.java
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;



public class Gift1 {
	private HashMap<String, Integer> net;
	public static void main(String[] args) throws IOException  {
		Gift1 g = new Gift1();
		g.initiate();
		//g.end();
		System.exit(0);
	}
	
	public void initiate()throws IOException{
		net = new HashMap<String, Integer>();
		BufferedReader f = new BufferedReader(new FileReader("files/in/gift1.in"));
		
		int number = Integer.parseInt(f.readLine());
		String[] people = new String[number];
		for (int i = 0; i < number; i++) {
			people[i] = f.readLine();
			net.put(people[i], 0);
		}
		for (int i = 0; i < number; i++) {
			String giver = f.readLine();
			StringTokenizer st = new StringTokenizer(f.readLine());
			int total = Integer.parseInt(st.nextToken());
			int ppl = Integer.parseInt(st.nextToken());
			//net.put(giver, total);
			String[] receiver = new String[ppl];
			if(ppl > 0){
			for (int j = 0; j < ppl; j++) {
				receiver[j] = f.readLine();
			}
			}
			calculations(giver, total, ppl, receiver);
		}
		
		end(people);
		
	}
	
	public void calculations(String name,int total, int people,String [] receiver){
			int each = 0;
			if(!(people == 0)){
					each = total/people;
				}
				for(String s : receiver){
					net.put(s, net.get(s) + each);
					net.put(name, net.get(name) - each);
				}
		
	}
	
	public void end(String [] people) throws IOException{
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"files/out/gift1.out")));
		
		for(String s : people){
			out.println(s + " " + net.get(s));
		}
		out.close();
		
	}
}
