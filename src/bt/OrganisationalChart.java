package bt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * This class creates an organisational chart and find the shortest path between
 * the two specified employees. This could also be used to build a tree and used
 * for other purposes
 * 
 * @author Mohammad Ali
 *
 */
public class OrganisationalChart {

	private static HashMap<String, Integer> namesMap;
	private static HashMap<Integer, Employee> employeeMap;
	private static Employee ceo;

	/**
	 * Main method which takes in the parameters and finds the shortest path
	 * 
	 * @param args
	 *            parameters which will be taken in (filename, employee 1 and
	 *            employee 2)
	 */
	public static void main(String[] args) {
		// usage is incorrect
		if (args.length > 0) {
			String fileName = args[0];
			String name1 = args[1];
			String name2 = args[2];
			try {
				// create our chart
				buildChart(fileName);
			} catch (FileNotFoundException e) {
				System.err
						.println("Error! There was something wrong with the file specified");
			} catch (IOException e) {
				System.err
						.println("Error! Something went wrong while reading the file");
				e.printStackTrace();
			}
			Employee employee1 = employeeMap.get(namesMap
					.get(constructNormalisedName(name1.split("\"")[0])));
			Employee employee2 = employeeMap.get(namesMap
					.get(constructNormalisedName(name2.split("\"")[0])));
			findWay(employee1, employee2);
		} else {
			System.err
					.println("Error! Usage is OrganisationalChart (filename) (employee1) (employee2)");
		}

	}

	/**
	 * Method to create our organisational chart
	 * 
	 * @param fileName
	 *            the text file containing all the employee records
	 * @throws IOException
	 *             possible problems in opening and reading through the file
	 */
	public static void buildChart(String fileName) throws IOException {
		namesMap = new HashMap<String, Integer>();
		employeeMap = new HashMap<Integer, Employee>();
		BufferedReader buff = new BufferedReader(new FileReader(fileName));
		String line = buff.readLine();
		line = buff.readLine(); // the headings
		while (line != null) {
			parseEmployee(line.trim());
			line = buff.readLine();
		}
		buff.close();
	}

	/**
	 * Method to create an Employee class for each line in the text file and add
	 * references to the HashMap
	 * 
	 * @param line
	 *            an employee record
	 */
	public static void parseEmployee(String line) {
		String[] bits = line.split(Pattern.quote("|"));
		int start = 0;
		if (bits[start].length() == 0) {
			start = 1;
		}
		int employeeId = Integer.parseInt(bits[start].trim());
		String name = bits[start + 1];
		Employee employee = employeeMap.get(employeeId);
		if (employee == null) {
			// employee does not exist so create a new one
			employee = new Employee(employeeId, name);
		} else {
			// employee was already created, so we set the unset values
			employee.setName(name);
		}
		String managerString = bits[start + 2].trim();
		if (managerString.length() > 0) {
			int managerId = Integer.parseInt(managerString);
			Employee manager = employeeMap.get(managerId);
			if (manager == null) {
				// manager does not exist so we create a new one
				manager = new Employee(managerId);
			}
			employee.setManager(manager);
			manager.addEmployee(employee);
			employeeMap.put(managerId, manager);
		} else {
			// this is the root of our tree
			ceo = employee;
		}
		namesMap.put(constructNormalisedName(employee.getName()),
				employee.getId());
		employeeMap.put(employee.getId(), employee);
	}

	/**
	 * Method to find the shortest path between the employees and printing it
	 * 
	 * @param to
	 *            the employee from whom we want to find the path
	 * @param from
	 *            the employee to whom we want to find the path
	 */
	public static void findWay(Employee to, Employee from) {
		int ancestor = findLowestCommonAncestor(to, from);
		printPaths(to, from, employeeMap.get(ancestor));
	}

	/**
	 * Method to find the lowest common ancestor between the two employees
	 * 
	 * @param to
	 *            the employee from whom we want to find the path
	 * @param from
	 *            the employee to whom we want to find the path
	 * @return the lowest common ancestor between the two employees
	 */
	public static int findLowestCommonAncestor(Employee to, Employee from) {
		ArrayList<Integer> paths = getPathToRoot(to);
		while (!from.equals(ceo)) {
			int id = from.getId();
			if (paths.contains(id)) {
				return id;
			} else {
				from = from.getManager();
			}
		}
		return ceo.getId();
	}

	/**
	 * Method to find the path from an employee (node) to the ceo (root)
	 * 
	 * @param employee
	 *            the employee from which to find the path
	 * @return the list of the employee ids present in this path
	 */
	public static ArrayList<Integer> getPathToRoot(Employee employee) {
		ArrayList<Integer> paths = new ArrayList<Integer>();
		paths.add(employee.getId());
		while (!employee.equals(ceo)) {
			Employee manager = employee.getManager();
			paths.add(manager.getId());
			employee = manager;
		}
		return paths;
	}

	/**
	 * Method to print the shortest path between the two specified employees
	 * 
	 * @param to
	 *            the employee from whom we want to find the path
	 * @param from
	 *            the employee to whom we want to find the path
	 * @param ancestor
	 *            the lowest common ancestor between the two employees
	 */
	public static void printPaths(Employee to, Employee from, Employee ancestor) {
		if (to.equals(from)) {
			System.out.println(to.getName() + " (" + to.getId() + ")");
		} else if (from.equals(ancestor)) {
			System.out.println(getPathTo(to, ancestor) + ancestor.getName()
					+ " (" + ancestor.getId() + ")");
		} else {
			System.out.println(getPathTo(to, ancestor) + ancestor.getName()
					+ " (" + ancestor.getId() + ")"
					+ getPathFrom(from, ancestor));
		}
	}

	/**
	 * Method to write path from an employee to another. This will be from the
	 * employee to the ancestor
	 * 
	 * @param to
	 *            the employee from whom we want to write the path
	 * @param from
	 *            the employee to whom we want to write the path
	 * @return the string containing the path
	 */
	public static String getPathTo(Employee to, Employee from) {
		String result = "";
		String space = " -> ";
		while (!to.equals(from)) {
			result += to.getName() + " (" + to.getId() + ")" + space;
			to = to.getManager();
		}
		return result;
	}

	/**
	 * Method to write path to an employee to another. This will be from the
	 * ancestor to the employee
	 * 
	 * @param to
	 *            the employee from whom we want to write the path
	 * @param from
	 *            the employee to whom we want to write the path
	 * @return the string containing the path
	 */
	public static String getPathFrom(Employee to, Employee from) {
		String result = "";
		String space = " <- ";
		while (!to.equals(from)) {
			result = space + to.getName() + " (" + to.getId() + ")" + result;
			to = to.getManager();
		}
		return result;
	}

	/**
	 * Utility method to construct normalised string from given name
	 * 
	 * @param originalName
	 *            the given name which may have unecessary whitespaces
	 * @return the normalised name
	 */
	public static String constructNormalisedName(String originalName) {
		StringBuilder sb = new StringBuilder();
		String[] bits = originalName.toLowerCase().trim().split(" ");
		for (String bit : bits) {
			sb.append(bit);
		}
		return sb.toString();
	}
}
