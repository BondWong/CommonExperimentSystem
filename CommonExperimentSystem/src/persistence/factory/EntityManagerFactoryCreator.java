package persistence.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryCreator {
	private static EntityManagerFactoryCreator instance;
	private static EntityManagerFactory emf;
	
	private EntityManagerFactoryCreator() {
		emf = Persistence.createEntityManagerFactory("CommonExperimentSystemPersistence");
	}
	
	public static EntityManagerFactoryCreator getInstance(){
		if(instance == null){
			synchronized(EntityManagerFactoryCreator.class){
				if(instance == null){
					instance = new EntityManagerFactoryCreator();
				}
			}
		}
		
		return instance;
	}
	
	public EntityManagerFactory getEntityManagerFactory(){
		return emf;
	}
	
}
