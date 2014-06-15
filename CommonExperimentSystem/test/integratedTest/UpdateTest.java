package integratedTest;

import static org.junit.Assert.*;

import java.util.List;

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
import persistence.transaction.daoTransaction.UpdateCourseTransaction;
import persistence.transaction.daoTransaction.UpdateExperimentTransaction;
import utils.UserType;

public class UpdateTest {
	@Before
	public void setUp() throws ClassNotFoundException{
		EntityManagerFactory emf;
		EntityManager em;
		emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
		
		DAO<User> udao = new DAO<User>(User.class, em);
		User p = new User();
		p.setId("2011052406");
		p.setUserType(UserType.PROFESSOR);
		
		em.getTransaction().begin();
		udao.create(p);
		em.getTransaction().commit();
		
		Course c = new Course();
		p.createCourse(c);
		p.createExperiment(c, new Experiment());
		
		em.getTransaction().begin();
		udao.update(p);
		em.getTransaction().commit();
	}
	
	@Test
	public void testUpdate() throws ClassNotFoundException{
		Course c = new Course();
		c.setId(new Long(1));
		c.setName("hehe");
		c.setOpen(true);
		
		Transaction transaction = new UpdateCourseTransaction();
		transaction.execute(c);
		
		EntityManagerFactory emf;
		EntityManager em;
		emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
		
		DAO<Course> cdao = new DAO<Course>(Course.class, em);
		
		em.getTransaction().begin();
		List<Course> courses = cdao.multiResultQueryRead(Course.class, "Course.getAll"); 
		em.getTransaction().commit();
		em.clear();
		
		assertEquals("hehe", courses.iterator().next().getName());
		assertEquals(true, courses.iterator().next().isOpen());
		
		
		Experiment e = new Experiment();
		e.setId(new Long(1));
		e.setPurpose("haha");
		
		transaction = new UpdateExperimentTransaction();
		transaction.execute(e);
		
		DAO<Experiment> edao = new DAO<Experiment>(Experiment.class, em);
		em.getTransaction().begin();
		e = edao.singleResultQueryRead(Experiment.class, "Experiment.getById", new Long(1));
		em.getTransaction().commit();
		
		assertEquals(new Long(1), e.getId());
		assertEquals("haha", e.getPurpose());
	}
}
