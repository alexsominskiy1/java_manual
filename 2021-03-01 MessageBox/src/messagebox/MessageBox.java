package messagebox;

public interface MessageBox<T> { 	
	public void put(T message);
	public T take();
}