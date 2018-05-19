package FileInput;

import java.io.*;
import java.util.Scanner;

import Controller.Serializer;
import Model.*;


public class ReadTextFile {
	
	
	
	
	public static void main(String[] args) {
		try {
			readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void readFile() throws IOException {
		Scanner scanner;
		File input;
		File folder = new File("src/Files");
		File[] listOfFiles = folder.listFiles();
		for (int i  = 0; i < listOfFiles.length; i++){
			input = listOfFiles[i];
			if (input.isFile() && input.getName().endsWith(".txt")){
				scanner = new Scanner(input);
				switch (scanner.next()){
					case("bidder"):
						makeBidder(scanner, input);
						break;
					case("auction"):
						makeAuction(scanner, input);
						break;
					case("contact"):
						makeContactPerson(scanner, input);
						break;
					case("employee"):
						makeEmployee(scanner, input);
						break;
					case("item"):
						makeItem(scanner, input);
						break;
					case("calendar"):
						makeCalendar(scanner, input);
						break;
					default:
						System.out.println("invalid input file");
						break;
				}	
			
			}
			
		}
//		FileInputStream input = new FileInputStream("src/Files/input.txt");
//		Scanner scanner = new Scanner(input);
		
	}

	private static void makeCalendar(Scanner theScanner, File theFile) {
			
	}

	private static void makeItem(Scanner theScanner, File theFile) {
		// TODO Auto-generated method stub
		
	}

	private static void makeEmployee(Scanner theScanner, File theFile) {
		// TODO Auto-generated method stub
		
	}

	private static void makeContactPerson(Scanner theScanner, File theFile) {
		theScanner.nextLine();	
		String name = theScanner.nextLine();
		System.out.println("name = "+ name);
		String logInName = theScanner.nextLine();
		System.out.println("logInName = "+ logInName);
		String orgName = theScanner.nextLine();
		System.out.println("orgName = "+ orgName);
		Organization org = new Organization(orgName);
		
		ContactPerson cPerson = new ContactPerson(name,logInName,org);
		System.out.println(cPerson);
		Serializer.serialize(cPerson, logInName);
		System.out.println("Serialized");
		
	}

	private static void makeAuction(Scanner theScanner, File theFile) {
		// TODO Auto-generated method stub
		
	}

	private static void makeBidder(Scanner theScanner, File theFile) {
		// TODO Auto-generated method stub
		
	}

}
