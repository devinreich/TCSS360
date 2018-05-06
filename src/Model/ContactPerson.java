package Model;

import java.io.Serializable;

public class ContactPerson extends User implements Serializable {

	private static final long serialVersionUID = 7989616279774184678L;

	protected ContactPerson(String name, String loginName, PhoneNumber phoneNumber, String contactInfo) {
		super(name, loginName, phoneNumber, contactInfo);
	}

}
