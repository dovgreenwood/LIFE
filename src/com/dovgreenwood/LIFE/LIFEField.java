package com.dovgreenwood.LIFE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JPanel;

public class LIFEField extends JPanel implements Runnable {

	private NV nv = new NV();
	
	private boolean beginGame = false;
	private boolean field [][] = new boolean [nv.width][nv.height];
	private Thread runner = new Thread(this);
	
	public LIFEField() {		
		for(int x = 0; x < nv.width; x += 1) {
			for(int y = 0; y < nv.height; y += 1)
				field[x][y] = false;
		}
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == e.BUTTON1 && beginGame == false)
					setupSquare(e.getX(), e.getY());
				if(e.getButton() == e.BUTTON3 && beginGame == false) {
					beginGame = true;
					runner.start();
				}
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if(beginGame == false)
					setupSquare(e.getX(),e.getY());
			}
		});
	
		this.setLayout(new BorderLayout());
		this.add(new BottomPanel(nv, this), BorderLayout.SOUTH);
	}

	private int surrounding(int x, int y) {
		int trues = 0;
		for(int sx =  -1; sx < 2; sx += 1) {
			for(int sy =  -1; sy < 2; sy += 1) {
				try {
					if(field[x + sx][y + sy] == true)
						trues++;
				}
				catch(ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		return trues;
	}

	//for the game setup, takes mouse pos. and places a square there
	private void setupSquare(int x, int y) {
		x = x / nv.squareSize;
		y = y / nv.squareSize;
		if(x < field.length && y < field[x].length) {
			if(field[x][y] == false)
				field[x][y] = true;
			else
				field[x][y] = false;
		}
		repaint();
	}

	public void paintComponent(Graphics plane) {
		super.paintComponent(plane);
		
		boolean [][] newArray = expandField();
		
		for(int x = 0; x < nv.width; x += 1) {
			for(int y = 0; y < nv.height; y += 1) {
				if(newArray[x][y] == false) {
					plane.setColor(Color.BLACK);
					plane.drawRect(x * nv.squareSize, y * nv.squareSize, nv.squareSize, nv.squareSize);
				}
				else {
					plane.setColor(Color.GREEN);
					plane.fillRect(x * nv.squareSize, y * nv.squareSize, nv.squareSize, nv.squareSize);
				}
			}
		}
	}

	private boolean[][] expandField() {
		boolean [][] newArray = new boolean [nv.width][nv.height];
		for(int i = 0; i < nv.width; i++)
			for(int j = 0; i < nv.height; i++)
				newArray[i][j] = false;
		for(int i = 0; i < field.length; i++)
			newArray[i] = Arrays.copyOf(field[i], nv.height);
		field = newArray;
		return newArray;
	}

	//pauses for the given number of milliseconds
	private void delay(int time) {
		try {
			Thread.sleep(time);
		}
		catch(Exception e) {
		}
	}

	//main game loop (set as a Thread to be able to close)
	public void run() {
		while(beginGame) {
			boolean [][] newField = new boolean[nv.width][nv.height];
			for(int x = 0; x < nv.width; x += 1) {
				for(int y = 0; y < nv.height; y += 1) {
					int trues = surrounding(x, y);
					if(field[x][y] == true) {
						newField[x][y] = (trues - 1 == 2 || trues - 1 == 3);
					}
					else {
						newField[x][y] = (trues == 3);
					}
				}
			}
			for(int x = 0; x < nv.width; x += 1) {
				for(int y = 0; y < nv.height; y += 1) {
					field[x][y] = newField[x][y];
				}
			}
			revalidate();
			repaint();
			delay(nv.delay);
		}
	}

}
