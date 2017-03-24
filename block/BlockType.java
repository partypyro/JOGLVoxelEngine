package block;

import java.util.HashMap;

public class BlockType {
	private static HashMap<Integer, float[]> idColorMap = new HashMap<Integer, float[]>();
	private static HashMap<Integer, String> idNameMap = new HashMap<Integer, String>();
	
	public static void registerBlockType(int id, String name, float red, float green, float blue) {
		float[] colorArray = new float[] {
			    red, green, blue,   red, green, blue,   red, green, blue,   red, green, blue,
			    red, green, blue,   red, green, blue,   red, green, blue,   red, green, blue,
			    red, green, blue,   red, green, blue,   red, green, blue,   red, green, blue,
			    red, green, blue,   red, green, blue,   red, green, blue,   red, green, blue,
			    red, green, blue,   red, green, blue,   red, green, blue,   red, green, blue,
			    red, green, blue,   red, green, blue,   red, green, blue,   red, green, blue
		};
		if(! idColorMap.containsKey(id)) {
			idColorMap.put(id, colorArray);
			idNameMap.put(id, name);
			System.out.println("Block registered with ID " + id);
		}
		else {
			System.out.println("ID " + id + " is taken!");
		}
	}
	public static void registerBlockType(int id, String name, float[] colorArray) {
		if(! idColorMap.containsKey(id)) {
			idColorMap.put(id, colorArray);
			idNameMap.put(id, name);
			System.out.println("Block registered with ID " + id);
		}
		else {
			System.out.println("ID " + id + " is taken!");
		}
	}
	
	public static float[] getColorArray(int id) {
		return idColorMap.get(id);
	}
	public static String getName(int id) {
		return idNameMap.get(id);
	}
}