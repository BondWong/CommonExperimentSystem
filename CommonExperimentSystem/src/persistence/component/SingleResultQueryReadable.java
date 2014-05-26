package persistence.component;

public interface SingleResultQueryReadable {
	public <T> T read(Class<T> type, String query, Object...params);
}
