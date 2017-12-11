package JU_Tetris_Package;

public enum Block {
	NoShape(new int[][] {{0,0},{0,0},{0,0},{0,0}}),
	AShape(new int[][] {{0,1},{0,0},{1,0},{1,-1}}), //s
	BShape(new int[][] {{0,1},{0,0},{-1,0},{-1,-1}}), //mirrored s
	CShape(new int[][] {{0,2},{0,1},{0,0},{0,-1}}), //l
	DShape(new int[][] {{-1,0},{0,0},{1,0},{0,1}}), //¤Ç
	EShape(new int[][] {{0,0},{0,-1},{1,0},{1,-1}}), //¤±
	FShape(new int[][] {{1,1},{0,1},{0,0},{0,-1}}), //r
	GShape(new int[][] {{1,1},{0,1},{1,0},{1,-1}})  //mirrored r
	;
	
	public int block_array[][];
	
	private Block(int block_array[][]) {
		this.block_array = block_array;
	}
}
