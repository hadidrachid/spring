package fr.dawan.spring.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.dawan.spring.beans.UserManagementForm;
import fr.dawan.spring.entities.User;
import fr.dawan.spring.service.UserService;

@Controller
@RequestMapping("userManagement")
public class UserManagementController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/display")
	public String display(Model model) {
		List<User> users = userService.readAll();
		model.addAttribute("usersList",users);
		
		return "users";
	}
	
	@GetMapping("/delete/{ligneId}")
	public String deleteUser(@PathVariable("ligneId") String id, Model model) {
		
		userService.delete(Long.parseLong(id));
		return display(model);
	}
	
	@PostMapping("/create")
	public String createUser(@Valid @ModelAttribute("userManagementForm")UserManagementForm userForm, BindingResult br, Model model) {
		
		// Vérification du résultat de la validation
		if(br.hasErrors()) {
			return display(model);
		}
		
		// Cas de création car l'id n'est pas renseigné
		if(userForm.getId() == null) {
			// Il faut maintenant convertir le user du bean (UserManagementForm) en user pour les DAO (User)
			User u = new User(0, 0, userForm.getNom(), userForm.getEmail(), userForm.getPassword());
		
			//  ci-dessous équivalent à la ligne juste au dessus
//			User u = new User();
//			u.setEmail(userForm.getEmail());
//			u.setId(0);
//			u.setNom(userForm.getNom());
//			u.setPassword(userForm.getPassword());
//			u.setVersion(0);
		
			userService.create(u);
		} 
		// Cas de la modification car l'id sera tjrs renseigné
		else {
			User u = new User(Long.parseLong(userForm.getId()), Integer.parseInt(userForm.getVersion()), userForm.getNom(), userForm.getEmail(), userForm.getPassword());
		
			userService.update(u);
		}
		
		

		
		
		
		return display(model);
	}
	
	@GetMapping("/modify/{ligneId}")
	public String modifyUser(@PathVariable("ligneId") String id, Model model) {
		
		User u = userService.readById(Long.parseLong(id));
		model.addAttribute("userToModify",u);
		return display(model);
	}
	// Injecte automatiquement le bean userManagementForm dans le model à chaque
	// nouvelle requête
	@ModelAttribute("userManagementForm")
	public UserManagementForm getUserManagementForm() {
		return new UserManagementForm();
	}
	
	@RequestMapping("/exporter")
	public void generateCsv(HttpServletResponse response) {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment;filename=utilisateurs.csv");
		List<User> users = userService.readAll();

		try {
			ServletOutputStream out = response.getOutputStream();
			out.write(("id;version;nom;email;password\n").getBytes()); //ligne d'entetes

			for (User u : users) {
				out.write((u.getId() + ";" + u.getVersion() + ";" + u.getNom() + ";" + u.getEmail() + ";" + u.getPassword() + "\n").getBytes());
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/importer")
	public String importerCsv(@RequestParam("user-file") MultipartFile multipartFile,@ModelAttribute UserManagementForm usersManagementBean, Model model) throws IOException {
		
		try(BufferedReader r = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))){
			String line;
			line = r.readLine();
			while((line = r.readLine()) != null) {
				if(!line.trim().equals("")) {
					String[] utilisateur = line.split(";");
					if(utilisateur.length == 5) {
						User user = new User(0, 0, utilisateur[2], utilisateur[3], utilisateur[4]);
						userService.create(user);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return display(model);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
