package persistence.transaction.daoTransaction;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Letter;

public class GetLettersTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Object... params)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		DAO<Letter> ldao = new DAO<Letter>(Letter.class, em);		
		return ldao.multiResultQueryRead(Letter.class, "Letter.getByOwnerId", params[0]);
	}

}
