/**
 INTELLIGENCE LAB
 Course		: 	COMP 417 - Artificial Intelligence
 Authors	:	A. Georgara, A. Vogiatzis
 Excercise	:	1st Programming
 Term 		: 	Spring 2018-2019
 Date 		:   March 2019
 **/
package LabNotes;
public class Cell {

	private int cost=1;
	private boolean starting_point;
	private boolean terminal_point;
	private char cell_type = 'L'; // l stands for Land
	private boolean visited;

	Cell (){
		this.starting_point = false;
		this.terminal_point = false;
	}

	Cell(char cell_type, boolean starting_point, boolean terminal_point, int world_cost){
		if(cell_type!= 'L' && cell_type!= 'W' && cell_type!= 'G'){
			System.out.println("Unknown type of cell. This cell is set to Land!");
			cell_type='L';
			world_cost = 1;
		}
		this.cell_type = cell_type;
		this.starting_point = starting_point;
		this.terminal_point = terminal_point;
		this.cost = world_cost;
	}

	public boolean isWall(){return  (this.cell_type=='W');}

	public boolean isGrass(){return  (this.cell_type=='G');}

	public boolean isLand(){return (this.cell_type=='L');}

	public boolean isStart(){return this.starting_point;}

	public boolean isTerminal(){return this.terminal_point;}

	public int getCost(){return this.cost;}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void changeCellType(char cell_type, int world_cost){
		if(cell_type!= 'L' && cell_type!= 'W' && cell_type!= 'G'){
			System.out.println("Unknown type of cell. This cell is set to Land!");
			cell_type='L';
			world_cost = 1;
		}
		this.cell_type = cell_type;
		this.cost = world_cost;
	}

	public char getCellType(){return this.cell_type;}

	public void setStartingPoint(boolean sp){this.starting_point=sp;}

	public void setTerminalPoint(boolean sp){this.terminal_point=sp;}
}