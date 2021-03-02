package implementations;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.BiFunction;
import java.util.function.Function;

import messagebox.Reciever;
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
	
	public static BiFunction<Message, Reciever<Message>, Message> messageProcessor(long delay){
		return (message, reciever) -> {
			LocalDateTime recieved = LocalDateTime.now();
			message.setRecieverName(reciever.getRecieverName());
			message.setRecieved(recieved);
			message.setDelay(Duration.between(message.getSent(), recieved).toMillis() + delay);
			
			try {
				Thread.sleep(delay);									// processing imitation
			} catch (InterruptedException e) {/* nop */}
			
			return message;
		};
	}

	


}
