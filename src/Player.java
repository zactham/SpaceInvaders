import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject
{
	private PlayerDirection direction = PlayerDirection.NONE;
	private int speed = 2;
	
	
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
	
	public PlayerDirection getDirection()
	{
		return direction;
	}
	
	public void setDirection(PlayerDirection d)
	{
		direction = d;
	}
	
	@Override
	public void update()
	{
		super.update();
		
		if (direction == PlayerDirection.RIGHT)
			x+=speed;
		
		if (direction == PlayerDirection.LEFT)
			x-=speed;
	}
}
