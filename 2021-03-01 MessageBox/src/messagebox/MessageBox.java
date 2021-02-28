package messagebox;

public interface MessageBox {	
	public void put(Message message);
	public Message take();
}