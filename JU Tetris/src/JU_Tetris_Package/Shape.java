package JU_Tetris_Package;

import java.util.Random;

public class Shape {
	Block block[] = new Block[7];
	Block cur_block;
	Shape tmp_shape;
	private int[][] coordinates = new int[4][2];
	
	public Shape() {
		Random r = new Random();
		block = Block.values();
		
		cur_block = block[r.nextInt(7)+1];
		for(int i=0; i<4; i++) {
			for(int j=0; j<2; j++) {
				coordinates[i][j]=cur_block.block_array[i][j];
			}
		}
	}
	
	public Shape(int num) {
		block = Block.values();
		
		cur_block = block[num];
		for(int i=0; i<4; i++) {
			for(int j=0; j<2; j++) {
				coordinates[i][j]=cur_block.block_array[i][j];
			}
		}
	}
	
	public int getX(int index) {
		return coordinates[index][0];
	}
	
	public int getY(int index) {
		return coordinates[index][1];
	}
	
	private void setX(int index, int value) {
		coordinates[index][0] = value;
	}
	
	private void setY(int index, int value) {
		coordinates[index][1] = value;
	}
	
	public Shape rotateBlock() {
		if(cur_block == Block.EShape) {
			tmp_shape = new Shape(5);
			return tmp_shape;
		}
		
		int tmp_coordinates[][] = new int[4][2];
		
		tmp_shape = Board.cur_shape;
		
		for(int i=0; i<4; i++) {
			tmp_coordinates[i][0]=getX(i);
			tmp_coordinates[i][1]=getY(i);
			
			setX(i, -tmp_coordinates[i][1]);
			setY(i, tmp_coordinates[i][0]);
		}
		
		return tmp_shape;
	}
}
