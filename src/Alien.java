import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Alien extends GameObject
{
	private  ArrayList<Image> alienList= new ArrayList<Image>();
	private static int imageIndex = 0;
	private AlienProjectile alienShot = null;
	private int shotSize = 10;
	private Image alienShotImage = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), "images/alienShot.png").
			getScaledInstance(shotSize, shotSize, Image.SCALE_DEFAULT);
	protected boolean lowestInColumn;


	private Sound sound = new Sound();

	public Alien()
	{

	}

	public void incImageIndex()
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
		if(getlowestinCol())
		{
			drawBounds(page);
		}
		super.draw(page);
		page.drawImage(alienList.get(imageIndex), getX(), getY(),null);

		if (!(alienShot == null))
			alienShot.draw(page);

	}


	public void update()
	{
		super.update();
		updateBounds();



		int randomNum = (int) (Math.random()*1000 +1);

		if(randomNum == 1)
		{

			fire();
		}

		if(alienShot != null)
		{
			alienShot.update();
			if(alienShot.getY()>GameObject.getGameHeight())
			{
				removeShot();
			}
		}
	}

	public AlienProjectile getAlienShot()
	{
		return alienShot;
	}

	public void setAlienShot(AlienProjectile a)
	{
		alienShot = a;
	}

	public void removeShot()
	{
		alienShot = null;
	}


	public boolean getlowestinCol()
	{
		return lowestInColumn;
	}

	public void setlowestinCol(boolean low)
	{
		lowestInColumn = low;
	}


	public void fire()
	{

		if(alienShot==null && getlowestinCol() == true)
		{
			alienShot=new AlienProjectile();
			alienShot.setImage(alienShotImage);
			//shot.setX(this.x);

			//This will have to change eventually for now we can use this
			//shot.setX(x+((int)getStandardSize()/2)-2/2);
			//		or this
			alienShot.setX((getX()+GameObject.getAlienSize()/2)-(shotSize-2));

			alienShot.setY(getY());
			sound.play("sounds//alienShoot.wav");

			alienShot.createBounds(alienShot.getX(), alienShot.getY(), shotSize,shotSize);
		}
	}


}
