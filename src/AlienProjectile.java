import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class AlienProjectile extends GameObject
{
	private Image alienprojectileImage;
	
	@Override
	public GameObjectType getType()
	{
		return GameObjectType.AlienProjectile;
	}
	
	public void setImage(Image i)
	{
		alienprojectileImage=i;
	}

	public Image getImage()
	{
		return alienprojectileImage;
	}
	
	@Override
	public void draw(Graphics page)
	{
		page.setColor(Color.red);
		page.drawRect(getX(), getY(), projectileWidth, projectileWidth);
	}
	
	@Override
	public void update()
	{
		super.update();
		setY(getY()+projectileHeight);
		updateBounds();
	}

}
