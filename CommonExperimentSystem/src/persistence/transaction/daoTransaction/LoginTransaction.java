package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.User;

public class LoginTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<User> udao = new DAO<User>(User.class, em);
		return udao.singleResultQueryRead(User.class, "User.getByIdAndPassword", params);
	}

}
