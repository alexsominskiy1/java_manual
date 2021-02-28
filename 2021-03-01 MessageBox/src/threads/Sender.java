package threads;

import java.time.LocalDateTime;

import messagebox.Message;
import messagebox.MessageBox;

public class Sender extends Thread {
	
	private String senderName;
	private MessageBox messageBox;

	public Sender(String senderName, MessageBox messageBox) {
		super();
		this.senderName = senderName;
		this.messageBox = messageBox;
	}


	@Override
	public void run() {
		Message message = new Message();
		message.setSenderName(senderName);
		message.setSent(LocalDateTime.now());
		messageBox.put(message);
	}
}
