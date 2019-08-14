package fr.dawan.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.spring.dao.UserDao;
import fr.dawan.spring.entities.User;

/*
 * Nouvelle couche Service qui permet :
 * 		- de protéger la couche DAO et donc la BDD des attaques externes
 * 		- faire le lien entre la couche métier et la couche DAO
 * 				=> augmente la modularité du projet
 * 		- facilite aussi la maintenance et l'évolution du projet
 * 		Si dans une servlet on appelle 50 fois une méthode dao, si on modifie la méthode dao
 * 	il faudra changer les appels 50 fois dans la servlet. Pour faciliter la maintenance on utilise maintenant
 * une couche service qui s'occupe des appels dao, et donc si les dao sont modifiés il y aura juste à changer 
 * un appel dans la couche service
 * 
 * 
 * C'est dans cette couche qu'on va appliquer des traitements spécifiques à l'objet user propre à notre projet
 * et qu'on va gérer les transactions vers la BDD.
 * 
 */
@Service
public class UserService {

	@Autowired // avec cette annotation plus besoin d'initialiser l'objet (plus besoin de userDao= new UserDao();)
	private UserDao userDao;
	
	// Annotation TRES IMPORTANTE
	@Transactional //  Annotation qui permet de gérer les flux de connexions (ouverture, fermeture et rollback en cas d'erreur sur la requête)
	public void create(User user) {
		// Si modification à faire sur le user les faire ici SURTOUT avant l'appel du dao
		userDao.create(user);
	}
	
	@Transactional
	public List<User> readAll() {
		return userDao.readAll();
	}

	@Transactional
	public User readById(long id) {
		return userDao.readById(id);
	}
	
	@Transactional
	public User readByEmail(String email) {
		return userDao.readByEmail(email);
	}
	
	@Transactional
	public void delete(long id) {
		User u = readById(id);
		userDao.delete(u);
	}
	
	@Transactional
	public void update(User utilisateur) {
		userDao.update(utilisateur);
	}
	
	@Transactional
	public List<User> readPartial(int start, int nbElts){
		return userDao.readPartial(start, nbElts);
	}
	
	@Transactional
	public long count() {
		return userDao.count();
	}
	
	
}
