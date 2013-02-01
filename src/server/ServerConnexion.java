package server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import data.Board;

public class ServerConnexion extends Thread{
    private Socket socket = null;
    private ServerProtocol sp;
    private Board data;
    private ObjectOutputStream oos = null;


 public ServerConnexion(Socket socket,ServerProtocol sp) {
	super("KKMultiServerThread");
	this.socket = socket;
	this.sp=sp;
}

public void run() {

try {
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(
			    new InputStreamReader(
			    socket.getInputStream()));

    String inputLine, outputLine;
    outputLine = sp.processInput(null,data);
    out.println(outputLine);
    //oos = new ObjectOutputStream(socket.getOutputStream());

    while ((inputLine = in.readLine()) != null) {
		outputLine = sp.processInput(inputLine,data);
		out.println(outputLine);
		if (outputLine.equals("Bye")){
			System.out.println("Terminating run method in thread");
		    break;
		}
		if (outputLine.equals("getdata")){
			System.out.println("Sending Data");
			synchronized(data){
				oos.writeObject(data);
				oos.flush();
			}
		    break;
		}
    }
    out.close();
    in.close();
    oos.close();
    socket.close();

} catch (IOException e) {
    e.printStackTrace();
}
}
}
