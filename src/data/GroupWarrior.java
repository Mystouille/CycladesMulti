package data;

public class GroupWarrior extends Group{
	public int nbWarrior;
	public CellIsland location;

	
	public GroupWarrior(int i, CellIsland island) {
		nbWarrior=i;
		location=island;
	}
}
