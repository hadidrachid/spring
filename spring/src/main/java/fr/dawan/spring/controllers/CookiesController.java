package fr.dawan.spring.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookiesController {

	
	@RequestMapping("/cookies")
	public String addCookies(@CookieValue(value = "monPanier", defaultValue = "vide") String c, HttpServletResponse response) {
		
		
		if(c.equals("vide")) {
			Cookie cookie = new Cookie("monPanier", "test");
			cookie.setMaxAge(60*60*24*365*10);//en seconde
			response.addCookie(cookie);
		} else {
			// recupérer la valeur de l'ancien cookie
			// ajouter des infos 
		}
		
		
		return "home";
	}
}
