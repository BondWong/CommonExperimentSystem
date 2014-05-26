package integratedTest;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Course;
import model.User;
import model.UserType;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import persistence.factory.EntityManagerFactoryCreator;
import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.CreateCourseTransaction;

public class CreateCourseTransactionTest {
	@Before
	public void setUp() throws ClassNotFoundException{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<User> udao = new DAO<User>(User.class, em);
		User u = new User();
		u.setUserType(UserType.PROFESSOR);
		u.setId("2011052407");
		
		em.getTransaction().begin();
		udao.create(u);
		em.getTransaction().commit();
		
	}
	
	@Test
	public void testCreateCourse() throws ClassNotFoundException{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<User> udao = new DAO<User>(User.class, em);
		
		Course c = new Course();
		
		Transaction transaction = new CreateCourseTransaction();
		transaction.execute("2011052407", c);
		
		em.getTransaction().begin();
		User u = udao.singleResultQueryRead(User.class, "User.getById", "2011052407");
		em.getTransaction().commit();
		
		assertEquals(1, u.getCreatedCourses().size());
	}
}
