package Model;

import java.io.Serializable;

/**
 * Calls super on User class, but with added affiliated Organization.
 * @author prancingponies
 */
public class ContactPerson extends User implements Serializable {

	private static final long serialVersionUID = 1090325710541310923L;
	private Organization affiliatedOrganization;
	
	
	public ContactPerson(String theName, String theLoginName, Organization theOrganization) {
		super(theName, theLoginName);
		affiliatedOrganization = theOrganization;
	}
	
	public Organization getAffiliatedOrganization() {
		return affiliatedOrganization;
	}	
}