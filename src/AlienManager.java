import java.awt.Graphics;
import java.util.ArrayList;

public class AlienManager {
	public ArrayList<GameObject> alienList= new ArrayList<GameObject>();

	public void draw(Graphics page)
	{
		for(GameObject Alien : alienList){
			Alien.draw(page);
		}
	}
	
	public void update(){
		for(GameObject Alien : alienList){
			Alien.update();
		}
	}
}
