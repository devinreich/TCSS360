package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


import Model.Bidder;
import Model.Calendar;
import Model.PhoneNumber;


public class Serializer {
	
	// TO SERIALIZE AN OBJECT
	// Create object as desired
	// Change path in FileOutputStream to appropriately named path
	// Change out.writeObject to contain your object
	public static void main(String[] args) {
		Bidder bidder = new Bidder("John Doe", "johnny", new PhoneNumber(555, 555, 5555), "3326 Hidden Lookout\n" + 
				"Elephant Point, Washington, 98865-8265, US");
		Calendar calendar;
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("src/SerializedObjects/johnny.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(bidder);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		
		calendar = new Calendar();
		try {
			FileOutputStream fileOut =
			new FileOutputStream("src/SerializedObjects/calendar.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(calendar);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved");
			} catch (IOException a) {
			   a.printStackTrace();
			}
	}

}
