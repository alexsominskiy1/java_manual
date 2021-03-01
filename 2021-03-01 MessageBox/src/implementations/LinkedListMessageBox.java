package implementations;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import messagebox.MessageBox;

public class LinkedListMessageBox<T> implements MessageBox<T>{
	
	private static final int DEFAULT_MESSAGE_BOX_SIZE = 5;
	
	private LinkedList<T> messageBox = new LinkedList<>();
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

	@Override
	public void put(T message) {
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

	@Override
	public T take() {
		try {
			lock.lock();
			while (messageBox.size() == 0) {
				try {
					receiversNotificationCondition.await();
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread() + "thread was interrupted");
				}
			}
			T message = messageBox.pollFirst();
			sendersNotificationCondition.signal();
			return message;
		} finally {
			lock.unlock();
		}
	}
}
