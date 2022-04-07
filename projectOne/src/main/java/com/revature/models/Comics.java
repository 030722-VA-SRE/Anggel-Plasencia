package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comics")
public class Comics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	private String description;
	private String genre;
	private String type;
	private int price;
	@ManyToOne
	@JoinColumn(name = "purchaser_id")
	private User purchaserId;

	
	public Comics() {
		super();
		
	}

	public Comics(int id, String name, String description, String genre, String type, int price, User purchaserId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.genre = genre;
		this.type = type;
		this.price = price;
		this.purchaserId = purchaserId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public User getPurchaserId() {
		return purchaserId;
	}


	public void setPurchaserId(User purchaserId) {
		this.purchaserId = purchaserId;
	}


	@Override
	public int hashCode() {
		return Objects.hash(description, genre, id, name, price, purchaserId, type);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comics other = (Comics) obj;
		return Objects.equals(description, other.description) && Objects.equals(genre, other.genre) && id == other.id
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(purchaserId, other.purchaserId) && Objects.equals(type, other.type);
	}


	@Override
	public String toString() {
		return "Comics [id=" + id + ", name=" + name + ", description=" + description + ", genre=" + genre + ", type="
				+ type + ", price=" + price + ", purchaserId=" + purchaserId + "]";
	}


	
}
