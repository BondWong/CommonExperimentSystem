package persistence.factory;

import javax.persistence.EntityManager;

import persistence.component.Creatable;
import persistence.component.CreatableImp;
import persistence.component.Deletable;
import persistence.component.DeletableImp;
import persistence.component.MultiResultQueryReadable;
import persistence.component.MultiResultQueryReadableImp;
import persistence.component.QueryDeletable;
import persistence.component.QueryDeletableImp;
import persistence.component.SingleResultQueryReadable;
import persistence.component.SingleResultQueryReadableImp;
import persistence.component.SingleResultReadable;
import persistence.component.SingleResultReadableImp;

public class UserComponentFactory extends ComponentFactory{

	@Override
	public Creatable createCreatable(EntityManager em) {
		// TODO Auto-generated method stub
		return new CreatableImp(em);
	}
	
	@Override
	public SingleResultReadable createSingleResultReadable(EntityManager em){
		return new SingleResultReadableImp(em);
	}
	
	@Override
	public SingleResultQueryReadable createSingleResultQueryReadable(EntityManager em) {
		// TODO Auto-generated method stub
		return new SingleResultQueryReadableImp(em);
	}

	@Override
	public MultiResultQueryReadable createMultiResultQueryReadable(EntityManager em) {
		// TODO Auto-generated method stub
		return new MultiResultQueryReadableImp(em);
	}

	@Override
	public QueryDeletable createQueryDeletable(EntityManager em) {
		// TODO Auto-generated method stub
		return new QueryDeletableImp(em);
	}
	
	@Override
	public Deletable createDeletable(EntityManager em){
		return new DeletableImp(em);
	}
	
}
