package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.User;

public class UpdateUserTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<User> udao = new DAO<User>(User.class, em);
		User user = udao.singleResultQueryRead(User.class, "User.getByIdAndPassword", params[0],params[2]);
		user.setName((String)params[1]);
		
		if(params[3]!=null){
			user.setPassword((String)params[3]);
		}
		
		udao.update(user);
		return user;
	}

}
