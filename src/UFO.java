import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class UFO extends GameObject
{
	private Image ufoImage;

	private int movement = 2;

	private Sound sound = new Sound();

	private boolean isVisible = false;
	
	private int direction=(int)Math.random()*2 + 1; // returns a number form 1 to 2
	
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
		if(direction==1){
			setX(getX()-movement);
		}
		else if(direction==2){
			setX(getX()+movement);
		}
		
	}
	
	public int getDirection(){
		return direction; 
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
