package block;

import main.Player;
import main.Vector3f;
import main.World;

public class BlockBounds {
	// TODO: implement collision detection
	
	//1 = LEFT
	//2 = RIGHT
	//3 = TOP
	//4 = BOTTOM
	//5 = BACK
	//6 = FRONT
	//7 = NO COLLSION
	public static int getCollsion(Player player) {
		int direction = 0;
		Vector3f pos = player.getPositionVector();
		
		return direction;
	}
}
