package persistence.component;

import javax.persistence.EntityManager;

public class SingleResultReadableImp implements SingleResultReadable{
private EntityManager em;
	
	public SingleResultReadableImp(EntityManager em){
		this.em = em;
	}
	
	@Override
	public <T> T read(Class<T> type, Object id) {
		// TODO Auto-generated method stub
		return em.find(type, id);
	}

}
