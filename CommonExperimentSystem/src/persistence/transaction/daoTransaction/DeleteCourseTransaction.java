package persistence.transaction.daoTransaction;

import java.util.List;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Course;
import model.User;
import model.UserType;

public class DeleteCourseTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Course> cdao = new DAO<Course>(Course.class, em);
		Course course = cdao.singleResultQueryRead(Course.class, "Course.getById", params[0]);
		List<User> students = udao.multiResultQueryRead(User.class, "User.getByUserType", UserType.STUDENT);
		for(User student : students){
			student.removeAppliedCourse(course);
			udao.update(student);
		}
		cdao.delete(course);
		User professor = udao.singleResultRead(User.class, course.getOwner().getId());
		professor.removeCreatedCourse(course);
		udao.update(professor);
		
		return null;
	}

}
