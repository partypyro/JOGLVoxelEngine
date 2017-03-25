package main;

import java.util.ArrayList;
import java.util.List;

import block.Block;

public class World {
	public static List<List<Chunk>> worldArray = new ArrayList<List<Chunk>>();
	private static final float WIDTH = 25;
	private static final float DEPTH = 25;
	private static final double SCALE = 400.0;
	
	/*public static void genWorld() {
		ArrayList<Chunk> worldSlice;
		for(int x = 0; x < WIDTH; x++) {
			worldSlice = new ArrayList<Chunk>();
			for(int y = 0; y < DEPTH; y++) {
				worldSlice.add(y, new Chunk(x, y));
			}
			worldArray.add(x, worldSlice);
		}
	}*/
	public static void genWorld() {
		ArrayList<Chunk> worldSlice;
		Block[][][] chunkArray;
		
		SimplexNoise.seed = (long) 10041241.0;
		for(int x = 0; x < WIDTH; x++) {
			worldSlice = new ArrayList<Chunk>();
			for(int y = 0; y < DEPTH; y++) {
				chunkArray = new Block[16][128][16];
				for(int a = 0; a < chunkArray.length; a++) {
					for(int c = 0; c < chunkArray[1][1].length; c++) {
						int d =	(int)(SimplexNoise.noise((a + x * 16) / SCALE, (c + y * 16) / SCALE)) + 50;
						for(int b = 0; b <= d; b++) {
							chunkArray[a][b][c] = new Block(Math.random() > 0.5 ? 1 : 0);
						}
					}
				}
				worldSlice.add(y, new Chunk(x, y, chunkArray));
			}
			worldArray.add(x, worldSlice);
		}
	}
	public static boolean isChunkAt(int chunkX, int chunkZ) {
		try {
			return (worldArray.get(chunkX).get(chunkZ) != null);
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	public static boolean isBlockAt(int worldX, int worldY, int worldZ) {
		return (worldArray.get(worldX / 16).get(worldZ / 16).getAt(worldX % 16, worldY, worldZ % 16)) != null;
	}
	public static Block getBlockAt(int worldX, int worldY, int worldZ) {
		return (worldArray.get(worldX / 16).get(worldZ / 16).getAt(worldX % 16, worldY, worldZ % 16));
	}
	public static int getBlockIDAt(int worldX, int worldY, int worldZ) {
			return (worldArray.get(worldX / 16).get(worldZ / 16).getAt(worldX % 16, worldY, worldZ % 16)).getID();
	}
}