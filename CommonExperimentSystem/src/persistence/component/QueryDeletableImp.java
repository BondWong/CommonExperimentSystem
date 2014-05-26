package persistence.component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class QueryDeletableImp implements QueryDeletable {
	private EntityManager em;
	
	public QueryDeletableImp(EntityManager em){
		this.em = em;
	}
	@Override
	public <T> void delete(Class<T> type, String query, Object... params) {
		// TODO Auto-generated method stub
		TypedQuery<T> tq = em.createNamedQuery(query, type);
		for(int i=0;params!=null&&i<params.length;i++){
			tq.setParameter(i+1, params[i]);
		}
		tq.executeUpdate();
	}

}
