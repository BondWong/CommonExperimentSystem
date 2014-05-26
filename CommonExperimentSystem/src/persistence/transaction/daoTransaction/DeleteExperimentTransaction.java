package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Course;
import model.Experiment;

public class DeleteExperimentTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<Experiment> edao = new DAO<Experiment>(Experiment.class, em);
		Experiment e = edao.singleResultQueryRead(Experiment.class, "Experiment.getById", params[0]);
		DAO<Course> cdao = new DAO<Course>(Course.class, em);
		Course course = cdao.singleResultQueryRead(Course.class, "Course.getById", params[1]);
		course.removeExperiment(e);
		cdao.update(course);
		return null;
	}

}
