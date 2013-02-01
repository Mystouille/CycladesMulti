package data;

public class GroupShip extends Group{
	public int nbShip;
	public Cell location;
	
	public GroupShip(int i, Cell target) {
		if(i>0){
			nbShip=i;
			location=target;
			location.owner=this.owner;
		}
		else{
			System.err.println("empty fleet created");
		}
	}
}
