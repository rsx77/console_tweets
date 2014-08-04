
public class User {
	private String userName;
	
	public User(){
	}
	
	public User(String name){
		this.userName = name;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public void setUserName(String name){
		this.userName = name;
	}
	
}
