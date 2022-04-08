package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Comics;

@Repository
public interface ComicRepository extends JpaRepository<Comics, Integer> {

	
	
	public Comics getComicById(int id);
	public List<Comics> getComicByName(String name);
	public List<Comics> getComicByGenre(String genre);
	public List<Comics> getComicByType(String type);
	
	
	

}
