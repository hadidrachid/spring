package fr.dawan.spring.controllers;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dawan.spring.beans.ExempleForm;

@Controller
@RequestMapping(value = "/exemples")
public class ExempleController {

	// Nouveauté par rapport à Serlvet / JSP de base :
	// On a plus les méthode doGet() doPost() : maintenant une méthode par requête
	// On retourne toujours un String qui equivaut à la page vers laquelle on veut rediriger.
	// Les objets request et response ne sont plus nécessaire et sont remplacés par Model model.
	@RequestMapping("/display") // Annotation de mapping la plus général (accepte tous les types si non précisé)
	//@RequestMapping(method=RequestMethod.GET, value="") 
	//@GetMapping(value="") //  c'est la même chose que ci-dessus mais n'accepte que des requêtes de type GET
	//@PostMapping(value="")
	//@PutMapping
	//@PatchMapping
	//@RequestMapping(value = {"", "/page","page*","view/*,**/msg"})
	public String display(Model model) {

		//model.addAttribute("monBean", new ExempleForm()); // plus besoin de mettre cette ligne si on a une méthode comme ligne 105 avec l'annotation @ModelAttribute
		
		return "exemple"; // mettre directement le nom exacte de la page, spring s'occupe /WEB-INF/views/exemple.jsp
	}

	@GetMapping("/exemple1")
	public String exemple1(Model model) {

		// Remplace le request.setAttribute("msg",msg) d'avant
		model.addAttribute("msg", "Mon Message");

		return "exemple";
	}

	// On peut appliquer un filtre qui prend le paramètre que si il respecte la pattern spécifié, le pattern est une expression régulière (REG EX)
	@GetMapping("/exemple2/{param1}/{param2:[0-9]}") // /exemple2/2/3
	public String exemple2_AvecParametres(@PathVariable("param1") String param1, @PathVariable("param2") String param2, Model model) {

		// @PathVariable("param1") String param1 == request.getParameter("param1")

		model.addAttribute("msg", param1 + " " + param2);

		return "exemple";

	}

	// Récupérer tous les paramètres directement dans une map
	@GetMapping("/exemple3/{param1}/cheminQuelconque/{param2}/{param3}")
	public String exemple3_AvecBcpParametres(@PathVariable Map<String, String> varsMap, Model model) {
		String param1 = varsMap.get("param1");
		
		model.addAttribute("msg", param1);
		
		return "exemple";
	}
	
	
	@PostMapping("/exemple4")
	public String exemple4_AvecFormulaire(@RequestParam("description") String description, Model model) {
		
		model.addAttribute("msg", description);
		
		return "exemple";
	}
	
	// Exemple annotation pour récupérer le header de la requête
	@GetMapping("/6")
	public String exemple5_Header(@RequestHeader HttpHeaders monHeader, Model model) {
		
		return "exemple";
	}
	
	@PostMapping("/exemple6")
	public String exemple4_AvecBean(@ModelAttribute("monBean")ExempleForm monBean, Model model) {
		
		// Avec spring on peut lier une classe Java à un formulaire côté JSP, on les appelle des Beans
		// Cette classe Java doit avoir l'annotation @Component
		// Le nom des attributs de cette classe doit être le même que les noms des champs côté JSP
		// Côté JSP on utilise les tag formulaire qui viennent de spring 
		//(<form:form modelAttribute="nomMaClasseJavaLiée">)
		// Pour récupérer le bean lié au formulaire il faut utiliser l'annotation 
		// @ModelAttribute("nomMaClasseJavaLiée") ExempleForm monBean
		// Il faut au préalable injecter dans le model le bean : 
		// model.addAttribute("nomMaClasseLiée", new ExempleForm() )		
		
		monBean.getDescription();
		
		return "exemple";
	}
	
	// Les méthodes annotées avec @ModelAttribute sont automatiquement appelées par Spring avant
	// qu'une requête arrive dans nos méthodes de la Servlet
	@ModelAttribute("monBean")
	public ExempleForm getExempleForm()
	{
		// Quand on veut utiliser un bean, en utilisant ce genre de méthode il va injecter dans le model
		// automatiquement le bean ( plus besoin de faire model.addAttribute("monBean", new ExempleForm());)
		return new ExempleForm();
	}
	
	
	
	
	
	
	
	
	
	
	
}
