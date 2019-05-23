package SearchGrid;

import LabNotes.Grid;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class DFSGrid {

    private static final int RIGHT = 1;
    private static final int LEFT = -1;
    private static final int UP = -1;
    private static final int DOWN = 1;
    private Grid grid;

    public DFSGrid(Grid grid){
        this.grid = grid;
    }

    private boolean isInBoundsX(int idx){
        return idx >= 0 && idx < grid.getNumOfRows();
    }

    private boolean isInBoundsY(int idx){
        return idx >= 0 && idx < grid.getNumOfColumns();
    }

    public int[] DFSearch(){

        int[] returnValue = {0,-1};
        Stack<Node> stack = new Stack<>();

        Node start = new Node(grid.getStart()[0],grid.getStart()[1],false,0);
        Node finish = new Node(grid.getTerminal()[0],grid.getTerminal()[1],false,1);

        if(start == finish){
            returnValue[0] = 0;
            returnValue[1] = 1;
            return returnValue;
        }

        stack.add(start);
        start.setVisited(true);
        grid.getCell(start.getX(),start.getY()).setVisited(true);

        while (!stack.isEmpty()){
            Node current = stack.pop();
            current.setVisited(true);

            if(grid.getCell(current.getX(),current.getY()).isTerminal()) {
                returnValue[0] = 1;
                returnValue[1] = current.getPath_till_cost() + 1;
                return returnValue;
            }
            else if(grid.getCell(current.getX(),current.getY()).isVisited()){
                if(grid.getCell(current.getX(),current.getY()).isLand()){
                    current.setPath_till_cost(current.getPath_till_cost() - grid.getLand_cost());
                }
                else if(grid.getCell(current.getX(),current.getY()).isGrass()){
                    current.setPath_till_cost(current.getPath_till_cost() - grid.getGrass_cost());
                }
            }
            else{
                grid.getCell(current.getX(),current.getY()).setVisited(true);
                if(grid.getCell(current.getX(),current.getY()).isLand()){
                    current.setPath_till_cost(current.getPath_till_cost() + grid.getLand_cost());
                    //System.out.println("\n Cell:" + current.getX() + current.getY());
                }
                else if(grid.getCell(current.getX(),current.getY()).isGrass()){
                    current.setPath_till_cost(current.getPath_till_cost() + grid.getGrass_cost());
                    //System.out.println("\n Cell:" + current.getX() + current.getY());
                }
            }

            for(Node node : this.getAdjacentEdges(current)) {
                if(!node.isVisited()) {
                    stack.push(node);
                }
            }
        }

        return returnValue;

    }

    private List<Node> getAdjacentEdges(Node node) {

        List<Node> neighbours = new ArrayList<>();

        if(isInBoundsX(node.getX() + LEFT )){
            if(!grid.getCell(node.getX() + LEFT,node.getY()).isWall()){
                boolean visited = grid.getCell(node.getX() + LEFT,node.getY()).isVisited();
                if(!visited) neighbours.add(new Node(node.getX() + LEFT, node.getY(),false,node.getPath_till_cost()));
            }
        }

        if(isInBoundsY(node.getY() + DOWN )){
            if(!grid.getCell(node.getX(),node.getY() + DOWN).isWall()){
                boolean visited = grid.getCell(node.getX(),node.getY() + DOWN).isVisited();
                if(!visited) neighbours.add(new Node(node.getX(), node.getY() + DOWN,false,node.getPath_till_cost()));
            }
        }

        if(isInBoundsX(node.getX() + RIGHT )){
            if(!grid.getCell(node.getX() + RIGHT,node.getY()).isWall()){
                boolean visited = grid.getCell(node.getX() + RIGHT,node.getY()).isVisited();
                if(!visited) neighbours.add(new Node(node.getX() + RIGHT, node.getY(),false,node.getPath_till_cost()));
            }
        }

        if(isInBoundsY(node.getY() + UP )){
            if(!grid.getCell(node.getX(),node.getY() + UP).isWall()){
                boolean visited = grid.getCell(node.getX(),node.getY() + UP).isVisited();
                if(!visited) neighbours.add(new Node(node.getX(), node.getY() + UP,false,node.getPath_till_cost()));
            }
        }
        return neighbours;
    }

}
