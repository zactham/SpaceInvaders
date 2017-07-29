import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Player extends GameObject
{
	private PlayerDirection direction = PlayerDirection.NONE;
	private int speed = 2;
	private Image playerImage;
	private PlayerProjectile shot=null;

	@Override
	public GameObjectType getType()
	{
		return GameObjectType.Player;
	}

	@Override
	public void draw(Graphics page)
	{
		page.setColor(Color.red);
		page.drawImage(playerImage, getX(), getY(), null);
		if(!(shot==null))
		{
			shot.draw(page);
		}
	}
	
	public void setImage(Image i)
	{
		playerImage=i;
	}
	
	public Image getImage()
	{
		return playerImage;
	}

	public PlayerDirection getDirection()
	{
		return direction;
	}

	public void setDirection(PlayerDirection d)
	{
		direction = d;
	}

	@Override
	public void update()
	{
		super.update();

		if(this.getX() < 0)
			this.setX(0);

		if (this.getX()>getgameWidth()-getStandardSize())
			this.setX(getgameWidth()-getStandardSize());

		if (direction == PlayerDirection.RIGHT)
			x+=speed;

		if (direction == PlayerDirection.LEFT)
			x-=speed;

		if(!(shot==null))
		{
			shot.update();
			if(shot.y<0){
				shot=null;
			}
		}
	}

	public void fire()
	{
		if(shot==null)
		{
			shot=new PlayerProjectile();
			//shot.setX(this.x);
			
			//This will have to change eventually for now we can use this
			//shot.setX(x+((int)getStandardSize()/2)-2/2);
			//		or this
			shot.setX(x+((int)getStandardSize()/2)-getProjectileSize()/2);
			
			shot.setY(y);
			SpaceInvadersGame.sound.play("sounds//playerShoot.wav");
		}
	}
}
