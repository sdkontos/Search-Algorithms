package MainPackage;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GuiMenu extends JDialog{
    private static final int EASY = 1;
    private static final int HARD_A = 2;
    private static final int HARD_B = 3;
    private static final int HARD_C = 4;
    private static final int RANDOM = 5;
    private static final int DEFAULT = 6;
    private static final int CHOOSE_FROM_FILE = 7;
    private int[] valuesFromUser;
    private JPanel menuPanel;
    private JTextArea headerMenu;
    private JButton easyButton;
    private JButton hardAButton;
    private JButton hardBButton;
    private JButton hardCButton;
    private JButton randomButton;
    private JButton defaultButton;
    private JTextArea heightText;
    private JEditorPane heightEdit;
    private JEditorPane widthEdit;
    private JTextArea widthText;
    private JButton chooseButton;
    private File selectedFile = null;

    public int[] getValuesFromUser() {
        return valuesFromUser;
    }

    public File getSelectedFile() { return selectedFile; }

    public GuiMenu(){
        super((Window)null);
        setModal(true);
        valuesFromUser = new int[3];
        add(menuPanel);
        setTitle("GUI Menu");
        setSize(400,250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        easyButton.addActionListener(e -> {
            valuesFromUser[0] = EASY;
            valuesFromUser[1] = 0;
            valuesFromUser[2] = 0;
            dispose();
        });

        hardAButton.addActionListener(e -> {
            valuesFromUser[0] = HARD_A;
            valuesFromUser[1] = 0;
            valuesFromUser[2] = 0;
            dispose();
        });

        hardBButton.addActionListener(e -> {
            valuesFromUser[0] = HARD_B;
            valuesFromUser[1] = 0;
            valuesFromUser[2] = 0;
            dispose();
        });

        hardCButton.addActionListener(e -> {
            valuesFromUser[0] = HARD_C;
            valuesFromUser[1] = 0;
            valuesFromUser[2] = 0;
            dispose();
        });

        randomButton.addActionListener(e -> {
            valuesFromUser[0] = RANDOM;
            if(Integer.parseInt(widthEdit.getText()) != 0) valuesFromUser[1] = Integer.parseInt(widthEdit.getText());
            else valuesFromUser[1] = 0;
            if(Integer.parseInt(heightEdit.getText()) != 0) valuesFromUser[2] = Integer.parseInt(heightEdit.getText());
            else valuesFromUser[2] = 0;
            dispose();
        });

        defaultButton.addActionListener(e -> {
            valuesFromUser[0] = DEFAULT;
            valuesFromUser[1] = 0;
            valuesFromUser[2] = 0;
            dispose();
        });

        chooseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(menuPanel);
            if(result == JFileChooser.APPROVE_OPTION){
                selectedFile = fileChooser.getSelectedFile();
                String selectedFileExtension = selectedFile.getAbsolutePath().substring(selectedFile.getAbsolutePath().length() - 6);
                String validExtension = ".world";
                if(selectedFileExtension.equals(validExtension)){
                    valuesFromUser[0] = CHOOSE_FROM_FILE;
                    valuesFromUser[1] = 0;
                    valuesFromUser[2] = 0;
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                }
                else{
                    valuesFromUser[0] = RANDOM;
                    valuesFromUser[1] = 0;
                    valuesFromUser[2] = 0;
                    System.out.println("Wrong file extension. Full Random World created.");
                }
                dispose();
            }
        });
    }
}
