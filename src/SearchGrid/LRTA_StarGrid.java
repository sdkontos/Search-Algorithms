package SearchGrid;

import LabNotes.*;

import java.util.ArrayList;
import java.util.List;


public class LRTA_StarGrid {

    private Node[][] searchArea;
    private static final int RIGHT = 1;
    private static final int LEFT = -1;
    private static final int UP = -1;
    private static final int DOWN = 1;
    private Node node = null;
    private Node initialNode;
    private Node finalNode;
    private Grid grid;

    public LRTA_StarGrid(Grid grid) {
        this.grid = grid;
        this.searchArea = new Node[grid.getNumOfRows()][grid.getNumOfColumns()];
        initialNode = new Node(grid.getStart()[0],grid.getStart()[1]);
        initialNode.setStarting(true);
        finalNode = new Node(grid.getTerminal()[0],grid.getTerminal()[1]);
        finalNode.setTerminal(true);
        setNodes();
    }

    private void setNodes() {

        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {

                Node node = new Node(i,j);
                node.calculateHeuristic(getFinalNode());

                if(grid.getCell(i,j).isStart()) node.setStarting(true);
                    node.setPath_till_cost(node.getPath_till_cost() + grid.getLand_cost());
                if(grid.getCell(i,j).isTerminal()) node.setTerminal(true);

                if(grid.getCell(i,j).isWall()) node.setWall(true);
                else if(grid.getCell(i,j).isGrass()) node.setGrass(true);
                else if(grid.getCell(i,j).isLand()) node.setLand(true);

                this.searchArea[i][j] = node;
            }
        }
    }

    private boolean isInBoundsX(int idx){ return idx >= 0 && idx < grid.getNumOfRows(); }

    private boolean isInBoundsY(int idx){ return idx >= 0 && idx < grid.getNumOfColumns(); }

    public int[] LRTA_StarSearch() {

        int[] returnValue = {0,-1};
        Node currentNode = initialNode;

        while(currentNode != null){

            if (currentNode.isTerminal()){
                returnValue[0] = 1;
                returnValue[1] = currentNode.getPath_till_cost();
                return returnValue;
            }

            if (currentNode.getH() == 0) currentNode.setH(currentNode.calculateHeuristic(getFinalNode()));

            if (!currentNode.isStarting()){
                int minFValue = Integer.MAX_VALUE;
                List<Node> adjacentNodes = getValidAdjacents(node);
                for (Node n : adjacentNodes) {
                    int fValue = getLRTACost(n);
                    if (fValue < minFValue){
                        minFValue = fValue;
                    }
                }
                node.setH(minFValue);
            }

            int minFValue = Integer.MAX_VALUE;
            Node nextNode = null;
            List<Node> adjacentNodes = getValidAdjacents(currentNode);

            for (Node n : adjacentNodes){
                int fValue = getLRTACost(n);
                n.setVisited(true);
                if (fValue < minFValue){
                    minFValue = fValue;
                    nextNode = n;
                }
            }

            if (nextNode.isGrass())
                nextNode.setPath_till_cost(currentNode.getPath_till_cost() + grid.getGrass_cost());
            else
                nextNode.setPath_till_cost(currentNode.getPath_till_cost() + grid.getLand_cost());

            node = currentNode;
            currentNode = nextNode;
        }
        return returnValue;
    }

    private List<Node> getValidAdjacents(Node node) {

        List<Node> neighbours = new ArrayList<>();

        if(isInBoundsY(node.getY() + UP )){
            if(!grid.getCell(node.getX(),node.getY() + UP).isWall()){
                neighbours.add(this.searchArea[node.getX()][node.getY() + UP]);
            }
        }

        if(isInBoundsX(node.getX() + LEFT )){
            if(!grid.getCell(node.getX() + LEFT,node.getY()).isWall()){
                neighbours.add(this.searchArea[node.getX() + LEFT][node.getY()]);
            }
        }

        if(isInBoundsY(node.getY() + DOWN )){
            if(!grid.getCell(node.getX(),node.getY() + DOWN).isWall()){
                neighbours.add(this.searchArea[node.getX()][node.getY() + DOWN]);
            }
        }

        if(isInBoundsX(node.getX() + RIGHT )){
            if(!grid.getCell(node.getX() + RIGHT,node.getY()).isWall()){
                neighbours.add(this.searchArea[node.getX() + RIGHT][node.getY()]);
            }
        }
        return neighbours;
    }

    private int getLRTACost(Node candNext){
        int fValue;
        int kValue;

        if(candNext == null){
            fValue = candNext.calculateHeuristic(getFinalNode());
        }
        else{
            int hValue = candNext.getH();
            if(candNext.isGrass())
                kValue = 2;
            else
                kValue = 1;
            fValue = hValue + kValue;
        }
        return fValue;
    }

    public Node getFinalNode() { return finalNode; }

    public Node[][] getSearchArea() {
        return searchArea;
    }

}
