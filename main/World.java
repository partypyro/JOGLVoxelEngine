package main;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import block.Block;

public class World {
	private static List<List<Chunk>> worldArray = new ArrayList<List<Chunk>>();
	private static ArrayList<ArrayList<FloatBuffer>> vertexArray = new ArrayList<ArrayList<FloatBuffer>>();
	public static FloatBuffer vertexBuffer = ByteBuffer.allocateDirect(100000000).asFloatBuffer();
	private static ArrayList<ArrayList<FloatBuffer>> colorArray = new ArrayList<ArrayList<FloatBuffer>>();
	public static FloatBuffer colorBuffer = ByteBuffer.allocateDirect(100000000).asFloatBuffer();
	private static final float WIDTH = 3;
	private static final float DEPTH = 3;
	
	public static void genWorld() {
		ArrayList<Chunk> worldSlice;
		for(int x = 0; x < WIDTH; x++) {
			worldSlice = new ArrayList<Chunk>();
			for(int y = 0; y < DEPTH; y++) {
				worldSlice.add(y, new Chunk(x, y));
			}
			worldArray.add(x, worldSlice);
		}
	}
	
	public static void populateVertexBufferArray() {
		for(int x = 0; x < worldArray.size(); x++) {
			ArrayList<FloatBuffer> slice = new ArrayList<FloatBuffer>();
			for(int y = 0; y < worldArray.get(x).size(); y++) {
				slice.add(worldArray.get(x).get(y).computeVertexBuffer());
			}
			vertexArray.add(x, slice);
		}
		System.out.println("DONE WITH POPULATION");
	}
	public static void populateColorVertexArray() {
		for(int x = 0; x < worldArray.size(); x++) {
			ArrayList<FloatBuffer> slice = new ArrayList<FloatBuffer>();
			for(int y = 0; y < worldArray.get(x).size(); y++) {
				slice.add(worldArray.get(x).get(y).computeColorBuffer());
			}
			vertexArray.add(x, slice);
		}
		System.out.println("DONE WITH POPULATION");
	}
	
	public static void createVertexBuffer() {
		vertexBuffer.clear();
		/*for(int x = 0; x < vertexArray.size(); x++) {
			for(int y = 0; y < vertexArray.get(x).size(); y++) {
				vertexBuffer.put(vertexArray.get(x).get(y));
			}
		}*/
		for(int x = 0; x < worldArray.size(); x++) {
			for(int y = 0; y < worldArray.get(x).size(); y++) {
				vertexBuffer.put(worldArray.get(x).get(y).computeVertexBuffer());
			}
		}
		//buffer.rewind();
		vertexBuffer.flip();
		System.out.println("DONE WITH COMPOSITE " + vertexBuffer.toString());
	}
	public static void createColorBuffer() {
		colorBuffer.clear();
		/*for(int x = 0; x < colorArray.size(); x++) {
			for(int y = 0; y < colorArray.get(x).size(); y++) {
				colorBuffer.put(colorArray.get(x).get(y));
			}
		}*/
		for(int x = 0; x < worldArray.size(); x++) {
			for(int y = 0; y < worldArray.get(x).size(); y++) {
				colorBuffer.put(worldArray.get(x).get(y).computeColorBuffer());
			}
		}
		colorBuffer.flip();
		System.out.println("DONE WITH COLOR COMPOSITE " + colorBuffer.toString());
	}
	
	public static int getBufferSize() {
		int size = 0;
		for(int x = 0; x < vertexArray.size(); x++) {
			for(int y = 0; y < vertexArray.get(x).size(); y++) {
				size += vertexArray.get(x).get(y).limit();
			}
		}
		return size;
	}
	
	public static void recomputeMeshAt(int chunkX, int chunkZ) {
		vertexArray.get(chunkX).set(chunkZ, worldArray.get(chunkX).get(chunkZ).computeVertexBuffer());
	}
	
	public static void destroyBlockAt(int worldX, int worldY, int worldZ) {
		int chunkX = worldX % 16;
		int chunkY = worldY % 16;
		int chunkZ = worldZ % 16;
		worldArray.get(worldX / 16).get(worldZ / 16).destroyAt(chunkX, chunkY, chunkZ);
	}
	
	public static void createBlockAt(int worldX, int worldY, int worldZ, int blockID) {
		worldArray.get(worldX / 16).get(worldZ / 16).createAt(worldX, worldY, worldZ, blockID);
	}
	public static boolean isBlockAt(int worldX, int worldY, int worldZ, int blockID) {
		return (worldArray.get(worldX / 16).get(worldZ / 16).getAt(worldX, worldY, worldZ)) == null;
	}
	public static Block getBlockAt(int worldX, int worldY, int worldZ) {
		return (worldArray.get(worldX / 16).get(worldZ / 16).getAt(worldX, worldY, worldZ));
	}
}