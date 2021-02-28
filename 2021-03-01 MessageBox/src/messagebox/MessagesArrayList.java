package messagebox;

import java.util.ArrayList;
import java.util.Collection;

public class MessagesArrayList implements MessagesCollection{
	
	private ArrayList<Message> messagesList = new ArrayList<>();
	
	public synchronized void put(Message message) {
		messagesList.add(message);
	}
	
	public Collection<Message> get(){
		return messagesList;
	}


}
