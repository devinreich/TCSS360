package Model;

import java.io.Serializable;

/**
 * User class. Requires a User & a login name. 
 * @author prancingponies
 *
 */
public abstract class User implements Serializable {
	
	private static final long serialVersionUID = 106144177927114562L;
	private String name;
	private String loginName; 
	//private String contactInfo;
	
	/**
	 * The super constructor for the Contact Person & Bidder classes. 
	 * @param theName
	 * @param theLoginName
	 */
	protected User(String theName, String theLoginName) {
		this.name = theName;
		this.loginName = theLoginName;
	}

	public String getName() {
		return name;
	}

//	public String contactInfo() {
//		return contactInfo;
//	}
	
	public String getLoginName() {
		return loginName;
	}
	
}