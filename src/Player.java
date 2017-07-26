import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject
{
	public GameObjectType getType()
	{
		return GameObjectType.Player;
	}
	
	public void draw(Graphics page, Color outline)
	{
		page.setColor(Color.red);
		page.drawRect(getX(), getY(), getStandardSize(), getStandardSize());
	}
}
