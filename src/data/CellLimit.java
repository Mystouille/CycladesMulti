package data;

import java.util.ArrayList;

public class CellLimit extends Cell{
	private static CellLimit instance=null;
	
	public final boolean acceptShip=false;
	public final boolean acceptWarrior=false;
	protected static final ArrayList<Cell> neighbors=null;
	
	
	public static CellLimit getInstance(){
		if(instance==null){
			instance= new CellLimit();
		}
		return instance;
	}
	
	private CellLimit(){}
}
