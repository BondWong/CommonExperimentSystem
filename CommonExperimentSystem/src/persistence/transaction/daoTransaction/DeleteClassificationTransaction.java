package persistence.transaction.daoTransaction;

import java.util.List;

import javax.persistence.EntityManager;

import model.Course;
import model.User;
import model.UserType;
import persistence.DAO;

public class DeleteClassificationTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<User> udao = new DAO<User>(User.class, em);
		List<User> users = udao.multiResultQueryRead(User.class, "User.getByClassification", params);
		System.out.println(users);
		if(users.get(0).getUserType().equals(UserType.PROFESSOR)){
			List<User> students = udao.multiResultQueryRead(User.class, "User.getByUserType", UserType.STUDENT);
			for(User professor : users){
				for(Course course : professor.getCreatedCourses()){
					for(User student : students){
						student.removeAppliedCourse(course);
						udao.update(student);
					}
				}
				udao.delete(professor);
			}
		} else{
			for(User user : users){
				udao.delete(user);
			}
		}
		
		
		return null;
	}

}
