package com.core.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.core.G2048;


public class G2048ui implements KeyListener{
	JFrame frame = new JFrame("2048 v1.0");
	public JPanel jpanel;
	G2048 g2048 = new G2048();
	private JButton[] jButtonArray;
	
	public G2048ui(){
		//frame.addKeyListener(new g2048KeyListenner());
		addInterface();
		addMenu();
		//printPosition();
	}
	
	
	
	public void addInterface(){
		jButtonArray = new JButton[g2048.getSquareLength()*g2048.getSquareLength()];
		//frame.setResizable(true);
		jpanel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jpanel.setLayout(new GridLayout(g2048.getSquareLength(), g2048.getSquareLength(), 0, 0));
		for (int i = 0; i < g2048.getSquareLength()*g2048.getSquareLength(); i++) {
			JButton jButton = new JButton(new ImageIcon("resources/blank.jpg")); // 按钮
			//jButton.setBounds((i%g2048.getSquareLength())*80,(i/g2048.getSquareLength())*80,80,80);
			Dimension preferredSize = new Dimension(40, 40);// 设置尺寸
			jButton.setPreferredSize(preferredSize);
			jButton.setMargin(new Insets(0, 0, 0, 0));
			jButton.setBorderPainted(false);
			jButton.setBorder(null);
			jButton.addKeyListener(this);
			jButton.setFocusPainted(false);
			jpanel.add(jButton);
			jButtonArray[i] = jButton;
		}
		frame.add(jpanel);
		frame.setBounds(0, 0, 40*g2048.getSquareLength()+16, 40*g2048.getSquareLength()+70);
		//frame.setResizable(false);
		frame.setLocationRelativeTo(null); 
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭动作处理
	}
	
	public void addMenu() {
		JMenuBar g2048MenuBar = new JMenuBar();
		frame.setJMenuBar(g2048MenuBar);
		JMenu g2048Menu_option = new JMenu("选项");
		JMenu g2048Menu_help = new JMenu("帮助");
		JMenuItem g2048Menu_option_1_basic = new JMenuItem("重新开始");
		JMenuItem g2048Menu_option_2_exit = new JMenuItem("退出");
		
		//添加菜单事件
		g2048Menu_option_2_exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Event){
				int i=JOptionPane.showConfirmDialog(null, "确定退出游戏?","退出确认对话框",JOptionPane.YES_NO_OPTION);
				if(i==0){
					frame.dispose();
				}
			}
		});
		
		g2048Menu_option_1_basic.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Event){
				frame.remove(jpanel);
				//init
				g2048.init();
				addInterface();
				updateSaoleiUI();
			}
		});
		
		g2048Menu_option.add(g2048Menu_option_1_basic);
		g2048Menu_option.addSeparator();
		g2048Menu_option.add(g2048Menu_option_2_exit);
		
		JMenuItem g2048Menu_help_1_des = new JMenuItem("说明");
		JMenuItem g2048Menu_help_2_about = new JMenuItem("关于");
		
		g2048Menu_help_1_des.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Event){
				JOptionPane.showMessageDialog(null, "2048 JAVA版\n请使用上下左右方向键开始游戏","2048",JOptionPane.YES_NO_OPTION);
			}
		});
		
		g2048Menu_help_2_about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent Event){
				JOptionPane.showMessageDialog(null, "作者:lyz QQ:498012214","2048 JAVA版",JOptionPane.YES_NO_OPTION);
			}
		});
		
		g2048Menu_help.add(g2048Menu_help_1_des);
		g2048Menu_help.add(g2048Menu_help_2_about);
		g2048MenuBar.add(g2048Menu_option);
		g2048MenuBar.add(g2048Menu_help);
		
	}
	
	public JButton getJButtonAtPosition(int x,int y){
		JButton jBtn = null;
		if(x<=0 || y<=0 || x>g2048.getSquareLength() || y>g2048.getSquareLength()){
			return jBtn;
		}
		int btnCount = jpanel.getComponentCount();
		for (int i = 0; i < btnCount; i++) {
			Component comp = jpanel.getComponent(i);
			if(comp instanceof JButton){
				jBtn = (JButton)comp;
				//System.out.println("X="+(btn.getX()/20+1)+";Y="+(btn.getY()/20+1));
				if((jBtn.getX()/40+1)==x && (jBtn.getY()/40+1)==y){
					//System.out.println("在UI上找到btn("+x+","+y+")");
					return jBtn;
				}
			}
		}
		return jBtn;
		/*if(jButtonArray.length>0){
			return jButtonArray[(g2048.getSquareLength()-1)*g2048.getSquareLength()+x-1];
		}else{
			return null;
		}*/
	}
	
	public void printPosition(){
		JButton jBtn = null;
		int btnCount = jpanel.getComponentCount();
		for (int i = 0; i < btnCount; i++) {
			Component comp = jpanel.getComponent(i);
			if(comp instanceof JButton){
				jBtn = (JButton)comp;
				System.out.println("在UI上找到btn("+jBtn.getX()+","+jBtn.getY()+")");
			}
		}
	}
	
	public void updateSaoleiUI(){
		//更新视图	
		
		for(int i=0;i<g2048.getSquareLength();i++){//纵向
			for(int j=0;j<g2048.getSquareLength();j++){//横向
				if(g2048.getSquareItemAtPosition(i+1,j+1)==-1){
					getJButtonAtPosition(i+1,j+1).setIcon(new ImageIcon("resources/blank.jpg"));
				}else if(g2048.getSquareItemAtPosition(i+1,j+1)>0){
					getJButtonAtPosition(i+1,j+1).setIcon(new ImageIcon("resources/g_"+g2048.getSquareItemAtPosition(i+1,j+1)+".jpg"));
				}
			}
		}
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			// 设置本属性将改变窗口边框样式定义
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			// TODO exception
		}
		G2048ui g = new G2048ui();
		g.show();
		g.updateSaoleiUI();
		//tb.showAll();
	}
	

	public void keyTyped(KeyEvent e) {//敲击
		//System.out.println("敲击");
	 }
	 public void keyPressed(KeyEvent e) {//按下
	  //System.out.println("key down "+e.getKeyCode());
	  if(e.getKeyCode()==38 || e.getKeyCode()==40 || e.getKeyCode()==37 || e.getKeyCode()==39){
		  String position = "";
		  if(e.getKeyCode()==38){
			  position="w";
		  }else if(e.getKeyCode()==40){
			  position="x";
		  }else if(e.getKeyCode()==37){
			  position="a";
		  }else if(e.getKeyCode()==39){
			  position="d";
		  }
		  if(!position.equals("")){
			  //updateSaoleiUI();
			  g2048.move(position);
			  g2048.setNumberAtAuywhereBlank();
			  updateSaoleiUI();
			  g2048.show();
			  if(g2048.isGameOver() && g2048.emptySquareNum<=0){
				  //并且没有至少两个相邻且相同的数
				  JOptionPane.showMessageDialog(null,"游戏结束!!!","GAME OVER",JOptionPane.OK_OPTION);
			  }
		  }
	  }
	  
	 }
	 public void keyReleased(KeyEvent e) {//释放
		// System.out.println("key up");
	 }
		

}
