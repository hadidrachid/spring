package fr.dawan.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.dawan.spring.entities.Product;

@Repository // Permet à Spring de savoir que cette classe est un composant de type DAO
public class ProductDao {

	@PersistenceContext // Permet à Spring d'injecter l'entity manager avec les infos de connexion de la base de données
	private EntityManager em;
	
	public void create(Product product) {
		try {
			em.persist(product);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> readAll() {
		try  {
			return em.createQuery("From Product").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Product readById(long id) {
		try {
			return (Product) em.createQuery("From Product WHERE id= :id").setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void delete(Product product) {
		try {
			em.remove(product);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Product product) {
		try {
			em.merge(product);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public long count() {
		try {
			return (long) em.createQuery("Select count(x) FROM Product x").getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> readPartial(int start, int nbElements) {
		try  {
			return em.createQuery("From Product").setFirstResult(start).setMaxResults(nbElements).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
