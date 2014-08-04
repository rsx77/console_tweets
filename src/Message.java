
public class Message {
	private String message;
	private long time;
	
	public Message(){
		
	}
	
	public Message(String newMessage, long newMessageTime){
		this.message = newMessage;
		this.time = newMessageTime;
	}
	
	public void setMessage(String newMessage){
		this.message = newMessage;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public void setMessageTime(long newMessageTime){
		this.time= newMessageTime;
	}
	
	public long getMessageTime(){
		return this.time;
	}
}
