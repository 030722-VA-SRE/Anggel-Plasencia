package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.models.Comics;
import com.revature.repositories.ComicRepository;
import com.revature.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class ComicServiceTest {

	static ComicRepository cr;
	static UserRepository ur;
	static ComicService cs;
	static List<Comics> c = new ArrayList<>();
	static Comics c1;
	static Comics c2;

	@BeforeAll
	public static void setup() {
		cr = mock(ComicRepository.class);
		ur = mock(UserRepository.class);
		cs = new ComicService(cr, ur);
		c1 = new Comics(2, "Berserk", "former mercenary", "darkfantasy", "Manga", 24, null);
		c2 = new Comics(3, "Vagabond", "wandering fighter", "action", "Manga", 36, null);
		c.add(c1);
		c.add(c2);

	}

	@Test
	void getAllComicsTest() {
		when(cr.findAll()).thenReturn(c);
		assertEquals(c, cs.getAllComics());
	}

	@Test
	void getComicByIdTest() {
		Mockito.when(cr.getComicById(2)).thenReturn(c2);
		assertEquals(cr.getComicById(2), c2);

	}

	@Test
	void getComicByNameTest() {
		when(cr.getComicByName("Berserk")).thenReturn(c);
		assertEquals(c, cs.getComicByName("Berserk"));
	}

	@Test
	void createComicTest() {
		when(cr.save(c1)).thenReturn(c1);
		assertEquals(c1, cs.createComic(c1));
	}

	@Test
	void updateComicTest() {
		when(cr.findById(2)).thenReturn(Optional.of(c1));
		when(cr.save(c1)).thenReturn(c1);
		assertEquals(c1, cs.updateComic(2, c1));
	}
	
	@Test 
	void deleteComicTest() {
		when(cr.getById(1)).thenReturn(c1); 
		assertEquals(true, cs.deleteComic(1)); 
	}
	
	
	

}
