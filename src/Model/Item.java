package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Item implements Serializable {
	
	private static final long serialVersionUID = -1992750129514273431L;
	private String name;
	private String description;
	private Double basePrice;
	//private Bid highestBid;
	private LocalDate dateCreated;
	
	public Item(String name, String description, Double basePrice, LocalDate dateCreated) {
		this.name = name;
		this.description = description;
		this.basePrice = basePrice;
		this.dateCreated = dateCreated;
		//highestBid = null;
	}
	
	//public Item(String name, String description, Double basePrice, LocalDate dateCreated, Bid initialBid) {
	//	this(name, description, basePrice, dateCreated);
		//this.highestBid = initialBid;
	//}
	
//	public Bid getHighestBid() {
//		return highestBid;
//	}
//	
//	public void setBid(Bid highestBid) {
//		this.highestBid = highestBid; // do bid comparison here
//	}
	
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
	
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj instanceof Item) {
			Item otherItem = (Item) obj;
			if (this.name.equals(otherItem.name) && this.description.equals(otherItem.description)
					&& this.basePrice.equals(otherItem.basePrice) && this.dateCreated.equals(otherItem.dateCreated)) {
				return true;
			}
		}
		return false;
	}
}