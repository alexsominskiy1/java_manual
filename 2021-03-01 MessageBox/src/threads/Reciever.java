package threads;

import java.time.Duration;
import java.time.LocalDateTime;
import messagebox.Message;
import messagebox.MessageBox;
import messagebox.MessagesCollection;

public class Reciever extends Thread {
	
	private String recieverName;
	private MessageBox messageBox;
	private long delay;
	private MessagesCollection messagesList;
	
	public Reciever(String recieverName, MessageBox messageBox, long delay, MessagesCollection messagesList) {
		super();
		this.recieverName = recieverName;
		this.messageBox = messageBox;
		this.delay = delay;
		this.messagesList = messagesList;
		setDaemon(true);
	}

	@Override
	public void run() {
		while (true) {
			Message message = messageBox.take();
			LocalDateTime recieved = LocalDateTime.now();
			message.setRecieverName(recieverName);
			message.setRecieved(recieved);
			message.setDelay(Duration.between(message.getSent(), recieved).toMillis() + delay);
			messagesList.put(message);
			
			try {
				sleep(delay);									// processing imitation
			} catch (InterruptedException e) {/* nop */}	
		}
	}
}
