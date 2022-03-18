package com.revature.models;

import java.util.Objects;

public class Comics {

	// My comic shop properties
	private int id;
	private String comic;
	private String description;
	private String genre;
	private double price;
//	private boolean inStock;
//	public static int counter;

	public Comics() {
		super();
	}

//	public Comics(int id, String comicomic, String description, String genre, boolean inStock) {
//		this();
//
//	}

//	public boolean isInStock() {
//		return inStock;
//	}
//
//	public void setInStock(boolean inStock) {
//		this.inStock = inStock;
//	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(comic, description, genre, id, price);
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
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}


}
