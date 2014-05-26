package persistence.component;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class SingleResultQueryReadableImp implements SingleResultQueryReadable {
	private EntityManager em;
	
	public SingleResultQueryReadableImp(EntityManager em){
		this.em = em;
	}
	
	@Override
	public <T> T read(Class<T> type, String query, Object... params) {
		// TODO Auto-generated method stub
		TypedQuery<T> tq = em.createNamedQuery(query, type);
		for(int i=0;params!=null&&i<params.length;i++){
			tq.setParameter(i+1, params[i]);
		}
		List<T> results = tq.getResultList();
		if(results.size()==0)
			return null;
		else
			return results.iterator().next();
	}

}
