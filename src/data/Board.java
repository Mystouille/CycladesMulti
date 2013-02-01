package data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;



public class Board {
	private static Board instance;
	
	public static enum Creature {
	    SIREN,PEGASUS,GIANT,CHIMERA,CYCLOPS,SPHINX,SYLPH,HARPY,GRIFFON,FATES,SATYR,DRYAD,KRAKEN,MINOTAUR,CHIRON,MEDUSA,POLYPHEMUS
	}
	
	public Stack<Creature> stack;
	public Stack<Creature> discard;
	public Creature[] creatureSlots;
	
	public Stack<Player> pions;
	public Gods gods;
	public static int globalWidth=6;
	public static int globalHeight=11;
	public static int bigHalf=6;
	public static int smallHalf=5;
	private static Player[] staticPlayers;
	private static int staticNbPlayer;
	
	public ArrayList<Cell> routes;
	public ArrayList<Cell> map;
	public ArrayList<CellIsland> islands;
	public ArrayList<GroupShip> fleets;
	public ArrayList<GroupWarrior> armies;
	public int sizePenalty; //Decreased width of the board (depends on player number)
	public static int nbPlayer; //number of players playing the game
	public Player[] players; //List of the players
	
	public static void init(Player[] players2){
		staticNbPlayer=players2.length;
		staticPlayers=players2;
		instance=null;
	}
	
	
	public static Board getInstance(){
		if(instance==null){
			instance=new Board();
		}
		return instance;
	}
	
	private Board(){
		nbPlayer=staticNbPlayer;
		players=staticPlayers;
		
		//Piste de tour
		pions=new Stack<Player>();
		
		
		//Piste des créatures
		stack=new Stack<Board.Creature>();
		discard=new Stack<Board.Creature>();
		creatureSlots=new Creature[3];
		for(Creature c: Creature.values()){
			stack.add(c);
		}
		Collections.shuffle(stack);
		
		//Piste des dieux
		gods=new Gods(nbPlayer);
		
		//Cyclades
		routes=new ArrayList<Cell>();
		map=new ArrayList<Cell>();
		islands= new ArrayList<CellIsland>();
		fleets= new ArrayList<GroupShip>();
		armies= new ArrayList<GroupWarrior>();
		

		setPenalty();
		fillOcean();
		setCommercialRoutes();
		dropIslands();
		installPlayers();
		
		
		
	}
	
	
	/*
	 *======================================================
	 *========== INITIALISING METHODS ======================
	 *====================================================== 
	 */
	
	private void installPlayers() {
		Player p;
		switch(nbPlayer){
			case 2:
				//P1
				p=players[0];
				pions.add(p);
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(8, 3)));
				addWarrior(p, map.get(getCellIndex(1, 0)));
				addShip(p,map.get(getCellIndex(6,4)));
				addShip(p,map.get(getCellIndex(1,1)));
				//P2
				p=players[1];				
				pions.add(p);
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(2, 2)));
				addWarrior(p, map.get(getCellIndex(8, 0)));
				addShip(p,map.get(getCellIndex(3,3)));
				addShip(p,map.get(getCellIndex(5,0)));
				break;
				
			case 3:
				//P1
				p=players[0];
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(8, 3)));
				addWarrior(p, map.get(getCellIndex(1, 0)));
				addShip(p,map.get(getCellIndex(6,4)));
				addShip(p,map.get(getCellIndex(2,1)));
				//P2
				p=players[1];
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(2, 2)));
				addWarrior(p, map.get(getCellIndex(5, 1)));
				addShip(p,map.get(getCellIndex(3,3)));
				addShip(p,map.get(getCellIndex(5,0)));
				//P3
				p=players[2];
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(3,1)));
				addWarrior(p, map.get(getCellIndex(8,0)));
				addShip(p,map.get(getCellIndex(10,1)));
				addShip(p,map.get(getCellIndex(5,3)));
				break;
			case 4:
				//P1
				p=players[0];
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(3,3)));
				addWarrior(p, map.get(getCellIndex(8,2)));
				addShip(p,map.get(getCellIndex(5,4)));
				addShip(p,map.get(getCellIndex(10,0)));
				//P2
				p=players[1];
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(3,4)));
				addWarrior(p, map.get(getCellIndex(6,0)));
				addShip(p,map.get(getCellIndex(4,5)));
				addShip(p,map.get(getCellIndex(5,0)));
				//P3
				p=players[2];
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(4,1)));
				addWarrior(p, map.get(getCellIndex(6,7)));
				addShip(p,map.get(getCellIndex(5,2)));
				addShip(p,map.get(getCellIndex(8,4)));
				//P4
				p=players[3];
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(1,0)));
				addWarrior(p, map.get(getCellIndex(5,6)));
				addShip(p,map.get(getCellIndex(1,2)));
				addShip(p,map.get(getCellIndex(6,5)));
				break;
			case 5:
				//P1
				p=players[0];
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(4,1)));
				addWarrior(p, map.get(getCellIndex(6,9)));
				addShip(p,map.get(getCellIndex(5,2)));
				addShip(p,map.get(getCellIndex(6,8)));
				//P2
				p=players[1];
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(4,4)));
				addWarrior(p, map.get(getCellIndex(8,4)));
				addShip(p,map.get(getCellIndex(5,4)));
				addShip(p,map.get(getCellIndex(10,5)));
				//P3
				p=players[2];
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(6,0)));
				addWarrior(p, map.get(getCellIndex(3,8)));
				addShip(p,map.get(getCellIndex(5,0)));
				addShip(p,map.get(getCellIndex(3,7)));
				//P4
				p=players[3];
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(1,0)));
				addWarrior(p, map.get(getCellIndex(5,6)));
				addShip(p,map.get(getCellIndex(1,2)));
				addShip(p,map.get(getCellIndex(6,5)));
				//P5
				p=players[4];
				pions.add(p);
				addWarrior(p, map.get(getCellIndex(0,4)));
				addWarrior(p, map.get(getCellIndex(9,1)));
				addShip(p,map.get(getCellIndex(2,5)));
				addShip(p,map.get(getCellIndex(10,0)));
				break;
		}
		Collections.shuffle(pions);
	}


	private void fillOcean() {
		//Filling the ocean
		map=new ArrayList<Cell>();
		Cell current;
		int nbCell= 2*(6+7+8+9+10)+11;
		//Creation des cells
		for(int h=0;h<globalHeight;h++){
			for(int l=0;l<globalHeight-sizePenalty-Math.abs(h-5);l++){
				map.add(new Cell());
			}
		}
		
		//Liaison des voisins, on part du coin nord ouest vers le coin nord est, en reprenant au début ouest de la ligne en dessous à chaque fois
		int i=0;
		int lMax;
		//Partie Supérieure
		for(int h=0;h<bigHalf;h++){
			lMax=globalHeight-sizePenalty-Math.abs(h-5)-1;
			for(int l=0;l<=lMax;l++){
				//N
				if(h!=0){
					//NE
					if(l!=0){
						map.get(i).setNeighbor(map.get(getCellIndex(h-1,l-1)), Cell.DIR_NE);
					}
					//NW
					if(l!=lMax){
						map.get(i).setNeighbor(map.get(getCellIndex(h-1,l)), Cell.DIR_NW);
					}
				}
				//E
				if(l!=0){
					map.get(i).setNeighbor(map.get(i-1), Cell.DIR_E);
				}
				//W
				if(l!=lMax){
					map.get(i).setNeighbor(map.get(i=1), Cell.DIR_W);
				}
				//SE
				if((l!=lMax)||(h<bigHalf-1)){
					map.get(i).setNeighbor(map.get(getCellIndex(h+1,l+1)), Cell.DIR_SE);
				}
				//SW
				if((l!=0)||(h<bigHalf-1)){
					map.get(i).setNeighbor(map.get(getCellIndex(h+1,l)), Cell.DIR_SW);
				}
				i++;
			}
		}
		//Partie Inférieure
		for(int h=bigHalf;h<globalHeight;h++){
			lMax=globalHeight-sizePenalty-Math.abs(h-5)-1;
			for(int l=0;l<=lMax;l++){
				//NE
				map.get(i).setNeighbor(map.get(getCellIndex(h-1,l-1)), Cell.DIR_NE);
				//NW
				map.get(i).setNeighbor(map.get(getCellIndex(h-1,l)), Cell.DIR_NW);
				//E
				if(l!=0){
					map.get(i).setNeighbor(map.get(i-1), Cell.DIR_E);
				}
				//W
				if(l!=lMax){
					map.get(i).setNeighbor(map.get(i=1), Cell.DIR_W);
				}
				//S
				if(h<globalHeight-1){
					//SE
					if(l!=lMax){
						map.get(i).setNeighbor(map.get(getCellIndex(h+1,l)), Cell.DIR_SE);
					}
					//SW
					if(l!=0){
						map.get(i).setNeighbor(map.get(getCellIndex(h+1,l-1)), Cell.DIR_SW);
					}
				}
				i++;
			}
		}
	}
	
	private static int checkWinner() {
		int money=0;
		int winner=-1;
		for(int i=0; i<nbPlayer;i++){
			if(Board.getInstance().players[i].nbBuilding[0]>=2){
				if(Board.getInstance().players[i].nbCoin>money){
					winner=i;
					money=Board.getInstance().players[i].nbCoin;
				}
			}
		}
		return winner;
	}
	
	private void setCommercialRoutes() {
		if(sizePenalty<4){
			routes.add(map.get(getCellIndex(0, 0)));
			routes.add(map.get(getCellIndex(Board.globalHeight-1, 0)));
		}
		routes.add(map.get(getCellIndex(0, Board.globalWidth-1-sizePenalty)));
		routes.add(map.get(getCellIndex(Board.globalWidth-1, 0)));
		routes.add(map.get(getCellIndex(Board.globalWidth-1,  Board.globalHeight-1-sizePenalty)));
		routes.add(map.get(getCellIndex(Board.globalHeight-1,  Board.globalWidth-1-sizePenalty)));
		for(Cell c:routes){
			c.setCommercialRoute();
		}
	}
	
	private void dropIslands() {
		ArrayList<int[]> islands=new ArrayList<int[]>();
		switch(sizePenalty){
			case 4: 
				islands.add(new int[]{1,0,2,2});
				islands.add(new int[]{2,0,1,7,8});
				islands.add(new int[]{2,0,1,10,16});
				islands.add(new int[]{1,0,2,18});
				islands.add(new int[]{3,1,0,21,27,28});
				islands.add(new int[]{1,0,2,30});
				islands.add(new int[]{4,2,0,32,37,41,44});
				islands.add(new int[]{3,1,0,38,42,45});
			break;
			case 2: 
				islands.add(new int[]{4,2,0,4,5,9,10});
				islands.add(new int[]{2,0,1,13,14});
				islands.add(new int[]{2,0,1,18,26});
				islands.add(new int[]{1,0,2,23});
				islands.add(new int[]{1,0,2,28});
				islands.add(new int[]{2,0,1,32,41});
				islands.add(new int[]{3,1,0,38,46,47});
				islands.add(new int[]{4,2,0,45,52,58,63});
				islands.add(new int[]{2,1,0,43});
				islands.add(new int[]{3,1,0,55,60,61});
			break;
			case 0:	
				islands.add(new int[]{2,0,1,3,10});
				islands.add(new int[]{4,2,0,6,7,13,14});
				islands.add(new int[]{2,0,1,24,34});
				islands.add(new int[]{2,0,1,26,27});
				islands.add(new int[]{2,0,1,29,38});
				islands.add(new int[]{1,0,2,31});
				islands.add(new int[]{2,0,1,43,54});
				islands.add(new int[]{1,0,2,46});
				islands.add(new int[]{3,1,0,51,61,62});
				islands.add(new int[]{4,2,0,60,69,77,84});
				islands.add(new int[]{1,0,2,67});
				islands.add(new int[]{3,1,0,72,79,80});
				islands.add(new int[]{3,1,0,74,82,89});
			break;
		default:System.err.println("wrong penalty");
		}
		raiseIslands(islands);
	}
	
	private void setPenalty() {
		switch(nbPlayer){
			case 2: sizePenalty=4;
			case 3: sizePenalty=4;
			case 4: sizePenalty=2;
			case 5: sizePenalty=0;
		}
	}


	public boolean moveArmy(Player player, Cell source, Cell target){
		return moveArmy(player,source,target,0);
	}
	public boolean moveArmy(Player player, Cell sourceCell, Cell targetCell, int numberStaying){
		GroupWarrior toMerge=null;
		CellIsland source=getIsland(sourceCell);
		CellIsland target=getIsland(targetCell);
		if(!targetCell.acceptShip){
			return false;
		}
		for(GroupWarrior army:armies){
			if((army.location == source)&&
					(player == army.owner)&&
					(numberStaying < army.nbWarrior)&&
					(checkLink(player,source,target))){
				for(GroupWarrior army2:armies){
					if((army2.location==target)&&
							(player==army2.owner)){
						toMerge=army2;
						break;
					}		
				}
				if(numberStaying > 0){
					if(toMerge==null){
						armies.add(new GroupWarrior(army.nbWarrior-numberStaying,target));
					}
					else{
						toMerge.nbWarrior+=army.nbWarrior-numberStaying;
					}
					army.nbWarrior=numberStaying;
				}
				else{
					army.location=target;
					source.owner=null;
				}
				target.owner=player;
				return true;
			}
		}
		return false;
	}
		
	
	public boolean moveFleet(Player player, Cell source, Cell target){
		return moveFleet(player, source,target,0);
	}
	public boolean moveFleet(Player player, Cell source, Cell target, int numberStaying){
		GroupShip toMerge=null;
		for(GroupShip fleet:fleets){
			if((fleet.location==source)&&
					(player==fleet.owner)&&
					(target.acceptShip)&&
					(source.neighbors.contains(target))){
				
				int f=0;
				boolean found=false;
				while(f<fleets.size()&&!found){
					if(fleets.get(f).location==target){
						if(player==fleets.get(f).owner){
							toMerge=fleets.get(f);
							found=true;
						}
						
					}	
					f++;
				}
				for(GroupShip fleet2:fleets){
					if((fleet2.location==target)&&
							(player==fleet2.owner)){
						toMerge=fleet2;
						break;
					}		
				}
				if(numberStaying > 0){
					if(toMerge==null){
						fleets.add(new GroupShip(fleet.nbShip-numberStaying,target));
					}
					else{
						toMerge.nbShip+=fleet.nbShip-numberStaying;
					}
					fleet.nbShip=numberStaying;
					
				}
				else{
					fleet.location=target;
					source.owner=null;
				}
				
				target.owner=player;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkLink(Player player, CellIsland source, CellIsland target) {
		ArrayList<Cell> firstNeighbors=source.getNeighbors();
		ArrayList<Cell> used=new ArrayList<Cell>();
		try{
			for(GroupShip fleet:fleets){
				if(firstNeighbors.contains(fleet.location)){
					checkLink(player, fleet.location, target.getNeighbors(), used);
				}
			}
		}
		catch(LinkFoundException e){
			return true;
		}
		return false;
	}
		
	private void checkLink(Player player, Cell source, ArrayList<Cell> targets, ArrayList<Cell> usedOld) throws LinkFoundException {
		ArrayList<Cell> used=new ArrayList<Cell>();
		Collections.copy(used, usedOld);
		if(targets.contains(source)){
			throw new LinkFoundException();
		}
		for(GroupShip fleet:fleets){
			if((!used.contains(fleet.location)) && source.neighbors.contains(fleet.location)){
				checkLink(player, fleet.location, targets, used);
			}
		}
	}




	public void raiseIslands(ArrayList<int[]> list) {
		islands=new ArrayList<CellIsland>();
		CellIsland newIsland;
		Cell currentCell;
		for(int i=0;i<list.size();i++){
			newIsland=new CellIsland();
			for(int c=2;c<list.get(i).length;c++){
				currentCell=map.get(list.get(i)[c]);
				currentCell.MorphToLand();
				newIsland.cells.add(currentCell);
			}
			newIsland.finalize(new int[]{list.get(i)[0],list.get(i)[1],list.get(i)[2]});
			islands.add(newIsland);
		}
	}
	
	private CellIsland getIsland(Cell c) {
		for(CellIsland island:islands){
			if(island.cells.contains(c)){
				return island;
			}
		}
		return null;
	}
	
	public int getCellIndex(int hM, int lM){
		int i=0;
		for(int h=0;h<hM;h++){
			for(int l=0;l<globalHeight-sizePenalty-Math.abs(h-5);l++){
				i++;
			}
		}
		return i+lM;
	}

	
	public boolean addWarrior(Player p, Cell cell){
		if(!cell.acceptWarrior){
			return false;
		}
		return addWarrior(p, getIsland(cell));
	}
	
	public boolean addWarrior(Player p, CellIsland island) {
		if((p.nbWarrior==0)||(island.owner!=p)){
			return false;
		}
		int a=0;
		boolean found=false;
		while(a<armies.size()&&(!found)){
			if(armies.get(a).location==island){
				armies.get(a).nbWarrior++;
				found=true;
			}
		}
		if(!found){
			armies.add(new GroupWarrior(1, island));
		}
		p.nbWarrior--;
		return true;
	}
	
	public boolean addShip(Player p, Cell cell){
		if((!cell.acceptShip)||(p.nbShip==0)||((cell.owner!=null)&&(cell.owner!=p))){
			return false;
		}
		int f=0;
		boolean found=false;
		while(f<fleets.size()&&(!found)){
			if(fleets.get(f).location==cell){
				fleets.get(f).nbShip++;
				found=true;
			}
			f++;
		}
		if(!found){
			fleets.add(new GroupShip(1,cell));
			cell.owner=p;
		}
		p.nbShip--;
		return true;
	}
	
	
	public void mergeAndShuffle(){
		stack.addAll(discard);
		discard.clear();
		Collections.shuffle(stack);
	}
	
	public boolean discard(Player p, int slot){
		if((p.nbCoin==0)||(creatureSlots[slot]==null)){
			return false;
		}
		discard.add(creatureSlots[slot]);
		creatureSlots[slot]=stack.pop();
		p.spendCoin(1);
		return true;
	}
	
	public boolean PowerSiren(Player source, Cell target){
		int f=0;
		boolean found=false;
		while(f<fleets.size()&&!found){
			if((fleets.get(f).location==target)&&
					(source!=fleets.get(f).owner)&&
					(fleets.get(f).nbShip==1)){
				fleets.remove(fleets.get(f));
				fleets.add(new GroupShip(1, target));
				target.owner=source;
				found=true;
			}	
			f++;
		}
		return found;
	}
	
	//Si il n'y a plus de flotte dispo en reserve du joueur
	public boolean PowerSiren(Player source,Cell sourceCell, Cell target){
		int f=0;
		boolean found=false;
		//on retire une flotte d'une case
		while(f<fleets.size()&&!found){
			if((fleets.get(f).location==sourceCell)&&
					(source==fleets.get(f).owner)&&
					(fleets.get(f).nbShip>=1)){
				if(fleets.get(f).nbShip>1){
					fleets.get(f).nbShip--;
				}
				else{
					fleets.remove(fleets.get(f));
					sourceCell.owner=null;
				}
				found=true;
			}	
			f++;
		}
		if(!found){
			return false;
		}
		f=0;
		found=false;
		while(f<fleets.size()&&!found){
			if((fleets.get(f).location==target)&&
					(source!=fleets.get(f).owner)&&
					(fleets.get(f).nbShip==1)){
				fleets.remove(fleets.get(f));
				addShip(source, target);
				found=true;
			}	
			f++;
		}
		return found;
	}
	
	public boolean powerPegasus(Player p, GroupWarrior source,int n, CellIsland target){
		if((source.nbWarrior<n)||(source.owner!=p)||(!checkVictoryConditions(p, target))){
			return false;
		}
		if(target.owner==p){
			if(source.nbWarrior==n){
				armies.remove(source);
			}
			int a=0;
			boolean found=false;
			while(a<armies.size()&&(!found)){
				if((armies.get(a).location==target)&&
					(armies.get(a).owner==p)){
					armies.get(a).nbWarrior+=n;
					found=true;
				}
			}
			if(!found){
				armies.add(new GroupWarrior(n, target));
			}
			
		}
		
		return true;
	}
	
	public boolean checkVictoryConditions(Player source, CellIsland target){
		int requirement=nbPlayer>2?3:2;
		int[] checkList=new int[5];
		if((getNbIslandOwned(target.owner)>1)||(target.owner==source)){
			return true;
		}
		for(int i=0;i<5;i++){
			checkList[i]+=source.nbBuilding[i];
			checkList[i]+=target.owner.nbBuilding[i];
		}
		return (checkList[0]+Math.min(checkList[1], Math.min(checkList[2], Math.min(checkList[3],checkList[4]))))>=requirement;
	}


	public int getNbIslandOwned(Player owner) {
		int a=0;
		for(CellIsland i:islands){
			if(owner==i.owner){
				a++;
			}
		}
		return a;
	}
	
	public int getNbHorn(Player owner) {
		int a=0;
		for(CellIsland i:islands){
			if(owner==i.owner){
				a+=i.getNbHorns();
			}
		}
		for(GroupShip f:fleets){
			if(owner==f.owner&&f.location.isCommercialRoute()){
				a++;
			}
		}
		return a;
	}
}
