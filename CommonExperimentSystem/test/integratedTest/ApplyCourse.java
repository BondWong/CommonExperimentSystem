package integratedTest;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Course;
import model.Experiment;
import model.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import persistence.factory.EntityManagerFactoryCreator;
import utils.UserType;

public class ApplyCourse {
	private EntityManagerFactory emf;
	private EntityManager em;
	@Before
	public void setUp() throws ClassNotFoundException{
		emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
		DAO<User> udao = new DAO<User>(User.class ,em);
		
		User u1 = new User();
		u1.setId("2011052407");
		u1.setUserType(UserType.STUDENT);
		User u2 = new User();
		u2.setUserType(UserType.PROFESSOR);
		u2.setId("2011052406");
		
		em.getTransaction().begin();
		udao.create(u1);
		udao.create(u2);
		em.getTransaction().commit();
	}
	
	@Test
	public void testApplyCourse() throws ClassNotFoundException{
		Course c = new Course();
		User u = null;
		DAO<User> udao = new DAO<User>(User.class ,em);
		em.getTransaction().begin();
		u = udao.singleResultQueryRead(User.class, "User.getByUserType", UserType.PROFESSOR);
		u.createCourse(c);
		udao.update(u);
		em.getTransaction().commit();
		
		DAO<Course> cdao = new DAO<Course>(Course.class, em);
		em.getTransaction().begin();
		c = cdao.multiResultQueryRead(Course.class, "Course.getAll").get(0);
		em.getTransaction().commit();
		
		Assert.assertNotNull(c);
		
		em.getTransaction().begin();
		u = udao.singleResultQueryRead(User.class, "User.getByUserType", UserType.STUDENT);
		c = cdao.multiResultQueryRead(Course.class, "Course.getAll").get(0);
		u.applyCourse(c);
		udao.update(u);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		u = udao.singleResultQueryRead(User.class, "User.getByUserType", UserType.STUDENT);
		em.getTransaction().commit();
		
		assertEquals(1, u.getAppliedCourses().size());
		
		em.getTransaction().begin();
		u = udao.singleResultQueryRead(User.class, "User.getByUserType", UserType.PROFESSOR);
		Experiment experiment = new Experiment();
		c = cdao.multiResultQueryRead(Course.class, "Course.getAll").get(0);
		u.createExperiment(c, experiment);
		cdao.update(c);
		em.getTransaction().commit();
		
		DAO<Experiment> edao = new DAO<Experiment>(Experiment.class, em);
		em.getTransaction().begin();
		experiment = edao.multiResultQueryRead(Experiment.class, "Experiment.getAll").get(0);
		em.getTransaction().commit();
		
		assertEquals(new Long(1), experiment.getId());
		
		em.getTransaction().begin();
		u = udao.singleResultQueryRead(User.class, "User.getByUserType", UserType.STUDENT);
		u.submitReport(experiment, "hehe");
		edao.update(experiment);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		experiment = edao.multiResultQueryRead(Experiment.class, "Experiment.getAll").get(0);
		em.getTransaction().commit();
		
		assertEquals("hehe", experiment.getReportLinks().iterator().next());
	}
}
