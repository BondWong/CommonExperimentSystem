package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Experiment;

public class GetExperimentsTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<Experiment> edao = new DAO<Experiment>(Experiment.class, em);
		return  edao.multiResultQueryRead(Experiment.class, "Experiment.getByCourseId", params[0]);
	}

}
