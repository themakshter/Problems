package bt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class OrganisationalChart {
	private static HashMap<String, Integer> namesMap;
	private static HashMap<Integer, Employee> employeeMap;
	private static Employee ceo;
	public static void main(String[] args) {
		String fileName = "files/in/superheroes.txt";
		String name1 = "Batman";
		String name2 = "    batman";
		if(args.length > 0){
			fileName = args[0];
			name1 = args[1];
			name2 = args[2];
		}
		try {
			buildMaps(fileName);
		} catch (FileNotFoundException e) {
			System.err.println("Error! There was something wrong with the file specified");
		} catch (IOException e) {
			System.err.println("Error! Something went wrong while reading the file");
			e.printStackTrace();
		}
		Employee employee1 = employeeMap.get(namesMap.get(Util.constructNormalisedName(name1)));
		Employee employee2 = employeeMap.get(namesMap.get(Util.constructNormalisedName(name2)));
		findWay(employee1,employee2);
	}
	
	public static void buildMaps(String fileName) throws IOException{
		namesMap = new HashMap<String, Integer>();
		employeeMap = new HashMap<Integer, Employee>();
		BufferedReader buff  = new BufferedReader(new FileReader(fileName));
		String line = buff.readLine();
		line = buff.readLine(); //the headings
		while(line != null){
			parseEmployee(line.trim());
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
		Employee employee = employeeMap.get(employeeId);
		if(employee == null){
			//employee does not exist so create a new one
			employee = new Employee(employeeId, name);
		}else{
			//employee was already created, so we set the unset values
			employee.setName(name);
		}
		String managerString = bits[start+2].trim();
		if(managerString.length() > 0){
			int managerId = Integer.parseInt(managerString);
			Employee manager = employeeMap.get(managerId);
			if(manager == null){ 
			//manager does not exist so we create a new one
			manager = new Employee(managerId);				
			}
			employee.setManager(manager);
			manager.addEmployee(employee);
			employeeMap.put(managerId, manager);
		}else{
			//this is the root of our tree
			ceo = employee;
		}
		namesMap.put(Util.constructNormalisedName(employee.getName()), employee.getId());
		employeeMap.put(employee.getId(), employee);
	}
	
	public static void findWay(Employee to,Employee from){
		int ancestor = findLowestCommonAncestor(to,from);
		printPaths(to, from, employeeMap.get(ancestor));
	}
	
	public static int findLowestCommonAncestor(Employee to, Employee from){
		ArrayList<Integer> paths = getPathToRoot(to);
		while(!from.equals(ceo)){
			int id = from.getId();
			if(paths.contains(id)){
				return id;
			}else{
				from = from.getManager();
			}
		}
		return ceo.getId();
	}
	
	public static ArrayList<Integer> getPathToRoot(Employee employee){
		ArrayList<Integer> paths = new ArrayList<Integer>();
		paths.add(employee.getId());
		while(!employee.equals(ceo)){
			Employee manager = employee.getManager();
			paths.add(manager.getId());
			employee = manager;
		}		
		return paths;
	}
	
	public static void printPaths(Employee to, Employee from, Employee ancestor){
		if(to.equals(from)){
			System.out.println(to.getName() + " (" + to.getId() + ")");
		}else if(from.equals(ancestor)){
			System.out.println(printTo(to, ancestor) + ancestor.getName() + " (" + ancestor.getId() + ")");
		}else{
		System.out.println(printTo(to, ancestor) + ancestor.getName()+ " (" + ancestor.getId() + ")" + printFrom(from, ancestor));
		}
	}
		
	
	public static String printTo(Employee to, Employee from){
		String result = "";
		String space = " -> ";
		while(!to.equals(from)){
			result+= to.getName() + " (" + to.getId() + ")" + space;
			to = to.getManager();
		}
		return result;
	}
	
	public static String printFrom(Employee to, Employee from){
		String result = "";
		String space = " <- ";
		while(!to.equals(from)){
			result = space + to.getName() + " (" + to.getId() + ")" + result;
			to = to.getManager();
		}
		return result;
	}
	
	
}
