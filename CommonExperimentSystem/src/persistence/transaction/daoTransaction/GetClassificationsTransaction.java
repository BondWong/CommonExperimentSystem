package persistence.transaction.daoTransaction;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class GetClassificationsTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		TypedQuery<String> query = em.createNamedQuery("User.getAllClassifications", String.class);
		List<String> results = query.getResultList();
		return results;
	}

}
