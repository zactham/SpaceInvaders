import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Alien extends GameObject
{
	private static ArrayList<Image> alienList= new ArrayList<Image>();
	private MyTimer stepTimer = new MyTimer(2000);
	private static int imageIndex = 0;
	private Sound sound = new Sound();

	public Alien()
	{
		stepTimer.start();
	}
	
	public static void incImageIndex()
	{
		imageIndex++;
		if(imageIndex>alienList.size()-1)
		{
			imageIndex = 0;
		}
	}

	public void addImage(Image i)
	{
		alienList.add(i);
	}

	@Override
	public void draw(Graphics page)
	{
		super.draw(page);
		page.drawImage(alienList.get(imageIndex), getX(), getY(),null);
	}

	@Override
	public void update()
	{
		super.update();

		if(stepTimer.isExpired())
		{
			stepTimer.start();
			imageIndex++;
			if(imageIndex >= alienList.size())
				imageIndex = 0;
		}
	}
}
