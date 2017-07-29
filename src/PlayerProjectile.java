import java.awt.Color;
import java.awt.Graphics;

public class PlayerProjectile extends GameObject{
	
	private static final int speed=12;
	
	@Override
	public GameObjectType getType()
	{
		return GameObjectType.PlayerProjectile;
	}
	
	@Override
	public void draw(Graphics page)
	{
		page.setColor(Color.white);
		page.fillRect(getX(), getY(), 2, getProjectileSize());
	}
	
	@Override
	public void update()
	{
		super.update();
		this.y-=speed;
	}
}
