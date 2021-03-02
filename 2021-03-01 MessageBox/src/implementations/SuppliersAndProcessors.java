package implementations;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.BiFunction;
import java.util.function.Function;

import messagebox.Receiver;
import messagebox.Sender;

public class SuppliersAndProcessors {
	
	public static Function<Sender<Message>, Message> messageCreator(){
		return sender -> {
			Message message = new Message();
			message.setSenderName(sender.getSenderName());
			message.setSent(LocalDateTime.now());
			return message;
		};
	}
	
	public static BiFunction<Message, Receiver<Message>, Message> messageProcessor(long delay){
		return (message, receiver) -> {
			LocalDateTime received = LocalDateTime.now();
			message.setReceiverName(receiver.getRecieverName());
			message.setReceived(received);
			message.setDelay(Duration.between(message.getSent(), received).toMillis() + delay);
			
			try {
				Thread.sleep(delay);									// processing imitation
			} catch (InterruptedException e) {/* nop */}
			
			return message;
		};
	}

	


}
