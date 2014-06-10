package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.User;

public class IsReportSentTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		User user = null;
		DAO<User> udao = new DAO<User>(User.class, em);
		user = udao.singleResultRead(User.class, params[0]);
		System.out.println(user.getReportedExpIds() + " " + params[1]);
		return user.getReportedExpIds().contains((Long) params[1]);
	}

}
