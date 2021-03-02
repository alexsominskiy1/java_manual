package messagebox;

import java.util.function.BiFunction;

public class Receiver<T> extends Thread {
	
	private String receiverName;
	private MessageBox<T> messageBox;
	private BiFunction<T, Receiver<T>, T> messageProcessor;
	private MessagesCollection<T> messagesCollection;
	
	public Receiver(String receiverName, MessageBox<T> messageBox, BiFunction<T,Receiver<T>, T> messageProcessor, 
			        MessagesCollection<T> messagesCollection) {
		super();
		this.receiverName = receiverName;
		this.messageBox = messageBox;
		this.messageProcessor = messageProcessor;
		this.messagesCollection = messagesCollection;
		setDaemon(true);                                     // optional to let the application finish
	}

	public String getRecieverName() {
		return receiverName;
	}

	@Override
	public void run() {
		while (true) messagesCollection.put(messageProcessor.apply(messageBox.take(), this)); 
	}
}
