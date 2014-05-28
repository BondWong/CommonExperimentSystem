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
		Course c = new Course();
		c.setClassTime((String)params[1]);
		c.setDescription((String)params[4]);
		c.setDuration((String)params[3]);
		c.setMajor((String)params[2]);
		c.setName((String)params[5]);
		c.setOpen(false);
		u.createCourse(c);
		udao.update(u);
		return null;
	}

}
