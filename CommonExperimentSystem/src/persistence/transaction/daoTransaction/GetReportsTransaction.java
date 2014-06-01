package persistence.transaction.daoTransaction;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class GetReportsTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		TypedQuery<String> query = em.createNamedQuery("Experiment.getReports", String.class);
		query.setParameter(1, params[0]);
		List<String> reports = (List<String>)query.getResultList();
		
		return reports;
	}

}
