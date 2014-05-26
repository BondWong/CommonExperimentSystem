package unitTest;

import static org.junit.Assert.*;
import model.Course;
import model.User;
import model.UserType;

import org.junit.Test;

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
