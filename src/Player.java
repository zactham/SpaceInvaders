import java.awt.Color;
import java.awt.Graphics;

public class Player
{
	public GameObjectType getType()
	{
		return GameObjectType.Player;
	}
	
	public void draw(Graphics page, Color outline)
	{
		page.setColor(Color.red);
		page.drawRect(GameObject.getX(), GameObject.getY(), 10, 10);
	}
}
