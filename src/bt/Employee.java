package bt;

import java.util.ArrayList;

public class Employee {
	private int id;
	private String name;
	private Employee manager;
	private ArrayList<Employee> managerOf;
	public Employee(int employeeId, String name){
		this(employeeId);
		this.name = name.trim();
	}
	
	public Employee(int id){
		this.id = id;
		managerOf = new ArrayList<Employee>();
	}
	
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int employeeId){
		this.id = employeeId;
	}		
	
	public Employee getManager(){
		return manager;
	}
	
	public void setManager(Employee manager){
		this.manager = manager;
	}
	
	public void addEmployee(Employee employee){
		this.managerOf.add(employee);
	}
	
	public ArrayList<Employee> getEmployees(){
		return managerOf;
	}
	
	public boolean equals(Employee employee){
		return this.id == employee.getId();
	}
	
}
