import java.awt.Color;
import java.awt.Graphics;

public class Alien4 extends GameObject{

	@Override
	public GameObjectType getType()
	{
		return GameObjectType.Alien4;
	}
	
	@Override
	public void draw(Graphics page)
	{
		page.setColor(Color.YELLOW);
		page.drawRect(getX(), getY(), getStandardSize(), getStandardSize());
	}
}
