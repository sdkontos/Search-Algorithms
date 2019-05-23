/**
 INTELLIGENCE LAB
 Course		: 	COMP 417 - Artificial Intelligence
 Authors	:	A. Georgara, A. Vogiatzis
 Excercise	:	1st Programming
 Term 		: 	Spring 2018-2019
 Date 		:   March 2019
 **/
package LabNotes;

import javax.swing.*;
import java.awt.Canvas;

public class GridGenerator{

	public static void VisualizeGrid(String frame_name, int N, int M, int [] walls, int [] grass, int start_idx, int terminal_idx ){
		JFrame frame = new JFrame(frame_name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Canvas canvas = new Drawing(N,M,walls,grass,start_idx,terminal_idx);
		canvas.setSize(M*30,N*30);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
}