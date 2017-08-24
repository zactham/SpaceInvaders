import java.awt.Graphics;
import java.awt.Image;

public class Explosion extends GameObject
{
	private Image explosionImage;

	private boolean isVisible = false;

	private Sound sound = new Sound();

	private MyTimer timer = new MyTimer(1000);

	@Override
	public GameObjectType getType()
	{
		return GameObjectType.Explosion;
	}

	@Override
	public void draw(Graphics page)
	{
		if(getVisible())
		{
			super.draw(page);
			page.drawImage(explosionImage, getX(), getY(),null);
		}


	}

	public void setImage(Image i)
	{
		explosionImage=i;
	}

	public Image getImage()
	{
		return explosionImage;
	}

	public Sound getSound()
	{
		return sound;
	}

	@Override
	public void update()
	{
		setVisible(!timer.isExpired());
		
		if(getVisible())
		{
			super.update();
			updateBounds();
		}
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
		timer.start();
		timer.setDelay(1000);
		setVisible(true);
		getSound().play("sounds/explosion.wav");
		
	}

	public void stop()
	{
		setVisible(false);
	}


}


