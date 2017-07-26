import java.awt.Color;
import java.awt.Graphics;

public class Alien2 extends GameObject{

	@Override
	public GameObjectType getType()
	{
		return GameObjectType.Alien2;
	}
	
	@Override
	public void draw(Graphics page)
	{
		page.setColor(Color.GREEN);
		page.drawRect(getX(), getY(), getStandardSize(), getStandardSize());
	}
}
