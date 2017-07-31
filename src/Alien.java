import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Alien extends GameObject
{
	private ArrayList<Image> alienList= new ArrayList<Image>();
	private MyTimer stepTimer = new MyTimer(2000);
	int imageIndex = 0;

	public Alien()
	{
		stepTimer.start();
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
