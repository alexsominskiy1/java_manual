package messagebox;

import java.util.Collection;

public interface MessagesCollection<T> {
	
	public void put(T message);
	public Collection<T> get();
}
