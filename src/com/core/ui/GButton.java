package com.core.ui;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class GButton extends JButton {
	private int x = 0;
	private int y = 0;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public GButton(){
		
	}
	
	public GButton(ImageIcon icon){
		super(icon);
	}
}
