package com.dovgreenwood.LIFE;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BottomPanel extends JPanel {
	
	private JTextField changeSquareSize = new JTextField("10",10);
	private JTextField changeHeight = new JTextField("50", 10);
	private JTextField changeWidth = new JTextField("100", 10);
	private JButton change = new JButton("CHANGE");
	
	private NV nv = new NV();
	
	public BottomPanel() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.add(new JLabel("Square Size: "));
		this.add(changeSquareSize);
		this.add(new JLabel("Height (# of squares): "));
		this.add(changeHeight);
		this.add(new JLabel("Width (# of squares): "));
		this.add(changeWidth);
		change.addActionListener(new ButtonListener());
		this.add(change);
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			nv.width = Integer.parseInt(changeWidth.getText());
			nv.height = Integer.parseInt(changeHeight.getText());
			nv.squareSize = Integer.parseInt(changeSquareSize.getText());
			System.out.println(nv.height + " " + nv.width + " " + nv.squareSize);
			//Entry.setSize(nv.getWidth(), nv.getHeight());
		}
		
	}
}
