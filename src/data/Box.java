package data;

public class Box {
	private static Box instance;
	private int[] nbBuilding;
	private int nbCoin;
	private int nbHorn;
	private int nbPhilosopher;
	private int nbPriest;
	
	public static Box getInstance(){
		if (instance==null){
			instance=new Box();
		}
		return instance;
	}
	
	private Box(){
		nbBuilding=new int[5];
		for(int i=0;i<5;i++){
			nbBuilding[i]=10;
		}
		nbHorn=16;
		nbCoin=98;
		nbPhilosopher=17;
		nbPriest=18;
	}
	
	protected int removeCoin(int n){
		if(n<=nbCoin){
			nbCoin=nbCoin-n;
			return n;
		}
		else{
			int a=nbCoin;
			nbCoin=0;
			return a;
		}
	}
	
	protected void addCoin(int n){
		nbCoin=nbCoin+n;
	}
	
	protected boolean removeHorn(){
		if(0<nbHorn){
			nbHorn=nbHorn-1;
			return true;
		}
		else{
			return false;
		}
	}
	
	protected void addPriest(){
		nbPriest++;
	}
	
	protected boolean removePriest(){
		if(0<nbPriest){
			nbPriest=nbPriest-1;
			return true;
		}
		else{
			return false;
		}
	}
	
	protected void addPhilosopher(){
		nbPhilosopher++;
	}
	
	protected boolean removePhilosopher(){
		if(0<nbPhilosopher){
			nbPhilosopher=nbPhilosopher-1;
			return true;
		}
		else{
			return false;
		}
	}
	
	protected boolean removeBuilding(int type){
		if(0<nbBuilding[type]){
			nbBuilding[type]=nbBuilding[type]-1;
			return true;
		}
		else{
			return false;
		}
	}
	
	protected void addBuilding(int type){
		nbBuilding[type]=nbBuilding[type]+1;
	}
}
