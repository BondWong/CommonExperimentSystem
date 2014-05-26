package integratedTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.User;
import model.UserType;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import persistence.factory.EntityManagerFactoryCreator;

public class CreateUserTest {
	private EntityManagerFactory emf;
	private EntityManager em;
	@Before
	public void setUp(){
		emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
	}
	
	@Test
	public void testCreateUser() throws ClassNotFoundException {
		User user = new User();
		User user1 = new User();
		User user2 = new User();
		user.setUserType(UserType.STUDENT);
		user.setId("2011052407");
		user1.setUserType(UserType.PROFESSOR);
		user1.setId("2011052406");
		user2.setUserType(UserType.ADMIN);
		user2.setId("2011052405");
		
		DAO<User> udao = new DAO<User>(User.class, em);
		em.getTransaction().begin();
		udao.create(user);
		udao.create(user1);
		udao.create(user2);
		em.getTransaction().commit();
		
		User u = null;
		List<User> users = new ArrayList<User>();
		em.getTransaction().begin();
		u = udao.singleResultQueryRead(User.class, "User.getById", "2011052407");
		users = udao.multiResultQueryRead(User.class, "User.getByUserType", UserType.STUDENT);
		em.getTransaction().commit();
		
		assertEquals(1, users.size());
		assertEquals("2011052407", u.getId());
	}
}
