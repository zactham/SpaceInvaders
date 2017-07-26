import java.awt.Color;
import java.awt.Graphics;

public class Alien3 extends GameObject
{
	
	@Override
	public GameObjectType getType()
	{
		return GameObjectType.Alien3;
	}
	
	@Override
	public void draw(Graphics page)
	{
		page.setColor(new Color(165, 42, 42));
		page.drawRect(getX(), getY(), getStandardSize(), getStandardSize());
	}
}
