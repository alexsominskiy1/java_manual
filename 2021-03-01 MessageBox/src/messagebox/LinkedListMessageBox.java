package messagebox;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedListMessageBox implements MessageBox{
	
	private static final int DEFAULT_MESSAGE_BOX_SIZE = 5;
	
	private LinkedList<Message> messageBox = new LinkedList<>();
	private int messageBoxSize = DEFAULT_MESSAGE_BOX_SIZE;
	
	private ReentrantLock lock = new ReentrantLock();
	private Condition sendersNotificationCondition = lock.newCondition();
	private Condition receiversNotificationCondition = lock.newCondition();

	public LinkedListMessageBox() {
		super();
	}

	public LinkedListMessageBox(int messageBoxSize) {
		super();
		this.messageBoxSize = messageBoxSize;
	}

	public void put(Message message) {
		try {
			lock.lock();
			while (messageBox.size() == messageBoxSize) {
				try {
					sendersNotificationCondition.await();
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread() + ": thread was interrupted");
				}
			}
			messageBox.addLast(message);
			receiversNotificationCondition.signal();
		} finally {
			lock.unlock();
		}
	}

	public Message take() {
		try {
			lock.lock();
			while (messageBox.size() == 0) {
				try {
					receiversNotificationCondition.await();
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread() + "thread was interrupted");
				}
			}
			Message message = messageBox.pollFirst();
			sendersNotificationCondition.signal();
			return message;
		} finally {
			lock.unlock();
		}
	}
}
