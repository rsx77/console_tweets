import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.collections4.MultiMap;


public class Functionality {

	@SuppressWarnings("rawtypes")
	MultiMap userMessages = new MultiValueMap();
	@SuppressWarnings("rawtypes")
	MultiMap userFollowing = new MultiValueMap();
	ArrayList<User> userList = new ArrayList<User>();
	User newUser = null;
	Driver newDriver = new Driver();

	long systemStartTimeStamp = System.currentTimeMillis();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void postToTimeLine(String userName, String newMessage){
		newUser = new User(userName);
		if(userList.contains(newUser.getUserName())){
			storeMessage(userName, newMessage);
		}else{
			createUser(userName);
			userFollowing.put(userName, userName);
			storeMessage(userName, newMessage);
		}
	}

	@SuppressWarnings("unchecked")
	public void storeMessage(String userName, String newMessage){
		userMessages.put(userName, (new Message(newMessage, calculatePostTime())));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void showUserTimeLine(String user){
		// get all the set of keys
		Set<String> keys = userMessages.keySet();
		Message msg = null;

		for (String key : keys) {
			String user_name = key;
			ArrayList userMsgs = (ArrayList) userMessages.get(key);
			//System.out.println(key + " -> " + multiMap.get(key));
			int i = 0;
			while(i <= (userMsgs.size() - 1) ){
				if(user_name.equalsIgnoreCase(user)){
					msg = (Message) userMsgs.get(i);
					System.out.println(user_name + " -> " + msg.getMessage() + " (" + msg.getMessageTime()+" minute(s) ago) ");
					i++;
				}else{
					System.out.println();
					break;
				}
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void showUserTimeLine(){
		// get all the set of keys
		Set<String> keys = userMessages.keySet();
		Message msg = null;

		for (String key : keys) {
			String user_name = key;
			ArrayList userMsgs = (ArrayList) userMessages.get(key);
			//System.out.println(key + " -> " + multiMap.get(key));
			int i = 0;
			while(i <= (userMsgs.size() - 1) ){
				//if(user_name.equalsIgnoreCase(user)){
					msg = (Message) userMsgs.get(i);
					System.out.println(user_name + " -> " + msg.getMessage() + " (" + msg.getMessageTime()+" minute(s) ago) ");
					i++;
				/*}else{
					System.out.println();
					break;
				}*/
			}
		}
	}
	
	public int calculatePostTime(){
		int postedTime = (int)((System.currentTimeMillis() - systemStartTimeStamp)/(60*1000));
		if(postedTime >= 0 && postedTime <= 1){
			postedTime = 1;
		}else if(postedTime >= 2){
			postedTime = (int) Math.round(postedTime); 
		}
		return postedTime;
	}


	@SuppressWarnings("unchecked")
	public void subscribeToUser(String userSub, String userPub){
		userFollowing.put(userSub, userPub);
		storeMessage(userPub, ""+userSub+" follows "+userPub+"");
	}

	public void createUser(String name){
		newUser = new User(name);
		userList.add(newUser);
	}
	
	public void listAllUsers(){
		for(int i=0; i<=(userList.size() - 1); i++){
			System.out.println(userList.get(i).getUserName());
		}
	}

	public void readPublishersTimeline(String subName, String pubName){
		ArrayList userList = (ArrayList) userFollowing.get(subName);
		int i = 0;
		while(i <= (userList.size() - 1)){
			String userPub = (String) userList.get(i);
			//System.out.println("userPub = " +userPub);
			if(userPub.equalsIgnoreCase(pubName)){
				System.out.println(userPub + "'s TimeLine");
				showUserTimeLine(userPub);
				break;
			}else{
				i++;
			}
		}
	}


}
