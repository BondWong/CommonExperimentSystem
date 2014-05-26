package persistence.transaction;

public interface Transaction {
	public Object execute(Object...params) throws ClassNotFoundException;
}
