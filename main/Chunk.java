package main;

import block.Block;
import block.BlockType;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

public class Chunk {
	private int chunkX, chunkZ;
	private Block[][][] chunkArray;
	
	Chunk(int chunkX, int chunkZ, Block[][][] chunkArray) {
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
		this.chunkArray = chunkArray;
	}
	
	public Block getAt(int x, int y, int z) {
		return chunkArray[x][y][z];
	}
	
	public int getChunkX() { return chunkX; }
	public int getChunkZ() { return chunkZ; }
	public Block[][][] getChunkArray() { return chunkArray; }
}