import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Alien extends GameObject
{
	private  ArrayList<Image> alienList= new ArrayList<Image>();
	private static int imageIndex = 0;
	private AlienProjectile alienShot = null;
	private Image alienShotImage = TitleScreen.theApp.getImage(TitleScreen.theApp.getCodeBase(), "images/alienShot.png").
			getScaledInstance(10, 10, Image.SCALE_DEFAULT);

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
		super.draw(page);
		page.drawImage(alienList.get(imageIndex), getX(), getY(),null);
	
		if (!(alienShot == null))
		alienShot.draw(page);
	
	}
	
	
	public void update()
	{
		super.update();
		updateBounds();
	
		

		int randomNum = (int) (Math.random()*2000 +1);

		if(randomNum == 1)
		{
			
			fire();
		}
		
		if(!(alienShot == null))
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
	
	public void fire()
	{
		if(alienShot==null)
		{
			alienShot=new AlienProjectile();
			alienShot.setImage(alienShotImage);
			//shot.setX(this.x);
			
			//This will have to change eventually for now we can use this
			//shot.setX(x+((int)getStandardSize()/2)-2/2);
			//		or this
			alienShot.setX(this.getX());
			
			alienShot.setY(this.getY());
			sound.play("sounds//alienShoot.wav");
			
			alienShot.createBounds(alienShot.getX(), alienShot.getY(), projectileWidth,projectileHeight);
		}
	}

	
}
