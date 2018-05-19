package Model;

import java.io.Serializable;


public class Employee extends User implements Serializable {
	
	private static final long serialVersionUID = 6331904034886917476L;
	

	public Employee(String name, String loginName) {
		super(name, loginName);
	}

}