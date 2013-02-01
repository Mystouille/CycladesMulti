package client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import data.Board;

public class Client {
    Socket kkSocket = null;
    ClientProtocol protocol;
    PrintWriter out = null;
    BufferedReader in = null;
    ObjectInputStream ois = null;
    Board board;
    public Client(){
    	try {
			launch();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

	 public void launch() throws IOException {
		 	board= Board.getInstance();
		 	protocol=new ClientProtocol(board);
	        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	        String fromServer;
	        String fromUser;

	        LobbyWindow lobbyWindow=new LobbyWindow(this);

//	        out.close();
//	        in.close();
//	        stdIn.close();
//	        kkSocket.close();
	    }
	 
	 public void startGame(){
		 
	 }





	public boolean extablishConnexionTo(String text) {
    	try {
			kkSocket = new Socket(text, 4444);
			out = new PrintWriter(kkSocket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
		} catch (UnknownHostException e) {
            System.err.println("Don't know about host");
            return false;
		} catch (IOException e) {	            
			System.err.println("Couldn't get I/O for the connection to host");
			return false;
		}
		return true;
	}
	
	
}
