package SearchGrid;

import LabNotes.*;
import java.io.*;
import java.util.*;

public class BFSGrid {

    private static final int RIGHT = 1;
    private static final int LEFT = -1;
    private static final int UP = -1;
    private static final int DOWN = 1;
    private static final int NO_MOVE = 0;
    private static final int DIRECTIONS = 4;
    private static int[] axisX = {RIGHT,LEFT,NO_MOVE,NO_MOVE};
    private static int[] axisY = {NO_MOVE,NO_MOVE,DOWN,UP};
    private Grid grid;

    public BFSGrid(Grid grid){
        this.grid = grid;
    }

    private boolean isInBoundsX(int idx){
        return idx >= 0 && idx < grid.getNumOfRows();
    }

    private boolean isInBoundsY(int idx){
        return idx >= 0 && idx < grid.getNumOfColumns();
    }

    public int[] BFSearch(){

        int[] returnValue = {0,-1};
        Queue <Node> queue = new LinkedList<>();

        Node start = new Node(grid.getStart()[0],grid.getStart()[1],false,1);
        Node finish = new Node(grid.getTerminal()[0],grid.getTerminal()[1],false,1);

        if(start == finish){
            returnValue[0] = 1;
            returnValue[1] = 1;
            return returnValue;
        }

        queue.add(start);
        start.setVisited(true);
        grid.getCell(start.getX(),start.getY()).setVisited(true);

        while(queue.peek() != null){
            Node current = queue.poll();
            for(int i=0; i<DIRECTIONS; i++){
                /* This is a check for valid neighbor as regards the size of grid */
                if(isInBoundsX(current.getX() + axisX[i]) && isInBoundsY(current.getY() + axisY[i])){
                    int nx = current.getX() + axisX[i];
                    int ny = current.getY() + axisY[i];
                    boolean nVisited = grid.getCell(nx,ny).isVisited();
                    /* Check if node visited and if node is wall */
                    if((!nVisited) && (!grid.getCell(nx,ny).isWall())){
                        if(grid.getCell(nx,ny).isTerminal()){ // Destination Found
                            returnValue[0] = 1;
                            returnValue[1] = current.getPath_till_cost() + 1;
                            return returnValue;
                        }
                        else if(grid.getCell(nx,ny).isGrass()){
                            grid.getCell(nx,ny).setVisited(true);
                            current.setVisited(true);
                            Node temp = new Node(nx,ny,true,current.getPath_till_cost() + grid.getGrass_cost());
                            queue.add(temp);
                        }
                        else if(grid.getCell(nx,ny).isLand()){
                            grid.getCell(nx,ny).setVisited(true);
                            current.setVisited(true);
                            Node temp = new Node(nx,ny,true,current.getPath_till_cost() + grid.getLand_cost());
                            queue.add(temp);
                        }
                    }
                }
            }
        }
        return returnValue;
    }
}
