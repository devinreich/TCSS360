package Controller;

import Model.User;
import java.util.Scanner;

public class Run {
	
	private User user;
	private String userType;

	public static void main(String[] args) {
		login();
	}
	
	private static void login() {
		System.out.println("Welcome to Auction Central");
		System.out.println("Please provide your username:");
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		// retrieve user from serialized object
	}
}
