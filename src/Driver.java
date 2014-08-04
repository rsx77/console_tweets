import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;

import com.sun.jmx.snmp.Timestamp;


public class Driver {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String menuChoice = "";
	static String mainMenuChoice = "";
	static String currentUser = null;

	final static Functionality func = new Functionality();
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//System.out.println(calculateTime(System.currentTimeMillis()));

		int i=0;

		while(true){
			print_Start_Menu();
		}
	}

	public static void print_Start_Menu(){
		System.out.println(" +--------------------------------+ ");
		System.out.println(" |              Main Menu         | ");
		System.out.println(" +--------------------------------+ ");
		System.out.println(" |    1 : Post to Timeline        | ");
		System.out.println(" |    2 : Read Timeline           | ");
		System.out.println(" |    3 : Read a Users Timeline   | ");
		System.out.println(" |    4 : Follow a User      	  | ");
		System.out.println(" |    5 : Exit                    | ");
		System.out.println(" +--------------------------------+ ");
		System.out.println();
		try {
			System.out.println("Please choose an option between (1-3) from the Menu.");
			System.out.print("Enter your option: ");
			menuChoice = br.readLine();
			if(menuChoice.equals("1")){
				post();
			}else if(menuChoice.equals("2")){
				System.out.println();
				System.out.println();
				func.showUserTimeLine(currentUser);
				System.out.println();
				System.out.println();
			}else if(menuChoice.equals("3")){
				viewTimeLine();
			}else if(menuChoice.equals("4")){
				followUser();
			}else{
				System.out.println("Please select one of the options from the Menu");
			}
		} catch (IOException ioe) {
			System.out.println("IO error trying to read your name!");
			System.exit(1);
		}
	}

	public static void post(){
		try {
			currentUser = null;
			System.out.print("Enter your Name: ");
			String userName = br.readLine();
			
			System.out.print("Enter your thoughts you would like to post: ");
			String newMessage = br.readLine();
			
			currentUser = userName;
			
			func.postToTimeLine(userName, newMessage);
			
			displayAllMessages();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void displayAllMessages(){
		System.out.println();
		System.out.println();
		func.showUserTimeLine();
		System.out.println();
		System.out.println();
	}

	public static void viewTimeLine(){
		try {
			System.out.print("Who's timeline would you like to view?: ");
			String pubName = br.readLine();
			
			func.readPublishersTimeline(currentUser, pubName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void followUser(){
		try {
			System.out.print("Type user's name you would like to follow?: ");
			String pubName = br.readLine();
			
			func.subscribeToUser(currentUser, pubName);	
			displayAllMessages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
