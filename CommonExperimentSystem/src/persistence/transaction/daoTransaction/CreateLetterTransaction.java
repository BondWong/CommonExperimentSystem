package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Letter;
import model.User;

public class CreateLetterTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<User> udao = new DAO<User>(User.class ,em);
		DAO<Letter> ldao = new DAO<Letter>(Letter.class, em);
		User owner = udao.singleResultQueryRead(User.class, "User.getById", params[0]);
		User receiver = udao.singleResultQueryRead(User.class, "getById", params[1]);
		Letter letter = (Letter) params[2];
		owner.sendLetter(receiver, letter);
		ldao.update(letter);
		return null;
	}

}
