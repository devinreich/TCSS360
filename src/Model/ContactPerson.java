package Model;

import java.io.Serializable;

public class ContactPerson extends User implements Serializable {

	private static final long serialVersionUID = 7989616279774184678L;
	private Organization affiliatedOrganization;
	
	
	public ContactPerson(String name, String loginName, PhoneNumber phoneNumber, String contactInfo,
						   Organization organization) {
		super(name, loginName, phoneNumber, contactInfo);
		affiliatedOrganization = organization;
	}
	
	public Organization getAffiliatedOrganization() {
		return affiliatedOrganization;
	}
	
	
}
