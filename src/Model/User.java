package Model;

import java.io.Serializable;

public abstract class User implements Serializable {
	
	private static final long serialVersionUID = -898640423353892899L;
	private String name;
	private String loginName; // might remove login name, probably will be a key to the value User
	private PhoneNumber phoneNumber;
	private String contactInfo; // could make contact info class

	protected User(String name, String loginName, PhoneNumber phoneNumber, String contactInfo) {
		this.name = name;
		this.loginName = loginName;
		this.phoneNumber = phoneNumber;
		this.contactInfo = contactInfo;
	}

	// probably wont use this. Login will most likely generate the user
	public void login() {

	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber.toString();
	}

	public String contactInfo() {
		return contactInfo;
	}
}
