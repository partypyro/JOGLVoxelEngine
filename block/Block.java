package block;

public class Block {
	private float[] vertexArray;
	private int blockID;
	int x, y, z;
	
	public Block(int x, int y, int z, int blockID) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.blockID = blockID;
		this.vertexArray = new float[] {
			     x    , y    , z    ,    x    , y    , z + 1,    x    , y + 1, z + 1,    x    , y + 1, z    , //LEFT
			     x + 1, y    , z    ,    x + 1, y    , z + 1,    x + 1, y + 1, z + 1,    x + 1, y + 1, z    , //RIGHT
			     x    , y    , z    ,    x    , y    , z + 1,    x + 1, y    , z + 1,    x + 1, y    , z    , //BOTTOM
			     x    , y + 1, z    ,    x    , y + 1, z + 1,    x + 1, y + 1, z + 1,    x + 1, y + 1, z    , //TOP
			     x    , y    , z    ,    x    , y + 1, z    ,    x + 1, y + 1, z    ,    x + 1, y    , z    , //FRONT
			     x    , y    , z + 1,    x    , y + 1, z + 1,    x + 1, y + 1, z + 1,    x + 1, y    , z + 1 //BACK
		};
	}
	
	public float[] getBlockArray() {
		return vertexArray;
	}
	public static float[] makeMesh(int x, int y, int z) {
		return new float[] {
			     x    , y    , z    ,    x    , y    , z + 1,    x    , y + 1, z + 1,    x    , y + 1, z    , //LEFT
			     x + 1, y    , z    ,    x + 1, y    , z + 1,    x + 1, y + 1, z + 1,    x + 1, y + 1, z    , //RIGHT
			     x    , y    , z    ,    x    , y    , z + 1,    x + 1, y    , z + 1,    x + 1, y    , z    , //BOTTOM
			     x    , y + 1, z    ,    x    , y + 1, z + 1,    x + 1, y + 1, z + 1,    x + 1, y + 1, z    , //TOP
			     x    , y    , z    ,    x    , y + 1, z    ,    x + 1, y + 1, z    ,    x + 1, y    , z    , //FRONT
			     x    , y    , z + 1,    x    , y + 1, z + 1,    x + 1, y + 1, z + 1,    x + 1, y    , z + 1 //BACK
		};
	}
	
	public int getID() {
		return this.blockID;
	}
}