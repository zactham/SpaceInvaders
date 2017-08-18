import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class UFO extends GameObject
{
	private Image ufoImage;

	private int movement = 5;

	private boolean moveRight = true;

	private int ufoX = SpaceInvadersGame.u.getX();
	private int ufoY = SpaceInvadersGame.u.getY();

	Sound sound = new Sound();




	@Override
	public GameObjectType getType()
	{
		return GameObjectType.UFO;
	}

	@Override
	public void draw(Graphics page)
	{
		page.setColor(Color.BLUE);

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

	@Override
	public void update()
	{
		if(moveRight)
			moveUFOsideways(moveRight);


		if(ufoX + 10 >= GameObject.getGameWidth())
			moveRight = false;

		if(!moveRight)
			moveUFOsideways(moveRight);

		if(ufoX <= 0)
			moveRight = true;

		sound.play("sounds/ufo_lowpitch.wav");


	}

	private void moveUFOsideways(boolean direction)
	{
		int X = SpaceInvadersGame.u.getX();
		if(direction)
			SpaceInvadersGame.u.setX(X+movement);
		else
			SpaceInvadersGame.u.setX(X-movement);


	}
}
