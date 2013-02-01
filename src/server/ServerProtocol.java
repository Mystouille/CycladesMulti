package server;

import data.Board;

public class ServerProtocol {
	private State state;
	
	public ServerProtocol() {
		state=State.WAITING;
	}
	
	enum State{
		WAITING
	}
	
    public synchronized String processInput(String theInput, Board a) {
        String theOutput = null;
        
        switch(state){
        case WAITING:break;
        	
        }

        return theOutput;
    }
}
