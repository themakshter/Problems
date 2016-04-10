package google.codejam.y16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FlippingPancakes {
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader reader = new BufferedReader(new FileReader("files/in/flippingPancakes.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"files/out/flippingPancakes.out")));
		int cases = Integer.parseInt(reader.readLine());
		for (int i = 0; i < cases; i++) {
			PancakeStack pancakeStack = new PancakeStack(reader.readLine());
			out.println("Case #" + (i+1) + ": " + pancakeStack.getMovesForCompletelyHappyStack());
		}
		out.close();
	}
}

class PancakeStack{
	ArrayList<Pancake> pancakes;
	public PancakeStack(String pancakeString){
		pancakes = new ArrayList<Pancake>();
		String[] pancakes = pancakeString.split("");
		for(String pancake:pancakes){
			this.pancakes.add(new Pancake(pancake.equals("+")));
		}
	}
	
	public int getMovesForCompletelyHappyStack(){
		int moves = 0;
		// start from the bottom (and now we here)
		int currentPancakeIndex = pancakes.size();
		while(!isHappyStack()){
			currentPancakeIndex--;
			if(!pancakes.get(currentPancakeIndex).isHappy){
				if(pancakes.get(0).isHappy){
					int tempIndex = currentPancakeIndex-1;
					while(!pancakes.get(tempIndex).isHappy){
						tempIndex--;
					}
					flipStack(tempIndex);
					moves++;
				}
				flipStack(currentPancakeIndex);
				moves++;
			}
		}
		return moves;
	}
	
	public void flipStack(int stackIndex){
		int i = 0; 
		int j = stackIndex;
		while(i <= j){
			Pancake iPancake = new Pancake(pancakes.get(i));
			Pancake jPancake = new Pancake(pancakes.get(j));
			iPancake.isHappy = !iPancake.isHappy;
			jPancake.isHappy = !jPancake.isHappy;
			pancakes.set(i, jPancake);
			pancakes.set(j, iPancake);
			i++;
			j--;
		}
	}
	
	public boolean isHappyStack(){
		for(Pancake pancake: pancakes){
			if(!pancake.isHappy)
				return false;
		}
		return true;
	}
	
	
}

class Pancake{
	public boolean isHappy;
	public Pancake(boolean isHappy){
		this.isHappy = isHappy;
	}
	
	public Pancake(Pancake pancake){
		this(pancake.isHappy);
	}
	
	
}
