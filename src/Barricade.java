import java.awt.Color;
import java.awt.Graphics;

public class Barricade extends GameObject
{

	@Override
	public GameObjectType getType()
	{
		return GameObjectType.Barricade;
	}
	
	@Override
	public void draw(Graphics page)
	{
		page.setColor(Color.BLACK);
		
		page.drawRect(getX(), getY(), getStandardSize(), getStandardSize());
	}
}
