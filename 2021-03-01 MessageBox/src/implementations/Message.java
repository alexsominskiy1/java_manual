package implementations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss.SSS");
	
	private String senderName;
	private LocalDateTime sent;
	private String receiverName;
	private LocalDateTime received;
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

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public LocalDateTime getRecieved() {
		return received;
	}

	public void setReceived(LocalDateTime received) {
		this.received = received;
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
			 "; receiver: " + receiverName + ", received: " + dtf.format(received) + 
             "; delay: " + delay + "ms";
	}
}
