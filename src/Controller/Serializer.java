package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import Model.Auction;
import Model.Bidder;
import Model.Calendar;
import Model.ContactPerson;
import Model.Item;
import Model.Organization;


public class Serializer {

	// TO SERIALIZE AN OBJECT
	// Create object as desired
	// Change path in FileOutputStream to appropriately named path
	// Change out.writeObject to contain your object
	public static void main(String[] args) {
		
		Bidder bidder = new Bidder("John Doe", "johnny");
		Organization testOrganization = new Organization("Ehli Auctions");
		ContactPerson cPerson =  new ContactPerson("John Ehli", "ehli22", testOrganization);
		
		//Auction auction = new Auction(LocalDate.of(2018, 6, 6), LocalDate.of(2018, 6, 6), LocalDate.now(), LocalTime.now(), 4, testOrganization);
		//auction.addItem(new Item("CZ P10-C", "Glock Killer", 499.00, LocalDate.now()));
		//testOrganization.addAuction(auction);
		//testOrganization.setCurrentAuction(auction);
		
		Calendar calendar = new Calendar();
		
		// Future
		Auction auction = new Auction(LocalDate.of(2018, 5, 20), LocalDate.now(), 0, 0, testOrganization);
		
		// Current Day
		Auction auction2 = new Auction(LocalDate.of(2018, 5, 15), LocalDate.now(), 0, 0, testOrganization);
		
		// Past
		Auction auction3 = new Auction(LocalDate.of(2017, 4, 15), LocalDate.of(2018, 4, 15), 0, 0, testOrganization);
		
		Item item1 = new Item("Voodoo Doll", "First generation original", 499.00, LocalDate.now());
		Item item2 = new Item("Pirate Eyepatch", "Blackbeard's very own", 1000.00, LocalDate.now());
		Item item3 = new Item("Glass eye", "Belonged to Madeye Moody", 9.99, LocalDate.now());
		
		calendar.submitAuctionRequestWithAuction(testOrganization, auction);
		auction.addItem(item1);
		auction.addItem(item2);
		auction.addItem(item3);
		calendar.submitAuctionRequestWithAuction(testOrganization, auction2);
		auction2.addItem(item1);
		calendar.submitAuctionRequestWithAuction(testOrganization, auction3);
		auction3.addItem(item1);	
		
		try {
			FileOutputStream fileOut = new FileOutputStream("src/SerializedObjects/johnny.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(bidder);
			out.close();
			fileOut.close();
			
			FileOutputStream fileOut2 = new FileOutputStream("src/SerializedObjects/ehli22.ser");
			ObjectOutputStream out2 = new ObjectOutputStream(fileOut2); 	 
			out2.writeObject(cPerson);
			out2.close();
			fileOut2.close();
			
			System.out.println("Serialized data for username is saved");
			
		} catch (IOException i) {
			i.printStackTrace();
		}

	
		
		try {
			FileOutputStream fileOut4 = new FileOutputStream("src/SerializedObjects/calendar.ser");
			ObjectOutputStream out4 = new ObjectOutputStream(fileOut4);
			out4.writeObject(calendar);
			out4.close();
			fileOut4.close();
			System.out.println("Serialized data for calendar is saved");
		} catch (IOException a) {
			a.printStackTrace();
		}
	}
	
	
	/**
	 * Serialize the Calendar
	 * @param theCalendar
	 */
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
	
	
	/** 
	 * Serialize Contact Person
	 * @param theContactPerson
	 */
	public void serializeContactPerson(ContactPerson theContactPerson) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("src/SerializedObjects/" + theContactPerson.getLoginName() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(theContactPerson);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			System.out.println("No such contact person exists");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Serialize path
	 * @param obj
	 * @param path
	 */
	public static void serialize(Serializable obj, String path) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("src/SerializedObjects/" + path + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Deserialize
	 * @param path
	 * @return serialized object file
	 */
	public static Serializable deserialize(String path) {
		Serializable ser = null;
		try {
			FileInputStream fileIn = new FileInputStream("src/SerializedObjects/" + path +".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ser = (Serializable) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not Found");
			c.printStackTrace();
		}
		return ser;
	}
	
	/**
	 * Deserialize Calendar
	 * @param path
	 * @return serialized object file
	 */
	public static Serializable deserializeCalendar(Calendar calendar) {
		Serializable ser = null;
		try {
			FileInputStream fileIn = new FileInputStream("src/SerializedObjects/" + "calendar.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ser = (Serializable) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not Found");
			c.printStackTrace();
		}
		return ser;
	}
}
