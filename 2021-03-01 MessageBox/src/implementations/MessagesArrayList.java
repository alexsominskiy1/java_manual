package implementations;

import java.util.ArrayList;
import java.util.Collection;

import messagebox.MessagesCollection;

public class MessagesArrayList<T> implements MessagesCollection<T>{
	
	private ArrayList<T> messagesList = new ArrayList<>();
	
	public synchronized void put(T message) {
		messagesList.add(message);
	}
	
	public Collection<T> get(){
		return messagesList;
	}


}
