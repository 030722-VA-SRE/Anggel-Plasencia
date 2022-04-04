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
	private String comic;
	private String description;
	private String genre;
	private double price;
	@ManyToOne
	@JoinColumn(name = "purchaser_id")
	private User purchaserId;

	
	
	public Comics() {
		super();
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getComic() {
		return comic;
	}


	public void setComic(String comic) {
		this.comic = comic;
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


	public void setPrice(double price) {
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
		return Objects.hash(comic, description, genre, id, price, purchaserId);
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
		return Objects.equals(comic, other.comic) && Objects.equals(description, other.description)
				&& Objects.equals(genre, other.genre) && id == other.id
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(purchaserId, other.purchaserId);
	}


	@Override
	public String toString() {
		return "Comic [id=" + id + ", comic=" + comic + ", description=" + description + ", genre=" + genre + ", price="
				+ price + ", purchaserId=" + purchaserId + "]";
	}



	
}
