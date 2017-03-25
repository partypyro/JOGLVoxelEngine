package main;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import com.jogamp.opengl.GL2;
import com.sun.prism.impl.BufferUtil;

import block.BlockType;

public class Renderer {
	private GL2 gl;
	private ArrayList<ArrayList<FloatBuffer>> vertexArray = new ArrayList<ArrayList<FloatBuffer>>();
	private ArrayList<ArrayList<FloatBuffer>> colorArray = new ArrayList<ArrayList<FloatBuffer>>();

	public Renderer(GL2 gl) {
		this.gl = gl;
	}
	
	public void render() {
		for (int i = 0; i < vertexArray.size(); i++) {
			for (int j = 0; j < vertexArray.get(i).size(); j++) {
				gl.glVertexPointer(3, GL2.GL_FLOAT, 0, vertexArray.get(i).get(j));
				gl.glColorPointer(3, GL2.GL_FLOAT, 0, colorArray.get(i).get(j));
				//gl.glInterleavedArrays(GL2.GL_V3F, 0, vertexArray.get(i).get(j));
				gl.glDrawArrays(GL2.GL_QUADS, 0, vertexArray.get(i).get(j).remaining() / 3);
			}
		}
	}

	public void buildAroundPlayer(Vector3f pos, int chunkDistance) {
		long start = System.currentTimeMillis();
		vertexArray.clear();
		colorArray.clear();
		ArrayList<FloatBuffer> tempArray;
		ArrayList<FloatBuffer> tempColorArray;
		
		int x = (int) Math.round(pos.getX());
		int z = (int) Math.round(pos.getY());
		int chunkX = x / 16;
		int chunkZ = z / 16;
		
		int indexX = 0, indexZ = 0;
		for (int a = chunkX - chunkDistance; a < chunkX + chunkDistance; a++) {
			tempArray = new ArrayList<FloatBuffer>();
			tempColorArray = new ArrayList<FloatBuffer>();
			for (int b = chunkZ - chunkDistance; b < chunkZ + chunkDistance; b++) {
				if(World.isChunkAt(a, b)) {
					tempArray.add(indexZ, makeChunkBuffer(a, b));
					tempColorArray.add(indexZ, makeChunkColorBuffer(a, b));
					indexZ++;
				}
				indexZ = 0;
			}
			vertexArray.add(indexX, tempArray);
			colorArray.add(indexX, tempColorArray);
			indexX++;
		}
		long end = System.currentTimeMillis();
		System.out.println((end - start) + "ms");
	}

	
	//TODO: Only put visible faces into the buffer
	//TODO: Combine both methods to return one interleaved C3F_V3F array
	public FloatBuffer makeChunkBuffer(int x, int z) {
		FloatBuffer buffer = BufferUtil.newFloatBuffer(1600000);
		buffer.clear();
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 128; j++) {
				for (int k = 0; k < 16; k++) {
					if (World.isBlockAt(i + (x * 16), j, k + (z * 16))) {
						buffer.put(makeArray(i + (x * 16), j, k + (z * 16)));
					}
				}
			}
		}
		buffer.flip();
		return buffer;
	}
	public FloatBuffer makeChunkColorBuffer(int x, int z) {
		FloatBuffer buffer = BufferUtil.newFloatBuffer(1600000);
		buffer.clear();
		if (World.isChunkAt(x, z)) {
			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 128; j++) {
					for (int k = 0; k < 16; k++) {
						if (World.isBlockAt(i + (x * 16), j, k + (z * 16)))
							buffer.put(BlockType.getColorArray(World.getBlockIDAt(i + (x * 16), j, k + (z * 16))));
					}
				}
			}
		}
		buffer.flip();
		return buffer;
	}

	private float[] makeArray(int x, int y, int z) {
		return new float[] { x, y, z, x, y, z + 1, x, y + 1, z + 1, x, y + 1, z, // LEFT
				x + 1, y, z, x + 1, y, z + 1, x + 1, y + 1, z + 1, x + 1, y + 1, z, // RIGHT
				x, y, z, x, y, z + 1, x + 1, y, z + 1, x + 1, y, z, // BOTTOM
				x, y + 1, z, x, y + 1, z + 1, x + 1, y + 1, z + 1, x + 1, y + 1, z, // TOP
				x, y, z, x, y + 1, z, x + 1, y + 1, z, x + 1, y, z, // FRONT
				x, y, z + 1, x, y + 1, z + 1, x + 1, y + 1, z + 1, x + 1, y, z + 1 // BACK
		};
	}
}