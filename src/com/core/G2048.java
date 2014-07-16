package com.core;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


/*
 * 2048游戏主体
 * 初始的时候在四个方向任意位置出现一个2,格子中任意位置初始一个数(2/4/8)
 */
public class G2048 {
	
	private int[][] square={{-1,-1,-1,-1},{-1,-1,-1,-1},{-1,-1,-1,-1},{-1,-1,-1,-1}};//-1表示空白,其余表示数字2的x次方
	private int squareLength;
	private boolean gameOver = false;
	private String message ="";
	public int emptySquareNum = 16;
	
	public int[][] getSquare() {
		return square;
	}

	public void setSquare(int[][] square) {
		this.square = square;
	}

	public int getSquareLength() {
		return squareLength;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSquareLength(int squareLength) {
		this.squareLength = squareLength;
	}

	public int getSquareItemAtPosition(int x,int y){
		return getSquare()[x-1][y-1];
	}
	
	public void setSquareItemAtPosition(int x,int y,int value){
		getSquare()[x-1][y-1]=value;
	}
	
	public G2048(){
		init();		
	}
	
	public void init(){
		setSquareLength(4);
		for(int i=0;i<getSquareLength();i++){
			for(int j=0;j<getSquareLength();j++){
				getSquare()[i][j] = new Integer(-1);
			}
		}
		setGameOver(false);
		setMessage("");
		emptySquareNum=16;
		setNumberAtAuywhereBlank();
		show();
	}
	
	//w-上;d-右;x-下;a-左
	public void move(String operateCode){
		for(int i=0;i<getSquareLength();i++){
			if(operateCode.equals("a")){
				moveToLeftOrRight(i+1,"a");
			}else if(operateCode.equals("d")){
				moveToLeftOrRight(i+1,"d");
			}else if(operateCode.equals("w")){
				moveToUpOrDown(i+1,"w");
			}else if(operateCode.equals("x")){
				moveToUpOrDown(i+1,"x");
			}
			
		}
	}
	
	public void moveToLeftOrRight(int row,String type){
		Stack<Integer> s = new Stack();
		if(type!= null && type.equals("a") ){//左移
			//System.out.println("\n----------------------左移----------------------");
			//反向入栈
			for(int i=getSquareLength()-1;i>-1;i--){
				if(getSquareItemAtPosition(i+1,row)!=-1){
					s.push(getSquareItemAtPosition(i+1,row));
					setSquareItemAtPosition(i+1,row,-1);
				}
			}
			//合并
			int count=0;
			while(!s.empty()){
				int value = s.pop();
				if(count==0){
					setSquareItemAtPosition(count+1,row,value);
				}else{
					if(value==getSquareItemAtPosition(count,row)){
						//发生一次合并
						setSquareItemAtPosition(count,row,getSquareItemAtPosition(count,row)+1);
						setSquareItemAtPosition(count+1,row,-1);
						emptySquareNum++;
					}else{
						setSquareItemAtPosition(count+1,row,value);
					}
				}
				count++;
			}
			s.clear();
			//去空格
			for(int i=getSquareLength()-1;i>-1;i--){
				if(getSquareItemAtPosition(i+1,row)!=-1){
					s.push(getSquareItemAtPosition(i+1,row));
					setSquareItemAtPosition(i+1,row,-1);
				}
			}
			count=0;
			while(!s.empty()){
				int value = s.pop();
				setSquareItemAtPosition(count+1,row,value);
				count++;
			}
		}else if(type!= null && type.equals("d") ){
			//右移
			//System.out.println("\n----------------------右移----------------------");
			//反向入栈
			for(int i=0;i<getSquareLength();i++){
				if(getSquareItemAtPosition(i+1,row)!=-1){
					s.push(getSquareItemAtPosition(i+1,row));
					setSquareItemAtPosition(i+1,row,-1);
				}
			}
			//合并
			int count=0;
			while(!s.empty()){
				int value = s.pop();
				if(count==0){
					setSquareItemAtPosition(getSquareLength()-count,row,value);
				}else{
					if(value==getSquareItemAtPosition(getSquareLength()-count+1,row)){
						//发生一次合并
						setSquareItemAtPosition(getSquareLength()-count+1,row,getSquareItemAtPosition(getSquareLength()-count+1,row)+1);
						setSquareItemAtPosition(getSquareLength()-count,row,-1);
						emptySquareNum++;
					}else{
						setSquareItemAtPosition(getSquareLength()-count,row,value);
					}
				}
				count++;
			}
			s.clear();
			//去空格
			for(int i=0;i<getSquareLength();i++){
				if(getSquareItemAtPosition(i+1,row)!=-1){
					s.push(getSquareItemAtPosition(i+1,row));
					setSquareItemAtPosition(i+1,row,-1);
				}
			}
			count=0;
			while(!s.empty()){
				int value = s.pop();
				setSquareItemAtPosition(getSquareLength()-count,row,value);
				count++;
			}
		}
	}
	
	public void moveToUpOrDown(int col,String type){
		Stack<Integer> s = new Stack();
		if(type!= null && type.equals("w") ){
			//上移
			//System.out.println("\n----------------------上移----------------------");
			//反向入栈
			for(int i=getSquareLength()-1;i>-1;i--){
				if(getSquareItemAtPosition(col,i+1)!=-1){
					s.push(getSquareItemAtPosition(col,i+1));
					setSquareItemAtPosition(col,i+1,-1);
				}
			}
			//合并
			int count=0;
			while(!s.empty()){
				int value = s.pop();
				if(count==0){
					setSquareItemAtPosition(col,count+1,value);
				}else{
					if(value==getSquareItemAtPosition(col,count)){
						//发生一次合并
						setSquareItemAtPosition(col,count,getSquareItemAtPosition(col,count)+1);
						setSquareItemAtPosition(col,count+1,-1);
						emptySquareNum++;
					}else{
						setSquareItemAtPosition(col,count+1,value);
					}
				}
				count++;
			}
			s.clear();
			//去空格
			for(int i=getSquareLength()-1;i>-1;i--){
				if(getSquareItemAtPosition(col,i+1)!=-1){
					s.push(getSquareItemAtPosition(col,i+1));
					setSquareItemAtPosition(col,i+1,-1);
				}
			}
			count=0;
			while(!s.empty()){
				int value = s.pop();
				setSquareItemAtPosition(col,count+1,value);
				count++;
			}
		}else if(type!= null && type.equals("x") ){
			//下移
			//System.out.println("\n----------------------下移----------------------");
			//反向入栈
			for(int i=0;i<getSquareLength();i++){
				if(getSquareItemAtPosition(col,i+1)!=-1){
					s.push(getSquareItemAtPosition(col,i+1));
					setSquareItemAtPosition(col,i+1,-1);
				}
			}
			//合并
			int count=0;
			while(!s.empty()){
				int value = s.pop();
				if(count==0){
					setSquareItemAtPosition(col,getSquareLength()-count,value);
				}else{
					if(value==getSquareItemAtPosition(col,getSquareLength()-count+1)){
						//发生一次合并
						setSquareItemAtPosition(col,getSquareLength()-count+1,getSquareItemAtPosition(col,getSquareLength()-count+1)+1);
						setSquareItemAtPosition(col,getSquareLength()-count,-1);
						emptySquareNum++;
					}else{
						setSquareItemAtPosition(col,getSquareLength()-count,value);
					}
				}
				count++;
			}
			s.clear();
			//去空格
			for(int i=0;i<getSquareLength();i++){
				if(getSquareItemAtPosition(col,i+1)!=-1){
					s.push(getSquareItemAtPosition(col,i+1));
					setSquareItemAtPosition(col,i+1,-1);
				}
			}
			count=0;
			while(!s.empty()){
				int value = s.pop();
				setSquareItemAtPosition(col,getSquareLength()-count,value);
				count++;
			}
		}
	}
	
	public void show(){
		for(int i=0;i<getSquareLength();i++){
			for(int j=0;j<getSquareLength();j++){
				if(getSquareItemAtPosition(j+1,i+1)==-1){
					System.out.print(" _");
				}else{
					System.out.printf(" %d",getSquareItemAtPosition(j+1,i+1));
				}
			}
			System.out.println("");
		}
	}
	
	public void setNumberAtAuywhereBlank(){
		Position[] positons = new Position[emptySquareNum];
		int count =0;
		for(int i=0;i<getSquareLength();i++){
			for(int j=0;j<getSquareLength();j++){
				if(getSquareItemAtPosition(i+1,j+1)==-1){
					try{
					positons[count]=new Position(i+1,j+1);
					}catch(Exception e){
						e.printStackTrace();
						System.out.println("count="+count);
					}
					count++;
				}
			}
		}
		if(emptySquareNum>0){
			//随机选择一个空白位置
			Random random = new Random();
			int randomNumber = Math.abs(random.nextInt());
			int value=mathSquareAtTwo(randomNumber%64<=4?randomNumber%3+1:1);
			int randomNumberPosition = Math.abs(random.nextInt());
			int findPosition=randomNumberPosition%emptySquareNum;
			
			setSquareItemAtPosition(positons[findPosition].getX(),positons[findPosition].getY(),value);
			emptySquareNum=emptySquareNum-1;
			System.out.printf("set (%d,%d) = %d\n",positons[findPosition].getX(),positons[findPosition].getY(),value);
			System.out.println("emptySquareNum="+emptySquareNum);
		}else{
			setGameOver(true);
			setMessage("GAME OVER");
		}
	}
	
	public int mathSquareAtTwo(int q){
		/*int result = 1;
		if(q<=0 || q>4){
			result = 2;
			return result;
		}else{
			for(int i=0;i<q;i++){
				result=result*2;
			}
			return result;
		}*/
		int result = 0;
		if(q<=0 || q>4){
			result =1 ;
			return result;
		}else{
			for(int i=0;i<q;i++){
				result=result+1;
			}
			return result;
		}
	}
	
	public enum P {
	       UP , RIGHT , DOWN ,LEFT ;
	}
	
	/*public static void main(String[] args){
		System.out.println("欢迎进入2048游戏 v1.0");
		G2048 g = new G2048();
		g.setNumberAtAuywhereBlank();
		g.show();
		while(true){
			System.out.println("");
			System.out.println("请输入移动方向(w-上;d-右;x-下;a-左;)输入完成后按Enter键确认");
			Scanner in=new Scanner(System.in);
			String input = in.nextLine();
			if(input==null || input.equals("")){
				System.out.println("输入错误,请重新输入");
				System.out.println("请输入移动方向(w-上;d-右;x-下;a-左;)输入完成后按Enter键确认");
				continue;
			}else if(!input.equals("w") && !input.equals("d") &&!input.equals("x") &&!input.equals("a")){
				System.out.println("输入错误,请重新输入");
				System.out.println("请输入移动方向(w-上;d-右;x-下;a-左;)输入完成后按Enter键确认");
				continue;
			}else{
				g.move(input);
				g.setNumberAtAuywhereBlank();
				g.show();
				if(g.isGameOver()){
					break;
				}
			}
		}
		
	}*/
}
