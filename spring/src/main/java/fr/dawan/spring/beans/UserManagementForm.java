package fr.dawan.spring.beans;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

@Component
public class UserManagementForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String version;
	
	@NotEmpty(message = "{user.management.nom.not.empty}")
	private String nom;
	
	@NotEmpty(message = "{user.management.email.not.empty}")
	private String email;
	
	@NotEmpty(message = "{user.management.password.not.empty}")
	private String password;

	public UserManagementForm() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
