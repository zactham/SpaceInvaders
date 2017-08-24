import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class UFO extends GameObject
{
	private Image ufoImage;

	private int movement = 2;

	private Sound sound = new Sound();

	private boolean isVisible = false;
	
	int i = 0;

	@Override
	public GameObjectType getType()
	{
		return GameObjectType.UFO;
	}

	@Override
	public void draw(Graphics page)
	{
		if(getVisible())
		{
			super.draw(page);
			page.drawImage(ufoImage, getX(), getY(),null);
		}


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
		if(getVisible())
		{
			super.update();
			moveUFOsideways();
			updateBounds();
		}
	}

	private void moveUFOsideways()
	{
		setX(getX()-movement);
	}

	public void setVisible(boolean v)
	{
		isVisible = v;
		
	}

	public boolean getVisible()
	{
		return isVisible;
	}

	public void start()
	{
		setVisible(true);
		getSound().play("sounds/ufo_lowpitch.wav");
		getSound().loop(true);
		
	}

	public void stop()
	{
		setVisible(false);
		getSound().stop();
	}


}
