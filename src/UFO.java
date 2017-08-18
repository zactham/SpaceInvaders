import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class UFO extends GameObject
{
	private Image ufoImage;

	private int movement = 2;

	Sound sound = new Sound();

	@Override
	public GameObjectType getType()
	{
		return GameObjectType.UFO;
	}

	@Override
	public void draw(Graphics page)
	{
		page.drawImage(ufoImage, getX(), getY(),null);
	}

	public void setImage(Image i)
	{
		ufoImage=i;
	}

	public Image getImage()
	{
		return ufoImage;
	}
	
	public Sound getSound()
	{
		return sound;
	}

	@Override
	public void update()
	{
		moveUFOsideways();
	}

	private void moveUFOsideways()
	{
		setX(getX()-movement);
	}
}
