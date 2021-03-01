package implementations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss.SSS");
	
	private String senderName;
	private LocalDateTime sent;
	private String recieverName;
	private LocalDateTime recieved;
	private long delay;
	
	public Message() {}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public LocalDateTime getSent() {
		return sent;
	}

	public void setSent(LocalDateTime sent) {
		this.sent = sent;
	}

	public String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}

	public LocalDateTime getRecieved() {
		return recieved;
	}

	public void setRecieved(LocalDateTime recieved) {
		this.recieved = recieved;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	@Override
	public String toString() {
		return "sender: " + senderName + "; sent: " + dtf.format(sent) + 
			 "; reciever: " + recieverName + ", recieved: " + dtf.format(recieved) + 
             "; delay: " + delay + "ms";
	}
}
