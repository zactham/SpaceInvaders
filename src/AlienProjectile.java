import java.awt.Color;
import java.awt.Graphics;

public class AlienProjectile extends GameObject{
	
	@Override
	public GameObjectType getType()
	{
		return GameObjectType.AlienProjectile;
	}
	
	@Override
	public void draw(Graphics page)
	{
		page.setColor(Color.red);
		page.drawRect(getX(), getY(), 15, 15);
	}
	
	@Override
	public void update()
	{
		super.update();
	}

}
