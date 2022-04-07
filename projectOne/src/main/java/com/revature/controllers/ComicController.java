package com.revature.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.UserDTO;
import com.revature.models.Comics;
import com.revature.models.User;
import com.revature.services.ComicService;

@RestController
@RequestMapping("/comics")
public class ComicController {

	private ComicService cs;

	
	public ComicController(ComicService cs) {
		super();
		this.cs = cs;
	} 
	
	@GetMapping
	public ResponseEntity<List<Comics>> getAllComics(String username) {

		return new ResponseEntity<>(cs.getAllComics(), HttpStatus.OK);
	}
	
	

//	@PostMapping
//	public ResponseEntity<String> createComic(@RequestBody Comics comic) {
//
//		
//		return new ResponseEntity<>("User was created!", HttpStatus.CREATED);
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<UserDTO> getComicById(@PathVariable("id") int id) {
//
//		return new ResponseEntity<>(cs, HttpStatus.OK);
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<String> updateComic(@RequestBody Comics comic, @PathVariable("id") int id) {
//
//		return new ResponseEntity<>("Comic of id: " + id + " was updated!", HttpStatus.OK);
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<String> deleteComic(@PathVariable("id") int id) {
//
//		return new ResponseEntity<>("Comic of " + id + " was deleted", HttpStatus.OK);
//
//	}
	
	
	
	
	
	
	
	
	
}//end of CC
