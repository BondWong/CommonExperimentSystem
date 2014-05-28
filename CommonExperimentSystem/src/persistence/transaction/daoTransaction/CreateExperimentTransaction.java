package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Course;
import model.Experiment;
import model.User;

public class CreateExperimentTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Course> cdao = new DAO<Course>(Course.class, em);
		User u = udao.singleResultQueryRead(User.class, "User.getById", params[0]);
		Course c = cdao.singleResultQueryRead(Course.class, "Course.getById", params[1]);
		Experiment e = new Experiment();
		e.setDemand((String) params[6]);
		e.setDescription((String) params[7]);
		e.setDuration((String) params[4]);
		e.setName((String) params[2]);
		e.setPurpose((String) params[5]);
		e.setType((String) params[3]);
		u.createExperiment(c, e);
		cdao.update(c);
		return null;
	}

}
