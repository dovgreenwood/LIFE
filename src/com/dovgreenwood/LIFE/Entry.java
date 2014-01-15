package com.dovgreenwood.LIFE;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Entry extends JFrame {

	private NV nv = new NV();
	
	public Entry() {
		setTitle("The Game of Life");
		setSize(nv.width*nv.squareSize, nv.height*nv.squareSize + 60);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new LIFEField();
		this.add(panel);
		setResizable(false);
	}

	public static void main(String [] args) {
		JFrame frame = new Entry();
		frame.setVisible(true);
	}

}
