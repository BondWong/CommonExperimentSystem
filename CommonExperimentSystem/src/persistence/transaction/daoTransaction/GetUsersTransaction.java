package persistence.transaction.daoTransaction;

import java.util.List;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.User;

public class GetUsersTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<User> udao = new DAO<User>(User.class, em);
		System.out.println(params[0]);
		List<User> users = udao.multiResultQueryRead(User.class, "User.getByClassification", params[0]);
		System.out.println(users);
		return users;
	}

}
