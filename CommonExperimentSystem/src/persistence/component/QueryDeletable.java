package persistence.component;

public interface QueryDeletable {
	public <T> void delete(Class<T> type, String query, Object...params);
}
