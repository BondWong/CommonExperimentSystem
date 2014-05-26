package persistence.component;

import java.util.List;

public interface MultiResultQueryReadable {
	public <T> List<T> read(Class<T> type, String query, Object...params);
}
