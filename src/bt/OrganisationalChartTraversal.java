package bt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Main {
	private static HashMap<String,Integer> nameToEmployeeId;
	private static HashMap<Integer, String> employeeIdToName;
	private static HashMap<Integer, Integer> employeeIdToManagerId;
	
	public static void main(String[] args) {
		String fileName = "files/in/superheroes.txt";
		String employee1 = "Batman";
		String employee2 = "Super Ted";
		if(args.length > 0){
			fileName = args[0];
			employee1 = args[1];
			employee2 = args[2];
		}
		try {
			buildMaps(fileName);
		} catch (FileNotFoundException e) {
			System.err.println("Error! There was something wrong with the file specified");
		} catch (IOException e) {
			System.err.println("Error! Something went wrong while reading the file");
			e.printStackTrace();
		}
		findWay(employee1,employee2);
	}
	
	public static void buildMaps(String fileName) throws IOException{
		nameToEmployeeId = new HashMap<String, Integer>();
		employeeIdToName = new HashMap<Integer,String>();
		employeeIdToManagerId = new HashMap<Integer, Integer>();
		BufferedReader buff  = new BufferedReader(new FileReader(fileName));
		String line = buff.readLine();
		line = buff.readLine(); //the headings
		String[] bits;
		while(line != null){
			parseEmployee(line.toLowerCase().trim());
			line = buff.readLine();
		}
	}
	
	public static void parseEmployee(String line){
		String[] bits = line.split(Pattern.quote("|"));
		int start = 0;
		if(bits[start].length() == 0){
			start = 1;
		}
		int employeeId = Integer.parseInt(bits[start].trim());
		String name = bits[start+1];
		nameToEmployeeId.put(name, employeeId);
		employeeIdToName.put(employeeId, name);
		String out = employeeId + "\t" + name;
		String manager = bits[start+2].trim();
		if(manager.length() > 0){
			int managerId = Integer.parseInt(manager);
			employeeIdToManagerId.put(employeeId, managerId);
			out+="\t"+managerId;
		}
		System.out.println(out);
	}
	
	public static void findWay(String to,String from){
		int employee1Id = nameToEmployeeId.get(to);
		int employee2Id = nameToEmployeeId.get(from);
		int ancestor = findLowestCommonAncestor(employee1Id,employee2Id);
	}
	
	public static int findLowestCommonAncestor(int to, int from){
		int lastAddedValue;
		HashSet<Integer> ancestorSet = new HashSet<Integer>();
		
		return 0;
	}
	
}
