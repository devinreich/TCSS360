package Model;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
	private Date startDate;
	private Date endDate;
	private int maxItemsPerBidder;
	private static final int DEFAULT_MAX_ITEMS = 5;
	private Organization forOrgainization;
	private Date creationDate;
	private String location; // make a location object?
	private ArrayList<Item> inventory;
	
	
	public void addItem(Item item) {
		// check if legal?
		inventory.add(item);
	}
}
