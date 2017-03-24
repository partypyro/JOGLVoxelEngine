package main;

public class Vector3f{
	private static final float TOLERANCE = 0.0001f;
	private float x, y, z;
	
	/**
	 * Return a new Vector3f initialized to the zero vector.
	 */
	public Vector3f() {
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
	}
	
	/**
	 * Return a new Vector3f with the specified initial values
	 * @param v0 Value of the first element.
	 * @param v1 Value of the second element.
	 * @param v2 Value of the third element.
	 */
	public Vector3f(float v0, float v1, float v2) {
		this.x = v0;
		this.y = v1;
		this.z = v2;
	}
	
	/**
	 * Return a new Vector3f that represents the array.
	 * @param src The source array for this Vector3f.
	 */
	public Vector3f(float[] src) {
		/* Make sure that src has three elements. */
		if (src.length != 3) {
			throw new IllegalArgumentException("Source array must have exactly three arguments.");
		}
		x = src[0];
		y = src[1];
		z = src[2];
	}
	
	/**
	 * Return a new Vector3f that is a copy of the input vector.
	 * @param src The source Vector3f for this Vector3f.
	 */
	public Vector3f(Vector3f src) {
		x = src.x;
		y = src.y;
		z = src.z;
	}
	
	/**
	 * Make a copy of this Vector3f
	 * @return A copy of this Vector3f
	 */
	public Vector3f copy() {
		return new Vector3f(this);
	}
	
	/**
	 * Get the array representation of this Vector3f.
	 * @return The array representation of this Vector3f.
	 */
	public float[] toArray() {
		return new float[] {x, y, z};
	}	

	/**
	 * Vector addition (performed in place).
	 * @param w The vector to add from this vector.
	 */
	public void add(Vector3f w) {
		x += w.x;
		y += w.y;
		z += w.z;
	}
	
	/**
	 * Vector subtraction (performed in place).
	 * @param w The vector to subtract from this vector.
	 */
	public void sub(Vector3f w) {
		x -= w.x;
		y -= w.y;
		z -= w.z;
	}

	/**
	 * Scale this vector by a constant factor.
	 * @param s The constant to scale this vector by.
	 */
	public void scale(float s) {
		x *= s;
		y *= s;
		z *= s;
	}
	
	/**
	 * Normalize this vector (performed in place).
	 * @throws ZeroVectorException If this vector is the zero vector, throw an
	 * error. 
	 *//*
	public void normalize() throws ZeroVectorException {
		float norm = norm();
		if (norm == 0.0f) {
			throw new ZeroVectorException("Cannot normalize the zero vector.");
		}
		scale(1.0f / norm);
	} */

	/**
	 * Compute the norm of this vector.
	 * @return The norm of this vector.
	 * @throws ZeroVectorException If this vector is the zero vector, throw an
	 * error.
	 */
	public float norm() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	/**
	 * Compute the dot product between this vector and another vector.
	 * @param w The vector to dot with this vector.
	 * @return The dot product between the two vectors.
	 */
	public float dot(Vector3f w) {
		return x * w.x + y * w.y + z * w.z;
	}
	
	/**
	 * Cross product (performed in place). Note that a.cross(b) is not the same
	 * as b.cross(a)
	 * @param w The vector to cross with this vector. 
	 */
	public void cross(Vector3f w) {
		float tmp0, tmp1, tmp2;		
		tmp0 = y * w.z - z * w.y;
		tmp1 = x * w.z - z * w.x;
		tmp2 = x * w.y - y * w.x;
		
		x =  tmp0;
		y = -tmp1;
		z =  tmp2;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vector3f) {
			Vector3f vec = (Vector3f) obj;
			if (withinTolerance(x, vec.x) &&
			    withinTolerance(y, vec.y) &&
			    withinTolerance(z, vec.z)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param element
	 * @param idx
	 * @return
	 */
	private boolean withinTolerance(float f0, float f1) {
		return (f1 - f0) < TOLERANCE && (f1 - f0) > -TOLERANCE; 
	}
	@Override
	public String toString() {
		return("VARS| X: " + this.getX() + " Y: " + this.getY() + " Z: " + this.getZ());
	}
	
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public float getZ() { return this.z; }
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public void setZ(float z) { this.z = z; }
}