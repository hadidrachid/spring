package fr.dawan.spring.controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.dawan.spring.beans.UserForm;
import fr.dawan.spring.entities.User;
import fr.dawan.spring.service.UserService;

@Controller
@RequestMapping("/georgette")
public class AuthenticationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/display")
	public String display(Model model)
	{
		
		return "authenticate";
	}
	
	@PostMapping("/authentication")
	public String authentication(@ModelAttribute("userBean") UserForm userForm, Model model, HttpSession session) {
		String email =userForm.getEmail();
		String msg = "";
		User u = null;
		
		if(email!= null && !email.isEmpty()) {
			u = userService.readByEmail(userForm.getEmail());
		} else {
			msg = "Veuillez remplir le champ email";
			model.addAttribute("msg", msg);
			return "authenticate";	
		}
		
		if(u != null && u.getPassword() != null && u.getPassword().equals(userForm.getPassword())) {
			
			
			return "dashboard";
		} else {
			msg = "Couple login password incorrect";
			model.addAttribute("msg", msg);
			return "authenticate";	
		}
	}
	
		// Pour la validation avec les annotations sur les beans, il faut rajouter un objet de type
		// BindingResult br JUSTE APRES le bean donc ici userForm car c'est lui qui contient
		// le resultat de la validation
		// Rajouter aussi l'annotation @Valid sur le bean
		@PostMapping("/authentication/avec/validation")
		public String authentication(@Valid @ModelAttribute("userBean") UserForm userForm,  BindingResult br, Model model, Locale locale, HttpSession session) {
			String email =userForm.getEmail();
			String msg = "";
			User u = null;
			
			if(br.hasErrors()) {
				return "authenticate";
			}
			
			
			u = userService.readByEmail(userForm.getEmail());

			if(u != null && u.getPassword() != null && u.getPassword().equals(userForm.getPassword())) {
				//session.setAttribute("role", u.getRole());
				return "dashboard";
			} else {
				
				msg = messageSource.getMessage("authentication.errors", null, locale);
				//msg = "Couple login password incorrect";
				model.addAttribute("msg", msg);
				return "authenticate";	
			}
		}
	
	@GetMapping("/load")
	public String load(Model model) {
		
		for(int i = 0 ; i<50 ; i++) {
			User u = new User(0, 0, "nom"+i, "email"+i, "password"+i);
			userService.create(u);
		}
		
		
		String msg = "Utilisateur nom/password enregistré en BDD!";
		model.addAttribute("msg", msg);
		
		return "authenticate";	
		
	}
	
	@ModelAttribute("userBean")
	public UserForm getUserForm() {
		return new UserForm();
	}
 }
