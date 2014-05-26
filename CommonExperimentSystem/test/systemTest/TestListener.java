package systemTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import persistence.DAO;
import persistence.factory.EntityManagerFactoryCreator;
import model.Course;
import model.Experiment;
import model.User;
import model.UserType;

/**
 * Application Lifecycle Listener implementation class TestListener
 *
 */
@WebListener
public class TestListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public TestListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	User p = new User();
    	p.setUserType(UserType.PROFESSOR);
    	p.setClassification("电气信息学院");
    	p.setId("2011052406");
    	p.setName("周密");
    	p.setPassword("1901103390");
    	
    	User s = new User();
    	s.setUserType(UserType.STUDENT);
    	s.setClassification("11软工");
    	s.setId("2011052407");
    	s.setName("黃俊邦");
    	s.setPassword("1901103390");
    	
    	User a = new User();
    	a.setUserType(UserType.ADMIN);
    	a.setClassification("电信信息学院教务处");
    	a.setId("2011052405");
    	a.setName("煞笔");
    	a.setPassword("1901103390");
    	
    	EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
    	EntityManager em = emf.createEntityManager();
    	try {
			DAO<User> udao = new DAO<User>(User.class, em);
			em.getTransaction().begin();
			udao.create(p);
			udao.create(s);
			udao.create(a);
			Course c1 = new Course();
			c1.setName("设计模式");
			c1.setClassTime("周五7、8、9节");
			c1.setDescription("很容易挂科");
			c1.setDuration("16周");
			c1.setOpen(true);
			p.createCourse(c1);
			Course c2 = new Course();
			c2.setName("人体学");
			c2.setClassTime("周四7、8、9节");
			c2.setDescription("100％易挂科");
			c2.setDuration("16周");
			c2.setOpen(false);
			p.createCourse(c2);
			udao.update(p);
			em.getTransaction().commit();
			
			DAO<Course> cdao = new DAO<Course>(Course.class, em);
			em.getTransaction().begin();
			Experiment e1 = new Experiment();
			e1.setPurpose("让你挂科");
			e1.setDescription("呵呵");
			e1.setDuration("第一周到第二周");
			e1.setName("使用java编程程序");
			p.createExperiment(c1, e1);
			p = udao.singleResultQueryRead(User.class, "User.getById", "2011052406");
			Course c = cdao.singleResultQueryRead(Course.class, "Course.getById", new Long(1));
			p.createExperiment(c, e1);
			cdao.update(c);
			em.getTransaction().commit();
			
			em.getTransaction().begin();
			s = udao.singleResultQueryRead(User.class, "User.getById", "2011052407");
			c = cdao.singleResultQueryRead(Course.class, "Course.getById", new Long(1));
			s.applyCourse(c);
			udao.update(s);
			em.getTransaction().commit();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }
	
}
