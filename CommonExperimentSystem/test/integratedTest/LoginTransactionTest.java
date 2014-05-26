package integratedTest;

import static org.junit.Assert.*;
import model.User;
import model.UserType;

import org.junit.Assert;
import org.junit.Test;

import persistence.transaction.Transaction;
import persistence.transaction.daoTransaction.DeleteUserTransaction;
import persistence.transaction.daoTransaction.LoginTransaction;
import persistence.transaction.daoTransaction.RegisterTransaction;

public class LoginTransactionTest {
	@Test
	public void testLoginTransaction() throws ClassNotFoundException{
		User user = null;
		user = new User();
		user.setUserType(UserType.STUDENT);
		user.setId("2011052407");
		user.setName("zhoumi");
		user.setPassword("1901103390");
		Transaction transaction = new RegisterTransaction();
		transaction.execute(user);
		
		transaction = new LoginTransaction();
		user = (User) transaction.execute("2011052407", "1901103390");
		
		Assert.assertNotNull(user);
		
		transaction = new DeleteUserTransaction();
		transaction.execute("2011052407");
		
		transaction = new LoginTransaction();
		user = (User) transaction.execute("2011052407", "1901103390");
		
		assertNull(user);
	}
}
