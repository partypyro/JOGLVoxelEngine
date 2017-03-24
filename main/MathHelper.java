package main;

public class MathHelper {
	public static float[] concat(float[] a, float[] b) {
		if(a == null) return b;
		if(b == null) return a;
		int aLen = a.length;
		int bLen = b.length;
		float[] c = new float[aLen+bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}
}
