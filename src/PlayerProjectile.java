import java.awt.Color;
import java.awt.Graphics;

public class PlayerProjectile extends GameObject{
	
	@Override
	public GameObjectType getType()
	{
		return GameObjectType.PlayerProjectile;
	}
	
	@Override
	public void draw(Graphics page)
	{
		page.setColor(Color.red);
		page.drawRect(getX(), getY(), getProjectileSize(), getProjectileSize());
	}
	
	@Override
	public void update()
	{
		super.update();
	}
}
