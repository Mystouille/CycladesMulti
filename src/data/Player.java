package data;
import java.awt.Color;


public class Player {
	public Color color;
	public int nbCoin;
	
	public int[] nbBuilding=new int[5];
	public int nbHarbor;
	
	public int nbPhilosopher;
	public int nbPriest;
	public int nbShip;
	public int nbWarrior;
	
	public Player(Color c){
		color=c;
		nbCoin=5;
		nbPhilosopher=0;
		nbPriest=0;
		nbShip=8;
		nbWarrior=8;
	}
	
	public boolean takeCoin(int n){
		int coinsRecieved=Box.getInstance().removeCoin(n);
		nbCoin=nbCoin+coinsRecieved;
		return (n==coinsRecieved);
		
	}
	
	public boolean spendCoin(int n){
		if(n<0 || n>nbCoin){
			return false;
		}
		else{
			Box.getInstance().addCoin(n);
			nbCoin=nbCoin-n;
			return true;
		}
	}
	
	
	
	public boolean putHorn(CellIsland island){
		boolean hornRecieved=Box.getInstance().removeHorn();
		if(hornRecieved){
			island.addHorn();
		}
		return hornRecieved;
	}
	
	public boolean putBuilding(CellIsland island, int type){
		boolean buildingRecieved= Box.getInstance().removeBuilding(type);
		if(buildingRecieved){
			island.build(type);
			nbBuilding[type]++;
		}
		return buildingRecieved;
	}
	
	public boolean destroyBuilding(CellIsland island, int type){
		boolean buildingDemolished=island.demolish(type);
		if(buildingDemolished){
			Box.getInstance().addBuilding(type);
			nbBuilding[type]--;
		}
		return buildingDemolished;
	}
	
	public boolean winPhilosopher(){
		boolean gotPhilosopher=Box.getInstance().removePhilosopher();
		if(gotPhilosopher){
			nbPhilosopher++;
		}
		return gotPhilosopher;
	}
	
	public boolean losePhilosopher(){
		if(nbPhilosopher>0){
			nbPhilosopher--;
			Box.getInstance().addPhilosopher();
			return true;
		}
		return false;
	}
	
	
	public boolean winPriest(){
		boolean gotPriest=Box.getInstance().removePriest();
		if(gotPriest){
			nbPriest++;
		}
		return gotPriest;
	}
	
	public boolean losePriest(){
		if(nbPriest>0){
			nbPriest--;
			Box.getInstance().addPriest();
			return true;
		}
		return false;
	}
	
	
}
