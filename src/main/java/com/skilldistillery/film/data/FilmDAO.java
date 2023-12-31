package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	public List<Film> findFilmById(int filmId);
	public List<Film> findFilmByKeyword(String keyword);

}
