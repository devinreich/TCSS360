package Controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


import Model.Bidder;
import Model.Calendar;
import Model.ContactPerson;
import Model.Organization;
import Model.PhoneNumber;
import Model.User;


public class Serializer {

	// TO SERIALIZE AN OBJECT
	// Create object as desired
	// Change path in FileOutputStream to appropriately named path
	// Change out.writeObject to contain your object
	public static void main(String[] args) {
		Bidder bidder = new Bidder("John Doe", "johnny", new PhoneNumber(555, 555, 5555), "3326 Hidden Lookout\n" + 
				"Elephant Point, Washington, 98865-8265, US");

		PhoneNumber organizationNumber = new PhoneNumber(253, 222, 4516);
		User[] users = new User[1];
		Organization testOrganization = new Organization("Ehli Auctions", 
				organizationNumber,
				"710 22 Ave SW", 
				users);

		ContactPerson cPerson =  new ContactPerson("John Ehli", "ehli22", organizationNumber, "108 F St N", testOrganization);

		Calendar calendar;
		try {
			FileOutputStream fileOut =
					new FileOutputStream("src/SerializedObjects/johnny.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(bidder);


			FileOutputStream fileOut2 =
					new FileOutputStream("src/SerializedObjects/ehli22.ser");
			ObjectOutputStream out2 = new ObjectOutputStream(fileOut2); 	         
			out2.writeObject(cPerson);
			out2.close();
			fileOut2.close();
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved");
		} catch (IOException i) {
			i.printStackTrace();
		}

		calendar = new Calendar();
		try {
			FileOutputStream fileOut3 =
					new FileOutputStream("src/SerializedObjects/calendar.ser");
			ObjectOutputStream out3 = new ObjectOutputStream(fileOut3);
			out3.writeObject(calendar);
			out3.close();
			fileOut3.close();
			System.out.printf("Serialized data is saved");
		} catch (IOException a) {
			a.printStackTrace();
		}
	}

	public void serializeCalendar(Calendar theCalendar) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("src/SerializedObjects/calendar.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(theCalendar);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void serializeContactPerson(ContactPerson theContactPerson) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("src/SerializedObjects/" + theContactPerson.getLoginName() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(theContactPerson);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	


}
