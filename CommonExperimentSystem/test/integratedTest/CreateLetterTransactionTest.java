package integratedTest;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Letter;
import model.User;
import model.UserType;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import persistence.factory.EntityManagerFactoryCreator;
import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.CreateLetterTransaction;

public class CreateLetterTransactionTest {
	@Before
	public void setUp() throws ClassNotFoundException{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<User> udao = new DAO<User>(User.class, em);
		User u = new User();
		u.setUserType(UserType.PROFESSOR);
		u.setId("2011052407");
		User u1 = new User();
		u1.setUserType(UserType.STUDENT);
		u1.setId("2011052406");
		
		em.getTransaction().begin();
		udao.create(u);
		udao.create(u1);
		em.getTransaction().commit();
		
	}
	
	@Test
	public void testCreateLetterTransaction() throws ClassNotFoundException{
		Letter letter = new Letter();
		Transaction transaction = new CreateLetterTransaction();
		transaction.execute("2011052406", "2011052407", letter);
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<Letter> ldao = new DAO<Letter>(Letter.class, em);
		
		em.getTransaction().begin();
		letter = ldao.singleResultQueryRead(Letter.class, "Letter.getUnread", "2011052407");
		em.getTransaction().commit();
		
		assertEquals("2011052407", letter.getReceiver().getId());
		assertEquals("2011052406", letter.getOwner().getId());
	}
}
