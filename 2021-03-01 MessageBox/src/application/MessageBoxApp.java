package application;

import messagebox.MessageBox;

import implementations.LinkedListMessageBox;
import implementations.Message;
import implementations.MessagesArrayList;
import implementations.SuppliersAndProcessors;
import messagebox.MessagesCollection;
import messagebox.Receiver;
import messagebox.Sender;

public class MessageBoxApp {

	private static final int MESSAGE_BOX_SIZE = 10;
	private static final int NUM_RECEIVERS = 10;
	private static final long RECEIVER_DELAY = 200;
	private static final int NUM_MESSAGES = 100;
	private static final long SENDER_DELAY = 10;

	public static void main(String[] args) throws InterruptedException {
		
		MessageBox<Message> messageBox = (MessageBox<Message>) new LinkedListMessageBox<Message>(MESSAGE_BOX_SIZE);
		MessagesCollection<Message> messagesCollection = new MessagesArrayList<Message>();
		
		Receiver<Message>[] receivers = new Receiver[NUM_RECEIVERS];
		for (int i = 0; i < receivers.length; i++) {
			receivers[i] = new Receiver<Message>("Receiver #"+i, messageBox, 
					                             SuppliersAndProcessors.getMessageProcessor(RECEIVER_DELAY), 
					                             messagesCollection);
		}
		for (int i = 0; i < receivers.length; i++) receivers[i].start();
		
		Sender<Message>[] senders = new Sender[NUM_MESSAGES];
		for (int i=0; i < senders.length; i++) {
			senders[i] = new Sender<Message>("Sender #"+i, messageBox, SuppliersAndProcessors.getMessageSupplier());
			senders[i].start();
			Thread.sleep(SENDER_DELAY);	
		}
		
		for (int i=0; i < senders.length; i++)senders[i].join();		
		Thread.sleep(2*MESSAGE_BOX_SIZE*RECEIVER_DELAY + 1000);
		
		// application stop
		
		System.out.println("messages processed: " + messagesCollection.get().size());
		messagesCollection.get().stream().forEach(System.out::println);
		long maxDelay = messagesCollection.get().stream().mapToLong(Message::getDelay).max().getAsLong();
		System.out.println("max delay: " + maxDelay);	
	}
}
