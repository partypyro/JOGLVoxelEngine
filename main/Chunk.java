package main;

import block.Block;
import block.BlockType;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

public class Chunk {
	private Block[][][] chunkArray = new Block[WIDTH][HEIGHT][WIDTH];
	private ArrayList<ArrayList<ArrayList<float[]>>> meshArray = new ArrayList<ArrayList<ArrayList<float[]>>>();
	private int worldX, worldY;
	private int chunkX, chunkZ;
	private final static int WIDTH = 16;
	private final static int HEIGHT = 64;
	private final static int DEPTH = 16;
	
	Chunk(int chunkX, int chunkZ) {
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
		this.worldX = chunkX * WIDTH;
		this.worldY = chunkZ * DEPTH;
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT / 2; y++) {
				for(int z = 0; z < DEPTH; z++) {
					if(Math.random() > 0.7) {
						if(Math.random() > 0.5)
							this.createAt(x, y, z, 0);
						else
							this.createAt(x, y, z, 1);
					}
				}
			}
		}
	}
	public FloatBuffer computeVertexBuffer() {
		FloatBuffer buffer = ByteBuffer.allocateDirect(900000).asFloatBuffer();
		buffer.clear();
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				for(int z = 0; z < DEPTH; z++) {
					if(!(chunkArray[x][y][z] == null)) {
						buffer.put(chunkArray[x][y][z].getBlockArray());
					}
				}
			}
		}
		buffer.flip();
		System.out.println("DONE WITH CHUNK MESH | SIZE: " + buffer.toString());
		return buffer;
	}
	public FloatBuffer computeColorBuffer() {
		FloatBuffer buffer = ByteBuffer.allocateDirect(900000).asFloatBuffer();
		buffer.clear();
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				for(int z = 0; z < DEPTH; z++) {
					if(!(chunkArray[x][y][z] == null)) {
						buffer.put(BlockType.getColorArray(chunkArray[x][y][z].getID()));
					}
				}
			}
		}
		buffer.flip();
		System.out.println("DONE WITH COLOR MESH | SIZE: " + buffer.toString());
		return buffer;
	}
	
	public void destroyAt(int x, int y, int z) {
		chunkArray[x][y][z] = null;
	}
	public void createAt(int x, int y, int z, int blockID) {
		chunkArray[x][y][z] = new Block(worldX + x, y, worldY + z, blockID);
	}
	public Block getAt(int x, int y, int z) {
		return chunkArray[x][y][z];
	}
	
	public int getChunkX() { return chunkX; }
	public int getChunkZ() { return chunkZ; }
	public Block[][][] getChunkArray() { return chunkArray; }
}