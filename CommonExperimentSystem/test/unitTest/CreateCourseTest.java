package unitTest;

import static org.junit.Assert.*;
import model.Course;
import model.User;
import model.UserType;

import org.junit.Test;

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
