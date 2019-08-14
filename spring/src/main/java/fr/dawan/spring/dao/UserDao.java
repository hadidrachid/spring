package fr.dawan.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.dawan.spring.entities.User;

@Repository // Permet à Spring de savoir que cette classe est un composant de type DAO
public class UserDao {

	@PersistenceContext // Permet à Spring d'injecter l'entity manager avec les infos de connexion de la base de données
	private EntityManager em;
	
	public void create(User user) {
		try {
			em.persist(user);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Transactional à rajouter si pas de couche service
	@SuppressWarnings("unchecked")
	public List<User> readAll() {
		try  {
			return em.createQuery("From User").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public User readById(long id) {
		try {
			//User u = (User) em.createQuery("From User WHERE id= :id").setParameter("id", id).getSingleResult();
			return (User) em.createQuery("From User WHERE id= :id").setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public User readByEmail(String email) {
		try {
			return (User) em.createQuery("From User WHERE email= :email").setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void delete(User user) {
		try {
			em.remove(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(User user) {
		try {
			em.merge(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public long count() {
		try {
			return (long) em.createQuery("Select count(x) FROM User x").getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<User> readPartial(int start, int nbElements) {
		try  {
			return em.createQuery("From User").setFirstResult(start).setMaxResults(nbElements).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
