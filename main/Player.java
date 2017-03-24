package main;

import com.jogamp.opengl.GL2;

public class Player {
	private Vector3f position;
	private Vector3f positionEnd;
	private Vector3f velocity;
	private float yaw, pitch;
	private static final float HEIGHT = 1.5f;
	private static final float MAXSPEED = 0.30f;
	private static final float WIDTH = 0.5f;
	private static final float DEPTH = 0.5f;
	
	public Player(float x, float y, float z, float pitch, float yaw) {
		position = new Vector3f(x, y, z);
		positionEnd = new Vector3f(x + WIDTH, y + HEIGHT, z + DEPTH);
		velocity = new Vector3f(0.0f, 0.0f, 0.0f);
		this.yaw = yaw;
		this.pitch = pitch;
	}
	public Player(float x, float y, float z) {
		this(x, y, z,
			 0.0f, 0.0f);
	}
	
	public void yaw(float amount) {
		yaw += amount;
	}
	public void pitch(float amount) {
		pitch += amount;
	}
	
	//velocity functions
	public void walk(float dv) {
		if(velocity.getX() < MAXSPEED && velocity.getX() > -MAXSPEED)
			velocity.setX(velocity.getX() + (float) (dv * Math.sin(Math.toRadians(yaw))));
		if(velocity.getZ() < MAXSPEED && velocity.getZ() > -MAXSPEED)
			velocity.setZ(velocity.getZ() - (float) (dv * Math.cos(Math.toRadians(yaw))));
	}
	public void strafe(float dv) {
		if(velocity.getX() < MAXSPEED && velocity.getX() > -MAXSPEED)
			velocity.setX(velocity.getX() + (float) (dv * Math.sin(Math.toRadians(yaw - 90))));
		if(velocity.getZ() < MAXSPEED && velocity.getZ() > -MAXSPEED)
			velocity.setZ(velocity.getZ() - (float) (dv * Math.cos(Math.toRadians(yaw - 90))));
	}
	public void jump(float dy) {
		position.setY(position.getY() + dy);
	}
	public void updateWithVelocity() {
		/*implement "gravity"
		if(velocity.getY() > 0.1f && position.getY() >= 1.0f)
			velocity.setY(velocity.getY() - 0.1f);
		else if(velocity.getY() > 0.0f && velocity.getY() < 0.1f)
			velocity.setY(0.0f);
		if(position.getY() >= 0.0f) {
			position.setY((float) (position.getY() + (velocity.getY())));
		}*/
		
		//update date pos based on velocity
		position.setZ(position.getZ() + velocity.getZ());
		position.setX(position.getX() + velocity.getX());
		
		if(velocity.getX() > 0)
			velocity.setX(velocity.getX() - 0.01f);
		if(velocity.getZ() > 0)
			velocity.setZ(velocity.getZ() - 0.01f);
		if(velocity.getX() < 0)
			velocity.setX(velocity.getX() + 0.01f);
		if(velocity.getZ() < 0)
			velocity.setZ(velocity.getZ() + 0.01f);
		
		if(velocity.getX() > -0.01f && velocity.getX() < 0.01f)
			velocity.setX(0.0f);
		if(velocity.getZ() > -0.01f && velocity.getZ() < 0.01f)
			velocity.setZ(0.0f);
	}
	
	//move the world around the camera
	public void lookThrough(GL2 gl) {
		gl.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(yaw  , 0.0f, 1.0f, 0.0f);
		gl.glTranslatef(-position.getX(), -position.getY() - HEIGHT, -position.getZ());
	}
	
	public Vector3f getPositionVector() { return position; }
	public Vector3f getVelocityVector() { return velocity; }
	public float getHeight() { return HEIGHT; }
	public float getDepth() { return DEPTH; }
	public float getWidth() { return WIDTH; }
	public float getMaxSpeed() { return MAXSPEED; }
}