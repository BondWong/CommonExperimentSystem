package persistence.component;

import javax.persistence.EntityManager;

public class DeletableImp implements Deletable{
	private EntityManager em;
	
	public DeletableImp(EntityManager em){
		this.em = em;
	}
	
	@Override
	public <T> void delete(T t) {
		// TODO Auto-generated method stub
		em.remove(t);
	}

}
