package messagebox;

import java.util.function.Function;

public class Sender<T> extends Thread {
	
	private String senderName;
	private Function<Sender<T>, T> messageSupplier;
	private MessageBox<T> messageBox;

	public Sender(String senderName, MessageBox<T> messageBox, Function<Sender<T>, T> messageSupplier) {
		super();
		this.senderName = senderName;
		this.messageSupplier = messageSupplier;
		this.messageBox = messageBox;
	}

	public String getSenderName() {
		return senderName;
	}

	@Override
	public void run() {
		messageBox.put(messageSupplier.apply(this));
	}
}
