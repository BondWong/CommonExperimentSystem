package persistence.component;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class MultiResultQueryReadableImp implements MultiResultQueryReadable {
	private EntityManager em;
	
	public MultiResultQueryReadableImp(EntityManager em){
		this.em = em;
	}
	@Override
	public <T> List<T> read(Class<T> type, String query, Object... params) {
		// TODO Auto-generated method stub
		TypedQuery<T> tq = em.createNamedQuery(query, type);
		for(int i=0;params!=null&&i<params.length;i++){
			tq.setParameter(i+1, params[i]);
		}
		return tq.getResultList();
	}

}
