package data;
import java.util.ArrayList;


public class Cell {
	public static final int DIR_NW=0;
	public static final int DIR_NE=1;
	public static final int DIR_E=2;
	public static final int DIR_SE=3;
	public static final int DIR_SW=4;
	public static final int DIR_W=5;
	
	public Player owner;
	public boolean acceptShip;
	public boolean acceptWarrior;
	private boolean commercialRoute;
	
	protected ArrayList<Cell> neighbors;
	

	
	public Cell(){
		if(!(this instanceof CellLimit)){
			acceptShip=true;
			acceptWarrior=false;
			commercialRoute=false;
			neighbors=new ArrayList<Cell>();
			for(int i=0;i<6;i++){
				neighbors.add(CellLimit.getInstance());
			}
		}
	}
	
	public void setNeighbor(Cell c,int dir){
		neighbors.remove(dir);
		neighbors.add(dir, c);
	}
	
	public void MorphToLand(){
		acceptShip=false;
		acceptWarrior=true;
	}
	
	public void setCommercialRoute(){
		commercialRoute=true;
	}
	
	public boolean isCommercialRoute(){
		return commercialRoute;
	}
	
	
	
	
	
}
