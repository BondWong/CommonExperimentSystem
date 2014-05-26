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
import persistence.transaction.daoTransaction.ApplyCourseTransaction;

public class ApplyCourseTransactionTest {
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
		
		Course c = new Course();
		
		em.getTransaction().begin();
		u.createCourse(c);
		udao.update(u);
		em.getTransaction().commit();
		
	}
	
	@Test
	public void testApplyCourseTransaction() throws ClassNotFoundException{
		Transaction transaction = new ApplyCourseTransaction();
		transaction.execute("2011052406", new Long(1));
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<User> udao = new DAO<User>(User.class, em);
		
		em.getTransaction().begin();
		User u = udao.singleResultQueryRead(User.class, "User.getById", "2011052406");
		em.getTransaction().commit();
		
		assertEquals(1, u.getAppliedCourses().size());
	}
	
}
