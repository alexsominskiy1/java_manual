package messagebox;

import java.util.function.BiFunction;

public class Reciever<T> extends Thread {
	
	private String recieverName;
	private MessageBox<T> messageBox;
	private BiFunction<T, Reciever<T>, T> messageProcessor;
	private MessagesCollection<T> messagesCollection;
	
	public Reciever(String recieverName, MessageBox<T> messageBox, BiFunction<T,Reciever<T>, T> messageProcessor, 
			        MessagesCollection<T> messagesCollection) {
		super();
		this.recieverName = recieverName;
		this.messageBox = messageBox;
		this.messageProcessor = messageProcessor;
		this.messagesCollection = messagesCollection;
		setDaemon(true);                                     // optional to let the application finish
	}

	public String getRecieverName() {
		return recieverName;
	}

	@Override
	public void run() {
		while (true) messagesCollection.put(messageProcessor.apply(messageBox.take(), this)); 
	}
}
