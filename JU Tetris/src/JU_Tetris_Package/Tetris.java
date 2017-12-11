package JU_Tetris_Package;

import javax.swing.JFrame;

import JU_Tetris_Package.Board.Keyboard;

public class Tetris extends JFrame{	
	
	public Tetris() {
		Board board = new Board();
		
		add(board);
		
		board.start();
		
		setSize(300,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		Tetris tetris = new Tetris();
	}
}
