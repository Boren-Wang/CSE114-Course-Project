//Boren Wang
//111385010
import java.util.*;
public class FinalProject {
	public static void main(String [] args) {
		Board b = new Board();
		b.displayBoard();
		Scanner in = new Scanner(System.in);
		String status = "continue";
		int round=1;
		Disk d1 = new Disk("Red");
		Disk d2 = new Disk("Yellow");
		while(status!="win"&&status!="draw") {
				if(round%2==1) {
					boolean continueInput=true;
					do {
						try {
							System.out.println("Drop a red disk at column(0-6): ");
							int col = in.nextInt();
							d1.dropDisk(b, col);
							b.displayBoard();
							continueInput=false;
							round++;
						}
						catch(InputMismatchException ex) {
							System.out.println("Invalid input");
							in.nextLine(); // discard input
						}
						catch(ArrayIndexOutOfBoundsException ex2) {
							System.out.println("Invalid input");
							in.nextLine(); // discard input
						}
						catch(Exception ex3) {
							System.out.println(ex3.getMessage());
							in.nextLine(); // discard input
						}
					}while(continueInput);
					if(b.win()) {
						status="win";
						System.out.println("Red wins");
						break;
					}
					else if(b.draw()) {
						status="draw";
						System.out.println("draws");
						break;
					}
				}
				
				else {
					boolean continueInput=true;
					do {
						try {
							System.out.println("Drop a yellow disk at column(0-6): ");
							int col = in.nextInt();
							d2.dropDisk(b, col);
							b.displayBoard();
							continueInput=false;
							round++;
						}
						catch(InputMismatchException ex1) {
							System.out.println("Invalid input");
							in.nextLine(); // discard input
						}
						catch(ArrayIndexOutOfBoundsException ex2) {
							System.out.println("Invalid input");
							in.nextLine(); // discard input
						}
						catch(Exception ex3) {
							System.out.println(ex3.getMessage());
							in.nextLine(); // discard input
						}
					}while(continueInput);
					if(b.win()) {
						status="win";
						System.out.println("Yellow wins");
						break;
					}
					else if(b.draw()) {
						status="draw";
						System.out.println("draws");
						break;
					}
				}
		}
		in.close();
	}
}

class Board {
	private String[][] board = {{"| |"," |"," |"," |"," |"," |"," |"},
							    {"| |"," |"," |"," |"," |"," |"," |"},
							    {"| |"," |"," |"," |"," |"," |"," |"},
							    {"| |"," |"," |"," |"," |"," |"," |"},
							    {"| |"," |"," |"," |"," |"," |"," |"},
							    {"| |"," |"," |"," |"," |"," |"," |"}};
	
	public Board() {}
	
	public String[][] getBoard() {
		return board;
	}
	
	public void displayBoard() {
		for(int i=0;i<6;i++) {
			for(int j=0;j<7;j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	public boolean draw() { // determines whether the game draws or not
		for(int i=0;i<6;i++) {
			for(int j=0;j<7;j++) {
				if(board[i][j].indexOf("R")==-1&&board[i][j].indexOf("Y")==-1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isRowConnected() {
		for(int i=0;i<6;i++) {
			for(int j=0;j<4;j++) {
				if((board[i][j].indexOf("R")!=-1&&board[i][j+1].indexOf("R")!=-1&&board[i][j+2].indexOf("R")!=-1&&board[i][j+3].indexOf("R")!=-1)
				 ||(board[i][j].indexOf("Y")!=-1&&board[i][j+1].indexOf("Y")!=-1&&board[i][j+2].indexOf("Y")!=-1&&board[i][j+3].indexOf("Y")!=-1)) 
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isColConnected() {
		for(int j=0;j<7;j++) {
			for(int i=0;i<3;i++) {
				if((board[i][j].indexOf("R")!=-1)&&(board[i+1][j].indexOf("R")!=-1)&&(board[i+2][j].indexOf("R")!=-1)&&(board[i+3][j].indexOf("R")!=-1)
				 ||(board[i][j].indexOf("Y")!=-1)&&(board[i+1][j].indexOf("Y")!=-1)&&(board[i+2][j].indexOf("Y")!=-1)&&(board[i+3][j].indexOf("Y")!=-1)) 
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isDiagonalConnected() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<4;j++) {
				if((board[i][j].indexOf("R")!=-1&&board[i+1][j+1].indexOf("R")!=-1&&board[i+2][j+2].indexOf("R")!=-1&&board[i+3][j+3].indexOf("R")!=-1)
				||(board[i][j].indexOf("Y")!=-1&&board[i+1][j+1].indexOf("Y")!=-1&&board[i+2][j+2].indexOf("Y")!=-1&&board[i+3][j+3].indexOf("Y")!=-1))
				{
					return true;
				}
			}
		}
		
		for(int i=0;i<3;i++) {
			for(int j=6;j>2;j--) {
				if((board[i][j].indexOf("R")!=-1&&board[i+1][j-1].indexOf("R")!=-1&&board[i+2][j-2].indexOf("R")!=-1&&board[i+3][j-3].indexOf("R")!=-1)
				 ||(board[i][j].indexOf("Y")!=-1&&board[i+1][j-1].indexOf("Y")!=-1&&board[i+2][j-2].indexOf("Y")!=-1&&board[i+3][j-3].indexOf("Y")!=-1))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean win() {
		if(isRowConnected()||isColConnected()||isDiagonalConnected()) {
			return true;
		}else {
			return false;
		}
	}
}

class Disk {
	private String color; // Red or Yellow
	
	public Disk(String color) {
		this.color = color;
	}
	
	public String getColor() {return color;}
	
	public void setColor(String color) {this.color = color;}
	
	public void dropDisk(Board b, int col) throws Exception {
		if((b.getBoard()[0][col].indexOf("R")!=-1)||(b.getBoard()[0][col].indexOf("Y")!=-1)) {
			throw new Exception("Column " + col + " has no available entries, try another column.");
		}
		for(int i=5;i>=0;i--) {
			if(b.getBoard()[i][col].indexOf("R")==-1&&b.getBoard()[i][col].indexOf("Y")==-1) {
				if(col==0) {
					b.getBoard()[i][col]="|"+getColor().charAt(0)+"|";
					break;
				}else {
					b.getBoard()[i][col]=getColor().charAt(0)+"|";
					break;
				}
			}
		}
	}
}
