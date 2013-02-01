package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Gods {
	//Test
	public static final int POSEIDON=3;
	public static final int ARES=2;
	public static final int ZEUS=1;
	public static final int ATHENA=0;
	public static final int APOLLON=4;
	
	public ArrayList<Integer> list;
	public boolean[] hidden;
	public Player[] owners;
	public int[] bets;
	public Player forbiddenPlayer;
	public int forbiddenGod;
	public Stack<Player> apollonStack;
	
	public Gods(int nbPlayer){
		bets=new int[4];
		owners=new Player[4];
		apollonStack=new Stack<Player>();
		hidden=new boolean[4];
		forbiddenPlayer=null;
		switch(nbPlayer){
		case 3:
			hidden[0]=false;
			hidden[1]=false;
			hidden[2]=true;
			hidden[3]=true;
			break;
		case 4:
			hidden[0]=false;
			hidden[1]=false;
			hidden[2]=false;
			hidden[3]=true;
			break;
		default:
			hidden[0]=false;
			hidden[1]=false;
			hidden[2]=false;
			hidden[3]=false;
			break;
		}
		hidden=new boolean[]{true,true,true,true};
		list=new ArrayList<Integer>();
		for(int i=1;i<=4;i++){
			list.add(i);
		}
		Collections.shuffle(list);
		
	}
	
	public void cycle(){
		Board.getInstance();
		switch(Board.getInstance().nbPlayer){
		case 3:
			if(hidden[2]){
				hidden[0]=true;
				hidden[1]=true;
				hidden[2]=false;
				hidden[3]=false;
			}
			else{
				hidden[0]=false;
				hidden[1]=false;
				hidden[2]=true;
				hidden[3]=true;
				Collections.shuffle(list);
			}
			break;
		case 4:
			int first=list.remove(list.size()-1);
			Collections.shuffle(list);
			list.add(0,first);
			hidden[0]=false;
			hidden[1]=false;
			hidden[2]=false;
			hidden[3]=true;
			break;
		default:
			Collections.shuffle(list);
			hidden[0]=false;
			hidden[1]=false;
			hidden[2]=false;
			hidden[3]=false;
			break;
		}
	}
	
	public Player bet(Player p){
		apollonStack.push(p);
		return Board.getInstance().pions.pop();
	}
	
	public Player bet(Player p, int god, int n){
		Player old;
		if((owners[god]!=null)&&(n>bets[god])){
			bets[god]=n;
			old=owners[god];
			owners[god]=p;
			return old;
		}
		else{
			if(owners[god]==null){
				bets[god]=n;
				owners[god]=p;
				return Board.getInstance().pions.pop();
			}
			else{
				return null;
			}
		}
		
	}
	

	
	
}
