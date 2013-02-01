package data;
import java.util.ArrayList;


public class CellIsland{
	
	
 public ArrayList<Cell> cells;
 private ArrayList<Building> buildings;
 private int nbSlots;
 private int nbSlotsWithCity;
 private int nbHorns;
 public Player owner;
 
 public CellIsland(){
	 cells=new ArrayList<Cell>();
	 buildings= new ArrayList<Building>();
 }
 
 
 public int getNbSlots() {
	return nbSlots;
}
public int getNbSlotsWithCity() {
	return nbSlotsWithCity;
}
public int getNbHorns() {
	return nbHorns;
}


public ArrayList<Cell> getNeighbors(){
	 ArrayList<Cell> neighborList=new ArrayList<Cell>();
	 for(Cell i:cells){
		 for(Cell o:i.neighbors){
			 if((o!=CellLimit.getInstance()) && (!o.acceptWarrior)&&(!neighborList.contains(o))){
				 neighborList.add(o);
			 }
		 }
	 }
	 return neighborList;
 }
 
 public boolean build(int type){
	 Building newB=Building.getNewBuildingOfType(type);
	 if(type==Building.BUILDING_CITY){
		 for(Building b:buildings){
			 if(b.type==Building.BUILDING_CITY){
				 return false;
			 }
		 }
		 if(nbSlotsWithCity>=buildings.size()){
			 buildings.add(newB);
			 return true;
		 }
		 else{
			 return false;
		 } 
	 }
	 else{
		 if(nbSlots>buildings.size()){
			 buildings.add(newB);
			 return true;
		 }
		 else{
			 return false;
		 }
	 }
 }
 
 public boolean demolish(int type){
	 for(Building b:buildings){
		 if(b.type==type){
			 buildings.remove(b);
			 return true;
		 }
	 }
	 return false;
	 
 }
 
 public void addHorn(){
	 nbHorns+=1;
 }

public void finalize(int[] param) {
	nbSlots=param[0];
	nbSlotsWithCity=param[1];
	nbHorns=param[2];
}


 
}
