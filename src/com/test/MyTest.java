package com.test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class MyTest{
	public JFrame frame = new JFrame();
	
	public MyTest(){
		frame.addKeyListener(new MyKeyListenner());
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args){
		MyTest m = new MyTest();
		
	}
	
	class MyKeyListenner implements KeyListener{

		public MyKeyListenner(){
			
		}
		public void keyTyped(KeyEvent e) {//ÇÃ»÷
			System.out.println("ÇÃ»÷");
		 }
		 public void keyPressed(KeyEvent e) {//°´ÏÂ
		  System.out.println("key down");
		 }
		 public void keyReleased(KeyEvent e) {//ÊÍ·Å
			 System.out.println("key up");
		 }
		
	}
}
