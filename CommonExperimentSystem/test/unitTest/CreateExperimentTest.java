package unitTest;

import static org.junit.Assert.*;
import model.Course;
import model.Experiment;
import model.User;
import model.UserType;

import org.junit.Test;

public class CreateExperimentTest {
	@Test
	public void testCreateExperiment() {
		Course course = new Course();
		User professor = new User();
		professor.setUserType(UserType.PROFESSOR);
		Experiment experiment = new Experiment();
		
		professor.createCourse(course);
		professor.createExperiment(course, experiment);
		
		assertEquals(course.getExperiments().iterator().next(), experiment);
	}
}
