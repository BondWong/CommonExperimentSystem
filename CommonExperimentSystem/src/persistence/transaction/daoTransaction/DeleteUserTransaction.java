package persistence.transaction.daoTransaction;

import java.util.List;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Course;
import model.User;
import model.UserType;

public class DeleteUserTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<User> udao = new DAO<User>(User.class, em);
		User user = udao.singleResultRead(User.class, params[0]);
		if(user.getUserType().equals(UserType.PROFESSOR)){
			List<User> students = udao.multiResultQueryRead(User.class, "User.getByUserType", UserType.STUDENT);
			for(Course c : user.getCreatedCourses()){
				for(User s :students){
					s.removeAppliedCourse(c);
					udao.update(s);
				}
			}
		}
		udao.delete(user);
		return null;
	}

}
