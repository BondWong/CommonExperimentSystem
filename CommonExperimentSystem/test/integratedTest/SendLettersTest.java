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

public class SendLettersTest {
	private EntityManagerFactory emf;
	private EntityManager em;
	@Before
	public void setUp() throws ClassNotFoundException{
		emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
		DAO<User> udao = new DAO<User>(User.class, em);
		
		User u = new User();
		u.setUserType(UserType.STUDENT);
		u.setId("2011052407");
		User u1 = new User();
		u1.setUserType(UserType.PROFESSOR);
		u1.setId("2011052406");
		em.getTransaction().begin();
		udao.create(u);
		udao.create(u1);
		em.getTransaction().commit();
		
	}
	
	@Test
	public void testSendLetters() throws ClassNotFoundException{
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Letter> ldao = new DAO<Letter>(Letter.class, em);
		
		Letter stp = new Letter();
		stp.setContent("hehe");
		stp.setRead(false);
		
		em.getTransaction().begin();
		User s = udao.singleResultQueryRead(User.class, "User.getByUserType", UserType.STUDENT);
		User p = udao.singleResultQueryRead(User.class, "User.getByUserType", UserType.PROFESSOR);
		s.sendLetter(p, stp);
		ldao.update(stp);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		stp = ldao.multiResultQueryRead(Letter.class, "Letter.getAll").get(0);
		em.getTransaction().commit();
		
		assertEquals("hehe", stp.getContent());
		
		em.getTransaction().begin();
		stp = ldao.multiResultQueryRead(Letter.class, "Letter.getUnread", p.getId()).get(0);
		em.getTransaction().commit();
		
		assertEquals(s, stp.getOwner());
	}
}
