package persistence.factory;

import javax.persistence.EntityManager;

import model.Course;
import model.Experiment;
import model.Letter;
import model.User;
import persistence.component.Creatable;
import persistence.component.Deletable;
import persistence.component.MultiResultQueryReadable;
import persistence.component.QueryDeletable;
import persistence.component.SingleResultQueryReadable;
import persistence.component.SingleResultReadable;

public abstract class ComponentFactory {
	
	public static <T> ComponentFactory getInstance(Class<T> type) throws ClassNotFoundException{
		if(type.equals(User.class)){
			return new UserComponentFactory();
		} else if(type.equals(Course.class)){
			return new CourseComponentFactory();
		} else if(type.equals(Experiment.class)){
			return new ExperimentComponentFactory();
		} else if(type.equals(Letter.class)) {
			return new LetterCompoenentFactory();
		} else{
			throw new ClassNotFoundException();
		}
	}
	
	public abstract Creatable createCreatable(EntityManager em);
	
	public abstract SingleResultReadable createSingleResultReadable(EntityManager em);
	
	public abstract SingleResultQueryReadable createSingleResultQueryReadable(EntityManager em);
	
	public abstract MultiResultQueryReadable createMultiResultQueryReadable(EntityManager em);
	
	public abstract QueryDeletable createQueryDeletable(EntityManager em);

	public abstract Deletable createDeletable(EntityManager em);
}
