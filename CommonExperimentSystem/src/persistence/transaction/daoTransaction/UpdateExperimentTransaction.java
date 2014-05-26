package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Experiment;

public class UpdateExperimentTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<Experiment> edao = new DAO<Experiment>(Experiment.class, em);
		Experiment exp = edao.singleResultQueryRead(Experiment.class, "Experiment.getById", params[0]);
		exp.setName((String)params[1]);
		exp.setType((String)params[2]);
		exp.setDemand((String)params[5]);
		exp.setDescription((String)params[6]);
		exp.setDuration((String)params[3]);
		exp.setPurpose((String)params[4]);
		edao.update(exp);
		return null;
	}

}
