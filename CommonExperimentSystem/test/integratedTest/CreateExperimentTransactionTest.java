package integratedTest;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Course;
import model.Experiment;
import model.User;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import persistence.factory.EntityManagerFactoryCreator;
import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.CreateExperimentTransaction;
import utils.UserType;

public class CreateExperimentTransactionTest {
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
		
		Course c = new Course();
		
		em.getTransaction().begin();
		u.createCourse(c);
		udao.update(u);
		em.getTransaction().commit();
		
	}
	
	@Test
	public void testCreateExperimentTransaction() throws ClassNotFoundException{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<Course> cdao = new DAO<Course>(Course.class, em);
		
		Experiment e = new Experiment();
		
		Transaction transaction = new CreateExperimentTransaction();
		transaction.execute("2011052407", new Long(1), e);
		
		em.getTransaction().begin();
		Course c = cdao.singleResultQueryRead(Course.class, "Course.getById", new Long(1));
		em.getTransaction().commit();
		
		assertEquals(1, c.getExperiments().size());
	}
}
