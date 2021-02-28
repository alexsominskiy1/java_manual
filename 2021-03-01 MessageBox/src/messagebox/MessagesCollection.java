package messagebox;

import java.util.Collection;

public interface MessagesCollection {
	
	public void put(Message message);
	public Collection<Message> get();
}
