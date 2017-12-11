package JU_Tetris_Package;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{

	public static final int BOARD_WIDTH = 10;
	public static final int BOARD_HEIGHT = 22;
	Timer timer = new Timer(300, this);
	boolean isStarted = false;
	boolean isFallingFinished = true;
	Block board[] = new Block[BOARD_HEIGHT * BOARD_WIDTH];
	private int curX, curY;
	static Shape cur_shape;
	
	public Board() {
		clearBoard();
		addKeyListener(new Keyboard());
		setFocusable(true);
	}
	
	public void start() {
		if(isStarted) {
			System.out.println("start");
			timer.start();
		}
		else {
			System.out.println("stop");
			timer.stop();
		}
	}
	
	private void clearBoard() {
		for(int i=0; i<BOARD_HEIGHT; i++) {
			for(int j=0; j<BOARD_WIDTH; j++) {
				board[i*BOARD_WIDTH + j] = Block.NoShape;
			}
		}
	}
	
	private void newShape() {
		cur_shape = new Shape();
		curX = BOARD_WIDTH >> 1;
		curY = BOARD_HEIGHT-1;
		isFallingFinished=false;
		repaint();
	}
	
	private boolean tryMove(int curX, int curY, Shape shape) {
		for(int i=0; i<4; i++) {
			int x = curX + shape.getX(i);
			int y = curY - shape.getY(i);
			
			if(x<0 || x>=BOARD_WIDTH || y<0 || y>=BOARD_HEIGHT)
				return false;
			
			if(board[y * BOARD_WIDTH + x] != Block.NoShape)
				return false;
		}
		
		this.curX = curX;
		this.curY = curY;
		this.cur_shape = shape;
		repaint();
		return true;
	}
	
	private void blockDrop() {
		if(!tryMove(curX, curY-1, cur_shape)) {
			for(int i=0; i<4; i++) {
				int x = curX + cur_shape.getX(i);
				int y = curY - cur_shape.getY(i);
				
				if(y * BOARD_WIDTH + x < BOARD_HEIGHT*BOARD_WIDTH ) {
					board[y * BOARD_WIDTH + x] = cur_shape.cur_block;
				}
				else {
					timer.stop();
				}
			}
			isFallingFinished=true;
		}
	}
	
	private void checkRemoveLine() {
		
		for(int i=BOARD_HEIGHT-1; i>=0; i--) {
			boolean isLineFull = true;
			
			for(int j=0; j<BOARD_WIDTH; j++) {
				if(board[i*BOARD_WIDTH+j] == Block.NoShape) {
					isLineFull = false;
					break;
				}
			}
			
			if(isLineFull) {
				for(int k=i; k<BOARD_HEIGHT; k++) {
					for(int j=0; j<BOARD_WIDTH; j++) {
						if((k+1)*BOARD_WIDTH+j > BOARD_HEIGHT*BOARD_WIDTH - 1) {
							board[k*BOARD_WIDTH+j] = Block.NoShape;
						}
						else {
							board[k*BOARD_WIDTH+j] = board[(k+1)*BOARD_WIDTH+j];
						}
					}
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		if(isFallingFinished==false) {
			blockDrop();
		}
		else {
			checkRemoveLine();
			newShape();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		g.drawLine(0, BOARD_HEIGHT*getOneBlockHeight(), BOARD_WIDTH*getOneBlockWidth()+10, BOARD_HEIGHT*getOneBlockHeight());
		
		for(int i=0; i<BOARD_HEIGHT-1; i++) {
			for(int j=0; j<BOARD_WIDTH; j++) {
				if(board[i*BOARD_WIDTH+j] != Block.NoShape) {
					g.drawRoundRect(getOneBlockWidth()*j, getOneBlockHeight()*(BOARD_HEIGHT-1-i), getOneBlockWidth(), getOneBlockHeight(), 10, 10);
				}
			}
		}
		
		if(isStarted == true) {
			for(int i=0; i<4; i++) {
				int x = curX + cur_shape.getX(i);
				int y = curY - cur_shape.getY(i);
				
				g.drawRoundRect(x*getOneBlockWidth(), ((BOARD_HEIGHT-1)-y)*getOneBlockHeight(), getOneBlockWidth(), getOneBlockHeight(), 10, 10);
			}
		}
	}
	
	private int getOneBlockWidth() {
		return (int)getSize().getWidth()/BOARD_WIDTH;
	}
	
	private int getOneBlockHeight() {
		return (int)getSize().getHeight()/BOARD_HEIGHT;
	}
	
	class Keyboard extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
			int keyCode = e.getKeyCode();
			
			switch(keyCode) {
			case KeyEvent.VK_ENTER:
				if(isStarted == false) {
					isStarted = true;
					start();
				}
				break;
			case KeyEvent.VK_LEFT:
				tryMove(curX-1, curY, cur_shape);
				break;
			case KeyEvent.VK_RIGHT:
				tryMove(curX+1, curY, cur_shape);
				break;
			case KeyEvent.VK_DOWN:
				tryMove(curX, curY-1, cur_shape);
				break;
			case KeyEvent.VK_UP:
				tryMove(curX, curY, cur_shape.rotateBlock());
				break;
			}
		}
	}
}
