package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import model.User;
import persistence.DAO;

public class RegisterTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<User> udao = new DAO<User>(User.class, em);
		udao.create((User)params[0]);
		return null;
	}

}
