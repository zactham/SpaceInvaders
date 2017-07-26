import java.awt.Color;
import java.awt.Graphics;

public class Alien1 extends GameObject
{
	@Override
	public GameObjectType getType()
	{
		return GameObjectType.Alien1;
	}
	
	public void draw(Graphics page, Color outline)
	{
		page.setColor(Color.red);
		page.drawRect(getX(), getY(),getStandardSize(), getStandardSize());
	}
}
