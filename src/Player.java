import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject
{
	private PlayerDirection direction = PlayerDirection.NONE;
	private int speed = 2;
	
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
		page.drawRect(getX(), getY(), getStandardSize(), getStandardSize());
		if(!(shot==null))
		{
			shot.draw(page);
		}
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
			shot.setX(this.x+((int)getStandardSize()/2)-shot.getProjectileSize()/2);
			shot.setY(this.y);
			SpaceInvadersGame.sound.play("sounds//shoot.wav");
		}
	}
}
