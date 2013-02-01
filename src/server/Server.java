package server;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public boolean listening=true;
	public int nbClients;
	ServerSocket serverSocket;
	ServerProtocol sp;
	
	public Server(){
		try {
            serverSocket = new ServerSocket(4444);
           
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(-1);
        }
        sp=new ServerProtocol();
        nbClients=0;
	}
	
	public void launch() throws IOException{
        while (listening){
        	System.out.println("new listening thread created");
        	Socket s=serverSocket.accept();
        	System.out.println("Connexion made to "+s.getInetAddress().toString()+" on port "+s.getPort());
        	new ServerConnexion(s,sp).start();
        }
        serverSocket.close();
	}
	
	
    public static void main(String[] args) throws IOException {
        Server s=new Server();
        s.launch();
    }
}
