package com.core;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


/*
 * 2048��Ϸ����
 * ��ʼ��ʱ�����ĸ���������λ�ó���һ��2,����������λ�ó�ʼһ����(2/4/8)
 */
public class G2048 {
	
	private int[][] square={{-1,-1,-1,-1},{-1,-1,-1,-1},{-1,-1,-1,-1},{-1,-1,-1,-1}};//-1��ʾ�հ�,�����ʾ����2��x�η�
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
	
	//w-��;d-��;x-��;a-��
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
		if(type!= null && type.equals("a") ){//����
			//System.out.println("\n----------------------����----------------------");
			//������ջ
			for(int i=getSquareLength()-1;i>-1;i--){
				if(getSquareItemAtPosition(i+1,row)!=-1){
					s.push(getSquareItemAtPosition(i+1,row));
					setSquareItemAtPosition(i+1,row,-1);
				}
			}
			//�ϲ�
			int count=0;
			while(!s.empty()){
				int value = s.pop();
				if(count==0){
					setSquareItemAtPosition(count+1,row,value);
				}else{
					if(value==getSquareItemAtPosition(count,row)){
						//����һ�κϲ�
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
			//ȥ�ո�
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
			//����
			//System.out.println("\n----------------------����----------------------");
			//������ջ
			for(int i=0;i<getSquareLength();i++){
				if(getSquareItemAtPosition(i+1,row)!=-1){
					s.push(getSquareItemAtPosition(i+1,row));
					setSquareItemAtPosition(i+1,row,-1);
				}
			}
			//�ϲ�
			int count=0;
			while(!s.empty()){
				int value = s.pop();
				if(count==0){
					setSquareItemAtPosition(getSquareLength()-count,row,value);
				}else{
					if(value==getSquareItemAtPosition(getSquareLength()-count+1,row)){
						//����һ�κϲ�
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
			//ȥ�ո�
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
			//����
			//System.out.println("\n----------------------����----------------------");
			//������ջ
			for(int i=getSquareLength()-1;i>-1;i--){
				if(getSquareItemAtPosition(col,i+1)!=-1){
					s.push(getSquareItemAtPosition(col,i+1));
					setSquareItemAtPosition(col,i+1,-1);
				}
			}
			//�ϲ�
			int count=0;
			while(!s.empty()){
				int value = s.pop();
				if(count==0){
					setSquareItemAtPosition(col,count+1,value);
				}else{
					if(value==getSquareItemAtPosition(col,count)){
						//����һ�κϲ�
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
			//ȥ�ո�
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
			//����
			//System.out.println("\n----------------------����----------------------");
			//������ջ
			for(int i=0;i<getSquareLength();i++){
				if(getSquareItemAtPosition(col,i+1)!=-1){
					s.push(getSquareItemAtPosition(col,i+1));
					setSquareItemAtPosition(col,i+1,-1);
				}
			}
			//�ϲ�
			int count=0;
			while(!s.empty()){
				int value = s.pop();
				if(count==0){
					setSquareItemAtPosition(col,getSquareLength()-count,value);
				}else{
					if(value==getSquareItemAtPosition(col,getSquareLength()-count+1)){
						//����һ�κϲ�
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
			//ȥ�ո�
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
			//���ѡ��һ���հ�λ��
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
		System.out.println("��ӭ����2048��Ϸ v1.0");
		G2048 g = new G2048();
		g.setNumberAtAuywhereBlank();
		g.show();
		while(true){
			System.out.println("");
			System.out.println("�������ƶ�����(w-��;d-��;x-��;a-��;)������ɺ�Enter��ȷ��");
			Scanner in=new Scanner(System.in);
			String input = in.nextLine();
			if(input==null || input.equals("")){
				System.out.println("�������,����������");
				System.out.println("�������ƶ�����(w-��;d-��;x-��;a-��;)������ɺ�Enter��ȷ��");
				continue;
			}else if(!input.equals("w") && !input.equals("d") &&!input.equals("x") &&!input.equals("a")){
				System.out.println("�������,����������");
				System.out.println("�������ƶ�����(w-��;d-��;x-��;a-��;)������ɺ�Enter��ȷ��");
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
