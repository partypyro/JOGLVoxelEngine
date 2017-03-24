package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

public class Controller extends KeyAdapter implements MouseMotionListener {
	
	private static HashMap<Integer, Boolean> keyMap = new HashMap<Integer, Boolean>();
	/*private int px, py;
	private static int dx;
	private static int dy;
	private static Robot robot;
	
	Controller() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	} */
	
	@Override
	public void keyPressed(KeyEvent e) {
		//walk
		keyMap.put(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyMap.put(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		/*dy = e.getY() - py;
		dx = e.getX() - px;
		py = e.getX();
		px = e.getY(); */
	}
	
	public static void bind(int keyCode) {
		keyMap.put(keyCode, false);
	}
	public static HashMap<Integer, Boolean> getKeyMap() {
		return keyMap;
	}
	
	public static void updateMotion() {
		if(keyMap.get(KeyEvent.VK_W)) {
			Main.camera.walk(0.03f);
		}
		if(keyMap.get(KeyEvent.VK_S)) {
			Main.camera.walk(-0.03f);
		}
		if(keyMap.get(KeyEvent.VK_A)) {
			Main.camera.strafe(0.03f);
		}
		if(keyMap.get(KeyEvent.VK_D)) {
			Main.camera.strafe(-0.03f);
		}
		if(keyMap.get(KeyEvent.VK_SPACE)) {
			Main.camera.jump(1.0f);
		}
		if(keyMap.get(KeyEvent.VK_SHIFT)) {
			Main.camera.jump(-1.0f);
		}
		//aim
		if(keyMap.get(KeyEvent.VK_LEFT)) {
			Main.camera.yaw(-2.0f);
		}
		if(keyMap.get(KeyEvent.VK_RIGHT)) {
			Main.camera.yaw(2.0f);
		}
		if(keyMap.get(KeyEvent.VK_UP)) {
			Main.camera.pitch(-2.0f);
		}
		if(keyMap.get(KeyEvent.VK_DOWN)) {
			Main.camera.pitch(2.0f);
		}
		
		//Main.camera.yaw(dx);
		//Main.camera.pitch(dy);
	}
}