package com.skilldistillery.film.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.film.entities.Film;

class FilmDaoJdbcImplTest {
	private FilmDaoJdbcImpl dao;
	
	@BeforeEach
	void setUp() throws Exception {
	 	dao = new FilmDaoJdbcImpl();

	}

	@AfterEach
	void tearDown() throws Exception {
		dao = null;
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	 @Test
	 void test_findFilmById_returns_film() {
	 	List<Film> film = dao.findFilmById(1);
	 	assertNotNull(film);
//	 	assertEquals("ACADEMY DINOSAUR", film.getTitle());
	 }
	 
	 @Test
	 void test_findFilmByKeyword_returns_null_for_invalid_id() {
	 	Film filmSetNumber2 = dao.findFilmByKeyword("car");
	 	assertNull(filmSetNumber2);
	 }

}
