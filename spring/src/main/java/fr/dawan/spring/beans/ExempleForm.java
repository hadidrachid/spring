package fr.dawan.spring.beans;

import java.io.Serializable;

import org.springframework.stereotype.Component;

// Bean qui respecte les conventions JavaBean
@Component
public class ExempleForm implements Serializable {

	String description;
	
	String prix;

	public ExempleForm() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}
	
	
	
}
