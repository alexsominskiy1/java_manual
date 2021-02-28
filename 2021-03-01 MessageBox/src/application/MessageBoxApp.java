package application;

import messagebox.Message;
import messagebox.MessageBox;
import messagebox.LinkedListMessageBox;
import messagebox.MessagesArrayList;
import messagebox.MessagesCollection;
import threads.Reciever;
import threads.Sender;

public class MessageBoxApp {

	private static final int MESSAGE_BOX_SIZE = 10;
	private static final int NUM_RECIEVERS = 10;
	private static final long RECIEVER_DELAY = 200;
	private static final int NUM_MESSAGES = 100;
	private static final long SENDER_DELAY = 10;

	public static void main(String[] args) throws InterruptedException {
		
		MessageBox messageBox = new LinkedListMessageBox(MESSAGE_BOX_SIZE);
		MessagesCollection messagesList = new MessagesArrayList();
		 
		Reciever[] recievers = new Reciever[NUM_RECIEVERS];
		for (int i = 0; i < recievers.length; i++) {
			recievers[i] = new Reciever("Reciever #"+i, messageBox, RECIEVER_DELAY, messagesList);
		}
		for (int i = 0; i < recievers.length; i++) recievers[i].start();
		
		Sender[] senders = new Sender[NUM_MESSAGES];
		for (int i=0; i < senders.length; i++) {
			senders[i] = new Sender("Sender #"+i, messageBox);
			senders[i].start();
			Thread.sleep(SENDER_DELAY);	
		}
		
		for (int i=0; i < senders.length; i++)senders[i].join();		
		Thread.sleep(2*MESSAGE_BOX_SIZE*RECIEVER_DELAY);
		
		messagesList.get().stream().forEach(System.out::println);
		long maxDelay = messagesList.get().stream().mapToLong(Message::getDelay).max().getAsLong();
		System.out.println("max delay: " + maxDelay);	
	}
}
