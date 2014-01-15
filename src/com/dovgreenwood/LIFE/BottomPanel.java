package com.dovgreenwood.LIFE;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BottomPanel extends JPanel {
	
	private JTextField changeSquareSize = new JTextField(10);
	private JTextField changeHeight = new JTextField(10);
	private JTextField changeWidth = new JTextField(10);
	private JTextField changeDelay = new JTextField(10);
	private JButton change = new JButton("CHANGE");
	
	private NV nv;
	private LIFEField lf;
	
	public BottomPanel(NV nv, LIFEField lf) {
		this.nv = nv;
		this.lf = lf;
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		this.add(new JLabel("Delay (millis): "));
		changeDelay.setText(Integer.toString(nv.delay));
		this.add(changeDelay);
		
		this.add(new JLabel("Square Size: "));
		changeSquareSize.setText(Integer.toString(nv.squareSize));
		this.add(changeSquareSize);
		
		this.add(new JLabel("Height (# of squares): "));
		changeHeight.setText(Integer.toString(nv.height));
		this.add(changeHeight);
		
		this.add(new JLabel("Width (# of squares): "));
		changeWidth.setText(Integer.toString(nv.width));
		this.add(changeWidth);
		
		change.addActionListener(new ButtonListener());
		this.add(change);
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			nv.width = Integer.parseInt(changeWidth.getText());
			nv.height = Integer.parseInt(changeHeight.getText());
			nv.squareSize = Integer.parseInt(changeSquareSize.getText());
			nv.delay = Integer.parseInt(changeDelay.getText());
			lf.repaint();
			//Entry.setSize(nv.getWidth(), nv.getHeight());
		}
		
	}
}
