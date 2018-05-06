package Model;

import java.time.LocalDate;

public class Item {
	private String name;
	private String description;
	private Double basePrice;
	private Bid highestBid;
	private LocalDate dateCreated;
	
	public Item(String name, String description, Double basePrice, LocalDate dateCreated) {
		this.name = name;
		this.description = description;
		this.basePrice = basePrice;
		this.dateCreated = dateCreated;
	}
	
	public Item(String name, String description, Double basePrice, LocalDate dateCreated, Bid initialBid) {
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
	
	public LocalDate getCreationDate() {
		return dateCreated;
	}
}
