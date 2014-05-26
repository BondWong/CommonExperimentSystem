package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import persistence.factory.EntityManagerFactoryCreator;
import persistence.transaction.Transaction;

public abstract class DAOTransaction implements Transaction{
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private void init(){
		emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
	}
	
	private void beginTransaction(){
		em.getTransaction().begin();
	}
	
	private void commitTransaction(){
		em.getTransaction().commit();
	}
	
	protected abstract Object process(EntityManager em, Object...params) throws ClassNotFoundException;
	
	public final Object execute(Object...params) throws ClassNotFoundException{
		init();
		beginTransaction();
		Object result = process(em, params);
		commitTransaction();
		return result;
		
	}
}
