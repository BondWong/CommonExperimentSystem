package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Course;

public class UpdateCourseTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<Course> cdao = new DAO<Course>(Course.class, em);
		Course c = cdao.singleResultQueryRead(Course.class, "Course.getById", params[0]);
		c.setClassTime((String)params[3]);
		c.setDuration((String)params[2]);
		c.setName((String)params[1]);
		cdao.update(c);
		return null;
	}

}
