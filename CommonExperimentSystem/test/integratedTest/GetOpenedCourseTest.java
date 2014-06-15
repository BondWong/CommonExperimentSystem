package integratedTest;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Course;
import model.User;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import persistence.factory.EntityManagerFactoryCreator;
import utils.UserType;

public class GetOpenedCourseTest {
	EntityManagerFactory emf;
	EntityManager em;
	@Before
	public void setUp() throws ClassNotFoundException{
		emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
		DAO<User> udao = new DAO<User>(User.class, em);
		
		User u = new User();
		u.setId("2011052407");
		u.setUserType(UserType.STUDENT);
		User p = new User();
		p.setId("2011052406");
		p.setUserType(UserType.PROFESSOR);
		
		em.getTransaction().begin();
		udao.create(u);
		udao.create(p);
		for(int i=0;i<5;i++){
			Course c = new Course();
			c.setOpen(true);
			p.createCourse(c);
		}
		udao.update(p);
		em.getTransaction().commit();
		
	}
	
	@Test
	public void testGetOpenedCourse() throws ClassNotFoundException {
		DAO<Course> cdao = new DAO<Course>(Course.class, em);
		
		em.getTransaction().begin();
		List<Course> courses = cdao.multiResultQueryRead(Course.class, "Course.getOpen", "2011052407");
		em.getTransaction().commit();
		assertEquals(5, courses.size());
	}
}
