package bt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class OrganisationalChartTraversal {
	private static HashMap<String,Integer> nameToEmployeeId;
	private static HashMap<Integer, String> employeeIdToName;
	private static HashMap<Integer, Integer> employeeIdToManagerId;
	
	public static void main(String[] args) {
		String fileName = "files/in/superheroes.txt";
		String employee1 = "Batman";
		String employee2 = "Black Widow";
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
		findWay(employee1.toLowerCase(),employee2.toLowerCase());
	}
	
	public static void buildMaps(String fileName) throws IOException{
		nameToEmployeeId = new HashMap<String, Integer>();
		employeeIdToName = new HashMap<Integer,String>();
		employeeIdToManagerId = new HashMap<Integer, Integer>();
		BufferedReader buff  = new BufferedReader(new FileReader(fileName));
		String line = buff.readLine();
		line = buff.readLine(); //the headings
		while(line != null){
			parseEmployee(line.toLowerCase().trim());
			line = buff.readLine();
		}
		buff.close();
	}
	
	public static void parseEmployee(String line){
		String[] bits = line.split(Pattern.quote("|"));
		int start = 0;
		if(bits[start].length() == 0){
			start = 1;
		}
		int employeeId = Integer.parseInt(bits[start].trim());
		String name = bits[start+1];
		nameToEmployeeId.put(name.trim(), employeeId);
		employeeIdToName.put(employeeId, name.trim());
		String manager = bits[start+2].trim();
		if(manager.length() > 0){
			int managerId = Integer.parseInt(manager);
			employeeIdToManagerId.put(employeeId, managerId);
		}
	}
	
	public static void findWay(String to,String from){
		int employee1Id = nameToEmployeeId.get(to);
		int employee2Id = nameToEmployeeId.get(from);
		int ancestor = findLowestCommonAncestor(employee1Id,employee2Id);
		printPaths(employee1Id, employee2Id, ancestor);
	}
	
	public static int findLowestCommonAncestor(int to, int from){
		ArrayList<Integer> paths = getPathToRoot(to);
		int node = from;
		while(employeeIdToManagerId.get(node)!=null){
			if(paths.contains(node)){
				return node;
			}else{
				node = employeeIdToManagerId.get(node);
			}
		}
		return node;
	}
	
	public static ArrayList<Integer> getPathToRoot(int node){
		ArrayList<Integer> paths = new ArrayList<Integer>();
		paths.add(node);
		while(employeeIdToManagerId.get(node) != null){
			int manager = employeeIdToManagerId.get(node);
			paths.add(manager);
			node = manager;
		}		
		return paths;
	}
	
	public static void printPaths(int to, int from, int ancestor){
		if(to == from){
			System.out.println(employeeIdToName.get(to) + " (" + to + ")");
		}else if(from == ancestor){
			System.out.println(printTo(to, ancestor) + employeeIdToName.get(ancestor) + " (" + ancestor + ")");
		}else{
		System.out.println(printTo(to, ancestor) + employeeIdToName.get(ancestor) + " (" + ancestor + ")" + printFrom(from, ancestor));
		}
	}
		
	
	public static String printTo(int to, int from){
		int node = to;
		String space = " -> ";
		String result = employeeIdToName.get(node) + " ("+node+")" + space;
		while(employeeIdToManagerId.get(node) != from){
			node = employeeIdToManagerId.get(node);
			result+= employeeIdToName.get(node) + "(" + node + ")" + space;
		}
		return result;
	}
	
	public static String printFrom(int to, int from){
		int node = to;
		String space = " <- ";
		String result =  space + employeeIdToName.get(node) + " (" + node + ")";
		while(employeeIdToManagerId.get(node)!= from){
			node = employeeIdToManagerId.get(node);
			result = space + employeeIdToName.get(node) + " (" + node + ")" + result;
		}
		return result;
	}
	
	
}
