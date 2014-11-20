package bt;

import java.util.ArrayList;

/**
 * This is a class created for a representation for each Employee. The employee
 * will have an id, a name and link to their manager. Employees also have a link
 * to all the employees they are managers of. This functionality is not used at
 * the moment but could be
 * 
 * @author Mohammad Ali
 *
 */
public class Employee {
	private int id;
	private String name;
	private Employee manager;
	private ArrayList<Employee> managerOf;

	/**
	 * Constructor for the class
	 * 
	 * @param id
	 *            the employee id
	 * @param name
	 *            the employee name
	 */
	public Employee(int id, String name) {
		this(id);
		this.name = name.trim();
	}

	/**
	 * Constructor for the class without the name
	 * 
	 * @param id
	 *            the employee id
	 */
	public Employee(int id) {
		this.id = id;
		managerOf = new ArrayList<Employee>();
	}

	/**
	 * Method to retrieve employee name
	 * 
	 * @return the employee name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set employee name
	 * 
	 * @param name
	 *            the employee name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to retrieve employee id
	 * 
	 * @return the employee id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method to set the employee id
	 * 
	 * @param employeeId
	 *            the employee id to be set
	 */
	public void setId(int employeeId) {
		this.id = employeeId;
	}

	/**
	 * Method to retrieve manager of the employee
	 * 
	 * @return the employee manager
	 */
	public Employee getManager() {
		return manager;
	}

	/**
	 * Method to set manager of the employee
	 * 
	 * @param manager
	 *            the manager to be set
	 */
	public void setManager(Employee manager) {
		this.manager = manager;
	}

	/**
	 * Add an employee to the list of employees this one manages
	 * 
	 * @param employee
	 *            the employee to be added
	 */
	public void addEmployee(Employee employee) {
		this.managerOf.add(employee);
	}

	/**
	 * Retrieve the list of employees managed by this employee
	 * 
	 * @return the list of employees managed by this employee
	 */
	public ArrayList<Employee> getEmployees() {
		return managerOf;
	}

	/**
	 * Custom method to compare two employees
	 * 
	 * @param employee
	 *            the employee to be compared
	 * @return confirmation or declination of equality
	 */
	public boolean equals(Employee employee) {
		return this.id == employee.getId();
	}

}
