package com.revature.eval.java.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.daos.ComicDao;
import com.revature.daos.ComicPostgres;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.Comics;
import com.revature.services.ComicServices;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class TestService {
	
	private static ComicDao testDao;
	private static ComicServices cs = new ComicServices();
	private static List<Comics> comicList;
	private static Comics c;
	
	
	@BeforeAll
	public static void setUp() {
		
		testDao = mock(ComicDao.class);
		
		comicList = new ArrayList<Comics>(); 
		comicList.add(new Comics(1, "Batman", "fighting crime in a dark city", "action", 705));
		comicList.add(new Comics(2, "Berserk", "former mercenary", "darkfantasy", 24));
		comicList.add(new Comics(3, "Vagabond", "wandering fighter", "action", 36));
		comicList.add(new Comics(4, "Spiderman", "witty superhero", "comedy", 79));
		comicList.add(new Comics(5, "MadeinAbyss", "the abyss", "exploration", 94));
		
		cs = new ComicServices(testDao);
		
	}

	
	@Test
	void getAllComicsTest() {
		when(testDao.getAllComics()).thenReturn(comicList);
		assertEquals(comicList,cs.getAllComics());
		
	}

	
	@Test
	void addComicTest() throws ItemNotFoundException {
		when(testDao.addComic(c)).thenReturn(1);
		assertEquals(true,cs.addComic(c));
			
	}
	
	
	@Test 
	void getByGenreTest() throws ItemNotFoundException {
		when(testDao.getByGenre("action")).thenReturn(comicList);
		assertDoesNotThrow(() -> {
			cs.getByGenre("action");
		});
		
		assertNotEquals(comicList,cs.getByGenre("comedy"));
		assertEquals(comicList,cs.getByGenre("action"));
	}
	
	@Test
	void getByNameTest() throws ItemNotFoundException {
		when(testDao.getByComicName("Berserk")).thenReturn(comicList);
		assertDoesNotThrow(() -> {
			cs.getByComicName("Berserk");
		});
		assertNotEquals(comicList,cs.getByComicName("Vagabond"));
		assertEquals(comicList,cs.getByComicName("Berserk"));
	}
	
	
	
	
@Test
void GetByIdTest() throws ItemNotFoundException {
	when(testDao.getById(1)).thenReturn(c);
	assertEquals(c,cs.getById(1));
}

	
	
}// end of TestService
