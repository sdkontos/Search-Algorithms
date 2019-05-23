package MainPackage;

import LabNotes.*;
import SearchGrid.*;
import javax.swing.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;


public class MainClass{
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, InvocationTargetException, InterruptedException {

        final int EASY = 1;
        final int HARD_A = 2;
        final int HARD_B = 3;
        final int HARD_C = 4;
        final int RANDOM = 5;
        final int DEFAULT = 6;
        final int CHOOSE_FROM_FILE = 7;

        final int[] userChoice = new int[3];
        final File[] selectedFile = new File[1];

        String frame = "";
        Grid myGrid;

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeAndWait(() -> {
            GuiMenu guiMenu = new GuiMenu();
            guiMenu.setVisible(true);
            userChoice[0]   =  guiMenu.getValuesFromUser()[0];
            userChoice[1]   =  guiMenu.getValuesFromUser()[1];
            userChoice[2]   =  guiMenu.getValuesFromUser()[2];
            selectedFile[0] =  guiMenu.getSelectedFile();
        });

        switch (userChoice[0]){
            case EASY:
                myGrid = new Grid("world_examples/easy.world");
                frame = "Easy world [Size: " + myGrid.getNumOfColumns() + "x" + myGrid.getNumOfRows() + "]";
                break;
            case HARD_A:
                myGrid = new Grid("world_examples/hard_a.world");
                frame = "Hard ( Type A ) [Size: " + myGrid.getNumOfColumns() + "x" + myGrid.getNumOfRows() + "]";
                break;
            case HARD_B:
                myGrid = new Grid("world_examples/hard_b.world");
                frame = "Hard ( Type B ) [Size: " + myGrid.getNumOfColumns() + "x" + myGrid.getNumOfRows() + "]";
                break;
            case HARD_C:
                myGrid = new Grid("world_examples/hard_c.world");
                frame = "Hard ( Type C )  [Size: " + myGrid.getNumOfColumns() + "x" + myGrid.getNumOfRows() + "]";
                break;
            case DEFAULT:
                myGrid = new Grid("world_examples/default.world");
                frame = "Default World [Size: " + myGrid.getNumOfColumns() + "x" + myGrid.getNumOfRows() + "]";
                break;
            case RANDOM:
                if(userChoice[1] == 0 || userChoice[2] == 0){
                    myGrid = new Grid();
                    frame = "Random World [Size: " + myGrid.getNumOfColumns() + "x" + myGrid.getNumOfRows() + "]";
                }
                else{
                    myGrid = new Grid(userChoice[2],userChoice[1]);
                    frame = "Random World [Size: " + myGrid.getNumOfColumns() + "x" + myGrid.getNumOfRows() + "]";

                }
                break;
            case CHOOSE_FROM_FILE:
                myGrid = new Grid(selectedFile[0].getAbsolutePath());
                frame = selectedFile[0].getAbsolutePath().split("/")[5] + " [Size: " + myGrid.getNumOfColumns() + "x" + myGrid.getNumOfRows() + "]";
                break;
            default:
                myGrid = null;
                System.out.println("\nBad selection");
                System.exit(0);
        }

        int N = myGrid.getNumOfRows();
        int M = myGrid.getNumOfColumns();

        GridGenerator.VisualizeGrid(frame,N,M,myGrid.getWalls(),myGrid.getGrass(),myGrid.getStartidx(),myGrid.getTerminalidx());

        System.out.println(frame + "\nCosts:\n-Land: " + myGrid.getLand_cost() + "\n-Grass: " + myGrid.getGrass_cost());
        BFSGrid bfsGrid = new BFSGrid(myGrid);

        int BFSResult[] = bfsGrid.BFSearch();

        if(BFSResult[0] == 0) System.out.println("\nBFS Algorithm could not found route!\n");

        else System.out.println("\nBFS Algorithm found route with cost: " + BFSResult[1] + " steps");
        
        /* Turn back visited nodes to not-visited in order to be applied a new algorithm */
        myGrid.initVisited();

        DFSGrid dfsGrid = new DFSGrid(myGrid);

        int DFSResult[] = dfsGrid.DFSearch();
        if(DFSResult[0] == 0) System.out.println("\nDFS Algorithm could not found route!\n");

        else System.out.println("\nDFS Algorithm found route with cost: " + DFSResult[1] + " steps");

        /* Turn back visited nodes to not-visited in order to be applied a new algorithm */
        myGrid.initVisited();

        A_StarGrid a_starGrid = new A_StarGrid(myGrid);

        int A_StarResult[] = a_starGrid.A_StarSearch();

        if(A_StarResult[0] == 0) System.out.println("\nA* Algorithm could not found route!\n");

        else System.out.println("\nA* Algorithm found route with cost: " + A_StarResult[1] + " steps");

        myGrid.initVisited();

        LRTA_StarGrid lrtaStarGrid = new LRTA_StarGrid(myGrid);

        int LRTA_StarResult[] = lrtaStarGrid.LRTA_StarSearch();

        if(LRTA_StarResult[0] == 0) System.out.println("\nLRTA* Algorithm could not found route!\n");

        else System.out.println("\nLRTA* Algorithm found route with cost: " + LRTA_StarResult[1] + " steps");

    }
}
