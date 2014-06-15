package unitTest;

import static org.junit.Assert.*;
import model.Course;
import model.User;

import org.junit.Test;

import utils.UserType;

public class CreateCourseTest {
	@Test
	public void testCreateCourse() {
		User professor = new User();
		professor.setUserType(UserType.PROFESSOR);
		Course course = new Course();
		
		professor.createCourse(course);
		assertEquals(1, professor.getCreatedCourses().size());
		assertEquals((User)professor, course.getOwner());
	}
}
