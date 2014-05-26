package unitTest;

import static org.junit.Assert.*;
import model.Course;
import model.Experiment;
import model.Letter;
import model.User;

import org.junit.Test;

import persistence.component.CreatableImp;
import persistence.component.MultiResultQueryReadableImp;
import persistence.component.NonCreatable;
import persistence.component.NonQueryDeletable;
import persistence.component.NonSingleResultQueryReadable;
import persistence.component.QueryDeletableImp;
import persistence.component.SingleResultQueryReadableImp;
import persistence.factory.ComponentFactory;

public class ComponentFactoryTest {
	@Test
	public void testUserComponentFactory() throws ClassNotFoundException{
		ComponentFactory cf = ComponentFactory.getInstance(User.class);
		assertEquals(CreatableImp.class, cf.createCreatable(null).getClass());
		assertEquals(SingleResultQueryReadableImp.class, cf.createSingleResultQueryReadable(null).getClass());
		assertEquals(MultiResultQueryReadableImp.class, cf.createMultiResultQueryReadable(null).getClass());
		assertEquals(QueryDeletableImp.class, cf.createQueryDeletable(null).getClass());
	}
	
	@Test
	public void testCourseComponentFactory() throws ClassNotFoundException{
		ComponentFactory cf = ComponentFactory.getInstance(Course.class);
		assertEquals(NonCreatable.class, cf.createCreatable(null).getClass());
		assertEquals(SingleResultQueryReadableImp.class, cf.createSingleResultQueryReadable(null).getClass());
		assertEquals(MultiResultQueryReadableImp.class, cf.createMultiResultQueryReadable(null).getClass());
		assertEquals(NonQueryDeletable.class, cf.createQueryDeletable(null).getClass());
	}
	
	@Test
	public void testExperimentComponentFactory() throws ClassNotFoundException{
		ComponentFactory cf = ComponentFactory.getInstance(Experiment.class);
		assertEquals(NonCreatable.class, cf.createCreatable(null).getClass());
		assertEquals(SingleResultQueryReadableImp.class, cf.createSingleResultQueryReadable(null).getClass());
		assertEquals(MultiResultQueryReadableImp.class, cf.createMultiResultQueryReadable(null).getClass());
		assertEquals(NonQueryDeletable.class, cf.createQueryDeletable(null).getClass());
	}
	
	@Test
	public void testLetterComponentFactory() throws ClassNotFoundException{
		ComponentFactory cf = ComponentFactory.getInstance(Letter.class);
		assertEquals(CreatableImp.class, cf.createCreatable(null).getClass());
		assertEquals(NonSingleResultQueryReadable.class, cf.createSingleResultQueryReadable(null).getClass());
		assertEquals(MultiResultQueryReadableImp.class, cf.createMultiResultQueryReadable(null).getClass());
		assertEquals(QueryDeletableImp.class, cf.createQueryDeletable(null).getClass());
	}
}
