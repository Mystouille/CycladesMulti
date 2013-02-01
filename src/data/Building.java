package data;

public abstract class Building {
	public final static int BUILDING_CITY=0;
	public final static int BUILDING_TEMPLE=1;
	public final static int BUILDING_FORTRESS=2;
	public final static int BUILDING_HARBOR=3;
	public final static int BUILDING_UNIVERSITY=4;
	
	public int type;
	public int defOcean;
	public int defIsland;
	public int bonusSummon;
	public int isUniversity;
	
	public static Building getNewBuildingOfType(int type){
		switch(type){
		case 0: return new BuildingCity();
		case 1: return new BuildingTemple();
		case 2: return new BuildingFortress();
		case 3: return new BuildingHarbor();
		case 4: return new BuildingUniversity();
		default: return null;
		}
	}
	
}
