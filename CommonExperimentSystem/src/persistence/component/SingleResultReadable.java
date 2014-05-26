package persistence.component;

public interface SingleResultReadable {
	public <T> T read(Class<T> type, Object id);
}
