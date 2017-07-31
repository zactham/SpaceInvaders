import java.awt.Color;
import java.awt.Graphics;

public class Alien1 extends Alien
{
	private Sound sound = new Sound();
	
	@Override
	public GameObjectType getType()
	{
		return GameObjectType.Alien1;
	}
	
}
