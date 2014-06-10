package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class GetAllReportLinksTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		TypedQuery<String> query = em.createNamedQuery("Experiment.getAllReportLinks", String.class);
		return query.getResultList();
	}

}
