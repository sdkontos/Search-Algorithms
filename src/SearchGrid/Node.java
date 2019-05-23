package SearchGrid;

public class Node {

    private int x;
    private int y;
    private boolean visited;
    private int path_till_cost;
    private Node parent;
    private int f = 0;
    private int g;
    private int h;
    private boolean isWall;
    private boolean isGrass;
    private boolean isLand;
    private boolean isStarting;
    private boolean isTerminal;

    public Node(int posx,int posy){

        this.x = posx;
        this.y = posy;
        this.isGrass = false;
        this.isLand = false;
        this.isWall = false;
        this.isStarting = false;
        this.isTerminal = false;
    }

    public Node(int posx,int posy,boolean visited,int path_till_cost){

        this.x = posx;
        this.y = posy;
        this.visited = visited;
        this.path_till_cost = path_till_cost;
    }

    public String toString(){
        return "Position: (" + this.getX() + "," + this.getY() + ") - " + "Visited: " + this.isVisited() + "\nCost till now: " + getPath_till_cost();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getPath_till_cost() {
        return path_till_cost;
    }

    public void setPath_till_cost(int path_till_cost) {
        this.path_till_cost = path_till_cost;
    }

    public int calculateHeuristic(Node finalNode) {  this.h = Math.abs(finalNode.getX() - getX()) + Math.abs(finalNode.getY() - getY());
        return this.h;
    }

    public void setNodeData(Node currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        setParent(currentNode);
        setG(gCost);
        calculateFinalCost();
    }

    public boolean checkBetterPath(Node currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        if (gCost < getG()) {
            setNodeData(currentNode, cost);
            return true;
        }
        return false;
    }

    private void calculateFinalCost() {
        int finalCost = getG() + getH();
        setF(finalCost);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public boolean isGrass() {
        return isGrass;
    }

    public void setGrass(boolean grass) {
        isGrass = grass;
    }

    public boolean isLand() {
        return isLand;
    }

    public void setLand(boolean land) {
        isLand = land;
    }

    public boolean isStarting() {
        return isStarting;
    }

    public void setStarting(boolean starting) {
        isStarting = starting;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }
}
