package unitTest;

import static org.junit.Assert.*;
import model.Course;
import model.Experiment;
import model.User;

import org.junit.Test;

import utils.UserType;

public class AllTogetherTest {
	@Test
	public void testAllTogether() {
		User student = new User();
		student.setUserType(UserType.STUDENT);
		User professor = new User();
		professor.setUserType(UserType.PROFESSOR);
		
		Course course = new Course();
		Experiment experiment = new Experiment();
		
		professor.createCourse(course);
		professor.createExperiment(course, experiment);
		
		student.applyCourse(course);
		student.submitReport(experiment, "haha");
		
		assertEquals(student.getAppliedCourses().iterator().next(), professor.getCreatedCourses().iterator().next());
	}
}
