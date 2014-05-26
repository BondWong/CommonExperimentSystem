package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Course;
import model.User;

public class CreateCourseTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<User> udao = new DAO<User>(User.class, em);
		User u = udao.singleResultQueryRead(User.class, "User.getById", params[0]);
		u.createCourse((Course)params[1]);
		udao.update(u);
		return null;
	}

}
