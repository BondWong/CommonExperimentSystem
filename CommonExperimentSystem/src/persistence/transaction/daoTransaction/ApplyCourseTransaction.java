package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Course;
import model.User;

public class ApplyCourseTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Course> cdao = new DAO<Course>(Course.class, em);
		User u = null;
		Course c = null;
		u = udao.singleResultQueryRead(User.class, "User.getById", params[0]);
		c = cdao.singleResultQueryRead(Course.class, "Course.getById", params[1]);
		u.applyCourse(c);
		udao.update(u);
		return null;
	}

}
