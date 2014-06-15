package integratedTest;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

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
import persistence.transaction.daoTransaction.GetCreatedCoursesTransaction;
import persistence.transaction.daoTransaction.GetExperimentsTransaction;
import persistence.transaction.daoTransaction.GetOpenedCoursesTransaction;
import persistence.transaction.daoTransaction.GetSelectedCoursesTransaction;
import utils.UserType;

public class GetTest {
	private EntityManagerFactory emf;
	private EntityManager em;
	@Before
	public void setUp() throws ClassNotFoundException{
		emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
		
		DAO<User> udao = new DAO<User>(User.class, em);
		User s = new User();
		s.setId("2011052407");
		s.setUserType(UserType.STUDENT);
		User p = new User();
		p.setId("2011052406");
		p.setUserType(UserType.PROFESSOR);
		
		em.getTransaction().begin();
		udao.create(s);
		udao.create(p);
		em.getTransaction().commit();
		
		for(int i=0;i<10;i++){
			Course c = new Course();
			p.createCourse(c);
			for(int j=0;j<5;j++){
				Experiment e = new Experiment();
				p.createExperiment(c, e);
			}
			if(i%2==0)
				s.applyCourse(c);
		}
		
		em.getTransaction().begin();
		udao.update(p);
		udao.update(s);
		em.getTransaction().commit();
		
		
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGet() throws ClassNotFoundException{
		Transaction transaction = new GetSelectedCoursesTransaction();
		Set<Course> courses = (Set<Course>) transaction.execute("2011052407");
		assertEquals(5, courses.size());
		
		transaction = new GetExperimentsTransaction();
		Set<Experiment> experiments = (Set<Experiment>) transaction.execute(courses.iterator().next().getId());
		assertEquals(5, experiments.size());
		
		transaction = new GetOpenedCoursesTransaction();
		List<Course> openedCourses = (List<Course>) transaction.execute();
		assertEquals(0, openedCourses.size());
		
		transaction = new GetCreatedCoursesTransaction();
		courses = (Set<Course>) transaction.execute("2011052406");
		assertEquals(10, courses.size());
	}
}
