package SearchGrid;

import LabNotes.*;
import java.util.*;

public class A_StarGrid {

    private Node[][] searchArea;
    private PriorityQueue<Node> openList;
    private Set<Node> closedSet;
    private Node initialNode;
    private Node finalNode;
    private Grid grid;

    public A_StarGrid(Grid grid) {

        this.grid = grid;
        this.searchArea = new Node[grid.getNumOfRows()][grid.getNumOfColumns()];
        this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node0, Node node1) {
                return Integer.compare(node0.getF(), node1.getF());
            }
        });

        initialNode = new Node(grid.getStart()[0],grid.getStart()[1]);
        initialNode.setStarting(true);
        finalNode = new Node(grid.getTerminal()[0],grid.getTerminal()[1]);
        finalNode.setTerminal(true);

        setNodes();
        this.closedSet = new HashSet<>();
    }

    private void setNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {

                Node node = new Node(i,j);
                node.calculateHeuristic(getFinalNode());

                if(grid.getCell(i,j).isStart()) node.setStarting(true);
                else if(grid.getCell(i,j).isTerminal()) node.setTerminal(true);

                if(grid.getCell(i,j).isWall()) node.setWall(true);
                else if(grid.getCell(i,j).isGrass()) node.setGrass(true);
                else if(grid.getCell(i,j).isLand()) node.setLand(true);

                this.searchArea[i][j] = node;
            }
        }
    }

    public int[] A_StarSearch() {

        int[] returnValue = {0,-1};
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            Node currentNode = openList.poll();
            closedSet.add(currentNode);
            if (currentNode.isTerminal()) {
                returnValue[0] = 1;
                returnValue[1] = currentNode.getF();
                return returnValue;
            } else {
                addAdjacentNodes(currentNode);
            }
        }

        return returnValue;
    }

    private void addAdjacentNodes(Node currentNode){
        int row = currentNode.getX();
        int col = currentNode.getY();
        int cost;

        if(currentNode.isGrass()) {
            cost = grid.getGrass_cost();
        } else {
            cost = grid.getLand_cost();
        }

        /* Upper Row */
        int upperRow = row - 1;
        if (upperRow >= 0) {
            checkNode(currentNode, col, upperRow, cost);
        }

        /* Middle Row */
        int middleRow = row;
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, middleRow, cost);
        }
        if (col + 1 < getSearchArea()[0].length) {
            checkNode(currentNode, col + 1, middleRow, cost);
        }

        /* Lower Row */
        int lowerRow = row + 1;
        if (lowerRow < getSearchArea().length) {
            checkNode(currentNode, col, lowerRow, cost);
        }
    }

    private void checkNode(Node currentNode, int col, int row, int cost) {
        Node adjacentNode = getSearchArea()[row][col];
        if (!adjacentNode.isWall() && !getClosedSet().contains(adjacentNode)) {
            if (!getOpenList().contains(adjacentNode)) {
                adjacentNode.setNodeData(currentNode, cost);
                getOpenList().add(adjacentNode);
            } else {
                boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
                if (changed) {
                    // Remove and Add the changed node, so that the PriorityQueue can sort again its
                    // contents with the modified "finalCost" value of the modified node
                    getOpenList().remove(adjacentNode);
                    getOpenList().add(adjacentNode);
                }
            }
        }
    }

    private boolean isEmpty(PriorityQueue<Node> openList) {
        return openList.size() == 0;
    }

    public Node getFinalNode() {
        return finalNode;
    }

    public Node[][] getSearchArea() {
        return searchArea;
    }

    public PriorityQueue<Node> getOpenList() {
        return openList;
    }

    public Set<Node> getClosedSet() {
        return closedSet;
    }

}