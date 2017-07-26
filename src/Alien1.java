import java.awt.Color;
import java.awt.Graphics;

public class Alien1 extends GameObject
{
	@Override
	public GameObjectType getType()
	{
		return GameObjectType.Alien1;
	}
	
	@Override
	public void draw(Graphics page)
	{
		page.setColor(Color.red);
		page.drawRect(getX(), getY(),getStandardSize(), getStandardSize());
	}
}
