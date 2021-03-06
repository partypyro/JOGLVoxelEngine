package main;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import block.BlockType;

public class Main implements GLEventListener {
	
	private GLU glu;
	private GL2 gl;
	private static FPSAnimator animator;
	public static Player camera = new Player(32.0f, 50.0f, 32.0f);
	private Renderer renderer;
	
	
	@Override
	public void display(GLAutoDrawable drawable) {
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); //clears the screen and depth buffer
		
		Controller.updateMotion();
		camera.updateWithVelocity();
		
		/*gl.glBegin(GL2.GL_LINES);
		for(int x = 0; x < 16; x++) {
			for(int y = 0; y < 16; y++) {
				gl.glVertex3f(x * 16, 0.0f, y * 16);
				gl.glVertex3f(x * 16, 100.0f, y * 16);
				
			}
		}
		gl.glEnd(); */
		if(camera.isChunkChanged()) {
			renderer.buildAroundPlayer(camera.getPositionVector(), 2);
		}
		
		renderer.render();
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		camera.lookThrough(gl);
		//System.out.println("Position " + camera.getPositionVector());
		//System.out.println("Velocity " + camera.getVelocityVector());
		//System.out.println(animator.getTotalFPS());
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		
		Controller.bind(KeyEvent.VK_W);
		Controller.bind(KeyEvent.VK_S);
		Controller.bind(KeyEvent.VK_A);
		Controller.bind(KeyEvent.VK_D);
		Controller.bind(KeyEvent.VK_LEFT);
		Controller.bind(KeyEvent.VK_RIGHT);
		Controller.bind(KeyEvent.VK_UP);
		Controller.bind(KeyEvent.VK_DOWN);
		Controller.bind(KeyEvent.VK_SPACE);
		Controller.bind(KeyEvent.VK_SHIFT);
		
		this.gl = drawable.getGL().getGL2();
		this.glu = GLU.createGLU(gl);

		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LESS);
		
		BlockType.registerBlockType(0, "Rock", 0.8f, 0.8f, 0.8f);
		BlockType.registerBlockType(1, "Dirt", 0.545f, 0.271f, 0.075f);
		BlockType.registerBlockType(2, "Grass", 0.1f, 0.8f, 0.0f);
		
		World.genWorld();
		renderer = new Renderer(gl);
		renderer.buildAroundPlayer(camera.getPositionVector(), 1);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		if(height <= 0) height = 1;
		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		glu.gluPerspective(45.0f, h, 0.1, 100.0);
		
	}
	
	public static void main(String args[]) {
		// capabilities of GL2 profile
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		// the canvas
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Main main = new Main();
		glcanvas.addGLEventListener(main);
		Controller ctrl = new Controller();
		glcanvas.addKeyListener(ctrl);
		glcanvas.addMouseMotionListener(ctrl);
		glcanvas.setSize(800, 800);
		glcanvas.setFocusable(true);
        glcanvas.requestFocusInWindow();
		
		// create frame and add canvas to frame
		final JFrame frame = new JFrame("Voxel Engine");
		frame.getContentPane().add(glcanvas);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		//Instantiate animator
		animator = new FPSAnimator(glcanvas, 60, true);
		animator.start();
	}
}