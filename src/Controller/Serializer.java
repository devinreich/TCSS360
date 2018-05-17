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
import java.util.ArrayList;

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
		Organization testOrganization2 = new Organization("Goodwill");
		Organization testOrganization3 = new Organization("Salvation Army");
		ContactPerson cPerson =  new ContactPerson("John Ehli", "ehli22", testOrganization);
		
		//Auction auction = new Auction(LocalDate.of(2018, 6, 6), LocalDate.of(2018, 6, 6), LocalDate.now(), LocalTime.now(), 4, testOrganization);
		//auction.addItem(new Item("CZ P10-C", "Glock Killer", 499.00, LocalDate.now()));
		//testOrganization.addAuction(auction);
		//testOrganization.setCurrentAuction(auction);
		
		Calendar calendar = new Calendar();
		
		// Two auctions on same day
		Auction auction = new Auction(LocalDate.now().plusDays(Run.calendar.getMinimumUpcomingDays()), LocalDate.now(), 0, 0, testOrganization);
		Auction auction2 = new Auction(LocalDate.now().plusDays(Run.calendar.getMinimumUpcomingDays()), LocalDate.now(), 0, 0, testOrganization2);
		
		// Past auction (to see past auctions)
		Auction pastAuction = new Auction(LocalDate.now().minusDays(60), LocalDate.of(2018, 4, 15), 0, 0, testOrganization);
		
		Item item1 = new Item("Voodoo Doll", "First generation original", 499.00, LocalDate.now());
		Item item2 = new Item("Pirate Eyepatch", "Blackbeard's very own", 1000.00, LocalDate.now());
		Item item3 = new Item("Glass eye", "Belonged to Madeye Moody", 9.99, LocalDate.now());
		Item item4 = new Item("Cursed Diamond Ring", "I wouldn't risk it", 2000.00, LocalDate.now());
		Item item5 = new Item("Crown Jewels", "Very fancy", 100000.00, LocalDate.now());
		Item item6 = new Item("Sailor Jerrys", "The first bottle ever made", 299.99, LocalDate.now());
		Item item7 = new Item("Wooden Keyboard", "Don't try to use it", 20.00, LocalDate.now());
		Item item8 = new Item("Bundle of clothes", "Lightly used", 50.00, LocalDate.now());
		Item item9 = new Item("Toothbrush", "Maybe it belonged to someone famous", 2.99, LocalDate.now());
		
		
		auction.addItem(item1);
		auction.addItem(item2);
		auction.addItem(item3);
		auction2.addItem(item4);
		auction2.addItem(item5);
		auction2.addItem(item6);
		pastAuction.addItem(item7);
		pastAuction.addItem(item8);
		pastAuction.addItem(item9);
		
		/* Ehli auctions sample data */
		ArrayList<Auction> addAuctionsDirectly = new ArrayList<Auction>();
		addAuctionsDirectly.add(auction);
		addAuctionsDirectly.add(pastAuction);
		calendar.addToSchedule(testOrganization, addAuctionsDirectly);
		testOrganization.addAuctionToOrganization(auction);	/* Add to org */
		testOrganization.addAuctionToOrganization(pastAuction);
		testOrganization.setCurrentAuction(auction); /* Set current auction */
		
		/* Sample data for a second organization with 1 auction */
		addAuctionsDirectly = new ArrayList<Auction>();
		addAuctionsDirectly.add(auction2);
		calendar.addToSchedule(testOrganization2, addAuctionsDirectly);
		testOrganization2.addAuctionToOrganization(auction2);
		testOrganization2.setCurrentAuction(auction2);
		
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
	 * Serialize Bidder
	 * @param theBidder
	 */
	public void serializeBidder(Bidder theBidder) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("src/SerializedObjects/" + theBidder.getLoginName() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(theBidder);
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
