package Model;

import java.util.Date;

public class Item {
	private String name;
	private String description;
	private Double basePrice;
	private Bid highestBid;
	private Date dateCreated;
	
	public Item(String name, String description, Double basePrice, Date dateCreated) {
		this.name = name;
		this.description = description;
		this.basePrice = basePrice;
		this.dateCreated = new Date(dateCreated.getTime());
	}
	
	public Item(String name, String description, Double basePrice, Date dateCreated, Bid initialBid) {
		this(name, description, basePrice, dateCreated);
		this.highestBid = initialBid;
	}
	
	public Bid getBid() {
		return highestBid;
	}
	
	public void setBid(Bid highestBid) {
		this.highestBid = highestBid; // do bid comparison here
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Double getBasePrice() {
		return basePrice; // maybe do string formating here to make it currency
	}
	
	public Date getCreationDate() {
		return new Date(dateCreated.getTime());
	}
}
