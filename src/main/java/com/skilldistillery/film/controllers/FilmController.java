package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	
	@Autowired
	private FilmDAO filmDAO;
	
	public void setFilmDAO(FilmDAO filmDAO) {
		this.filmDAO = filmDAO;
	}
	
	@RequestMapping(path = {"home.do", "/"})
	public String goToHome() {
		return "/WEB-INF/home.jsp";
	}

	@RequestMapping(path = "GetFilmByID.do", params = "filmId", method = RequestMethod.GET)
	public ModelAndView findFilmById(@RequestParam("filmId") int filmId) {
		ModelAndView modelAndView = new ModelAndView();
		List<Film> film = filmDAO.findFilmById(filmId);
		modelAndView.addObject("film", film);
		modelAndView.setViewName("WEB-INF/result.jsp");
		return modelAndView;
	}
	
	@RequestMapping(path = "GetFilmByKeyword.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView findFilmByKeyword(@RequestParam("keyword") String keyword) {
		ModelAndView modelAndView = new ModelAndView();
		Film film = filmDAO.findFilmByKeyword(keyword);
		modelAndView.addObject("film", film);
		modelAndView.setViewName("WEB-INF/result.jsp");
		return modelAndView;
	}
}
