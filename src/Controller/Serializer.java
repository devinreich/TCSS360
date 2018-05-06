package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Model.Bidder;
import Model.PhoneNumber;

public class Serializer {

	public static void main(String[] args) {
		Bidder bidder = new Bidder("John Doe", "johnny", new PhoneNumber(555, 555, 5555), "3326 Hidden Lookout\n" + 
				"Elephant Point, Washington, 98865-8265, US");
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("src/SerializedObjects/johnny.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(bidder);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in src/SerializedObjects/johnny.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}

}
