package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Course;

public class GetOpenedCoursesTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<Course> cdao = new DAO<Course>(Course.class, em);
		return cdao.multiResultQueryRead(Course.class, "Course.getOpen");
	}

}
