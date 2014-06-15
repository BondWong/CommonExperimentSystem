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
import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.DeleteClassificationTransaction;
import persistence.transaction.daoTransaction.DeleteUserTransaction;
import persistence.transaction.daoTransaction.GetClassificationsTransaction;
import persistence.transaction.daoTransaction.GetUsersTransaction;
import utils.UserType;

public class DeleteUserTest {
	@Before
	public void setUp() throws ClassNotFoundException{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<User> udao = new DAO<User>(User.class, em);
		User u = new User();
		u.setId("2011052407");
		u.setClassification("11se");
		u.setUserType(UserType.STUDENT);
		User u1 = new User();
		u1.setId("2011052406");
		u1.setClassification("11se");
		u1.setUserType(UserType.STUDENT);
		User u2 = new User();
		u2.setId("2011052405");
		u2.setClassification("11se");
		u2.setUserType(UserType.STUDENT);
		User u3 = new User();
		u3.setId("2011052404");
		u3.setClassification("10se");
		u3.setUserType(UserType.STUDENT);
		User u4 = new User();
		u4.setId("2011052403");
		u4.setClassification("10se");
		u4.setUserType(UserType.STUDENT);
		User u5 = new User();
		u5.setId("2011052402");
		u5.setClassification("10se");
		u5.setUserType(UserType.STUDENT);
		User u6 = new User();
		u6.setId("2011052401");
		u6.setClassification("dq");
		u6.setUserType(UserType.PROFESSOR);
		
		em.getTransaction().begin();
		udao.create(u);
		udao.create(u1);
		udao.create(u2);
		udao.create(u3);
		udao.create(u4);
		udao.create(u5);
		udao.create(u6);
		Course c1 = new Course();
		Course c2 = new Course();
		u6.createCourse(c1);
		u6.createCourse(c2);
		udao.update(u6);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testDeleteUser() throws ClassNotFoundException{
		Transaction transaction = new GetClassificationsTransaction();
		List<String> classifications = (List<String>) transaction.execute();
		assertEquals(3, classifications.size());
		
		transaction = new GetUsersTransaction();
		System.out.println(classifications.get(0));
		List<User> users = (List<User>) transaction.execute(classifications.get(0));
		assertEquals(1, users.size());
		
		transaction = new DeleteUserTransaction();
		transaction.execute(users.get(0).getId());
		transaction = new GetUsersTransaction();
		users = (List<User>) transaction.execute(classifications.get(0));
		assertEquals(0, users.size());
		
		transaction = new DeleteClassificationTransaction();
		transaction.execute(classifications.get(1));
		transaction = new GetUsersTransaction();
		users = (List<User>) transaction.execute(classifications.get(1));
		assertEquals(0, users.size());
	}
}
