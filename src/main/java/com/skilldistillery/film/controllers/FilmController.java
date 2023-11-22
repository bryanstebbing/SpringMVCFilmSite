package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.film.data.FilmDAO;

@Controller
public class FilmController {
	
	@Autowired
	private FilmDAO filmDAO;
	
	@RequestMapping(path = {"home.do", "/"})
	public String goToHome() {
		return "/WEB-INF/home.jsp";
	}

}