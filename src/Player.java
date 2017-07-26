import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject
{
	@Override
	public GameObjectType getType()
	{
		return GameObjectType.Player;
	}

	@Override
	public void draw(Graphics page)
	{
		page.setColor(Color.red);
		page.drawRect(getX(), getY(), getStandardSize(), getStandardSize());
	}
}
