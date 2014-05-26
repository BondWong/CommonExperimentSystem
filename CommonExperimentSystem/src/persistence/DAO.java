package persistence;

import java.util.List;

import javax.persistence.EntityManager;

import persistence.component.Creatable;
import persistence.component.Deletable;
import persistence.component.MultiResultQueryReadable;
import persistence.component.QueryDeletable;
import persistence.component.SingleResultQueryReadable;
import persistence.component.SingleResultReadable;
import persistence.factory.ComponentFactory;

public class DAO<T> {
	private EntityManager em;
	
	private Creatable creatable;
	private SingleResultReadable srReadable;
	private SingleResultQueryReadable srqReadable;
	private MultiResultQueryReadable mrqReadable;
	private Deletable deletable;
	private QueryDeletable qDeletable;

	public DAO (Class<T> type, EntityManager em) throws ClassNotFoundException{
		this.em = em;
		
		ComponentFactory cf = ComponentFactory.getInstance(type);
		this.creatable = cf.createCreatable(em);
		this.srReadable = cf.createSingleResultReadable(em);
		this.srqReadable = cf.createSingleResultQueryReadable(em);
		this.mrqReadable = cf.createMultiResultQueryReadable(em);
		this.qDeletable = cf.createQueryDeletable(em);
		this.deletable = cf.createDeletable(em);
	}
	
	public void create(T t){
		creatable.create(t);
	}
	
	public T singleResultRead(Class<T> type, Object id){
		return this.srReadable.read(type, id);
	}
	
	public T singleResultQueryRead(Class<T> type, String query, Object...params){
		return this.srqReadable.read(type, query, params);
	}
	
	public List<T> multiResultQueryRead(Class<T> type, String query, Object...params){
		return this.mrqReadable.read(type, query, params);
	}
	
	public void update(T t){
		em.merge(t);
	}
	
	public void queryDelete(Class<T> type, String query, Object...params){
		this.qDeletable.delete(type, query, params);
	}
	
	public void delete(T t){
		this.deletable.delete(t);
	}
}
