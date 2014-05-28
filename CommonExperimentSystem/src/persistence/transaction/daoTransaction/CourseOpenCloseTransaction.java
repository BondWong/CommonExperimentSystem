package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import model.Course;
import persistence.DAO;

public class CourseOpenCloseTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Long courseId = (Long)params[0];
		boolean open = (boolean)params[1];
		DAO<Course> cdao = new DAO<Course>(Course.class, em);
		Course c = cdao.singleResultRead(Course.class, courseId);
		c.setOpen(open);
		cdao.update(c);
		return null;
	}

}
