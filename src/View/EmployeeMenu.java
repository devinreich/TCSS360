package View;

import Model.Employee;

public class EmployeeMenu {
	
	private Employee employee;
	
	public EmployeeMenu(Employee theEmployee){
		employee = theEmployee;
	}
	
	public void launchMenu(){
		System.out.println("Launched Employee Menu");
	}
}
