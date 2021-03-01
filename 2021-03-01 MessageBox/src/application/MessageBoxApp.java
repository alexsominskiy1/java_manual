package application;

import messagebox.MessageBox;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.BiFunction;
import java.util.function.Function;

import implementations.LinkedListMessageBox;
import implementations.Message;
import implementations.MessagesArrayList;
import messagebox.MessagesCollection;
import messagebox.Reciever;
import messagebox.Sender;

public class MessageBoxApp {

	private static final int MESSAGE_BOX_SIZE = 10;
	private static final int NUM_RECIEVERS = 10;
	private static final long RECIEVER_DELAY = 200;
	private static final int NUM_MESSAGES = 100;
	private static final long SENDER_DELAY = 10;
	
	private static BiFunction<Message, Reciever<Message>, Message> messageProcessor(){
		return (message, reciever) -> {
			LocalDateTime recieved = LocalDateTime.now();
			message.setRecieverName(reciever.getRecieverName());
			message.setRecieved(recieved);
			message.setDelay(Duration.between(message.getSent(), recieved).toMillis() + RECIEVER_DELAY);
			
			try {
				Thread.sleep(RECIEVER_DELAY);									// processing imitation
			} catch (InterruptedException e) {/* nop */}
			
			return message;
		};
	}
	
	private static Function<Sender<Message>, Message> messageCreator(){
		return sender -> {
			Message message = new Message();
			message.setSenderName(sender.getSenderName());
			message.setSent(LocalDateTime.now());
			return message;
		};
	}

	public static void main(String[] args) throws InterruptedException {
		
		MessageBox<Message> messageBox = (MessageBox<Message>) new LinkedListMessageBox<Message>(MESSAGE_BOX_SIZE);
		MessagesCollection<Message> messagesCollection = new MessagesArrayList<Message>();
		
		Reciever<Message>[] recievers = new Reciever[NUM_RECIEVERS];
		for (int i = 0; i < recievers.length; i++) {
			recievers[i] = new Reciever<Message>("Reciever #"+i, messageBox, messageProcessor(), messagesCollection);
		}
		for (int i = 0; i < recievers.length; i++) recievers[i].start();
		
		Sender<Message>[] senders = new Sender[NUM_MESSAGES];
		for (int i=0; i < senders.length; i++) {
			senders[i] = new Sender<Message>("Sender #"+i, messageBox, messageCreator());
			senders[i].start();
			Thread.sleep(SENDER_DELAY);	
		}
		
		for (int i=0; i < senders.length; i++)senders[i].join();		
		Thread.sleep(2*MESSAGE_BOX_SIZE*RECIEVER_DELAY + 1000);
		
		System.out.println("messages processed: " + messagesCollection.get().size());
		messagesCollection.get().stream().forEach(System.out::println);
		long maxDelay = messagesCollection.get().stream().mapToLong(Message::getDelay).max().getAsLong();
		System.out.println("max delay: " + maxDelay);	
	}
}
