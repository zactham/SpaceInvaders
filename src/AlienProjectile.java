import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class AlienProjectile extends GameObject
{
	private Image alienprojectileImage;
	
	
	private static final int speed=6;
	
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
		super.draw(page);
		page.drawImage(alienprojectileImage, getX(), getY(),null);
	}
	
	@Override
	public void update()
	{
		super.update();
		setY(getY()+speed);
		updateBounds();
	}

}
