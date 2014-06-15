package unitTest;

import static org.junit.Assert.*;
import model.Course;
import model.User;

import org.junit.Test;

import utils.UserType;

public class ApplyCourse {
	@Test
	public void testApplyCourse() {
		Course course = new Course();
		User student = new User();
		student.setUserType(UserType.STUDENT);
		student.applyCourse(course);
		
		assertEquals(1, student.getAppliedCourses().size());
		assertEquals(1, course.getApplicationNum());
	}
}
