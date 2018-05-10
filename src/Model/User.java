package Model;

import java.io.Serializable;

public abstract class User implements Serializable {
	
	private static final long serialVersionUID = 106144177927114562L;
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

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber.toString();
	}

	public String contactInfo() {
		return contactInfo;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
}
