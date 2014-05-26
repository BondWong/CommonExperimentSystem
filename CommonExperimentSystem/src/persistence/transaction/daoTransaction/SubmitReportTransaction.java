package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Experiment;
import model.User;

public class SubmitReportTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<Experiment> edao = new DAO<Experiment>(Experiment.class, em);
		DAO<User> udao = new DAO<User>(User.class, em);
		User u = udao.singleResultQueryRead(User.class, "User.getById", params[0]);
		Experiment e = edao.singleResultQueryRead(Experiment.class, "Experiment.getById", params[1]);
		u.submitReport(e, (String) params[2]);
		edao.update(e);
		return null;
	}

}
