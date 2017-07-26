import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//7/25/17
public class TitleScreen extends JApplet //implements MouseListener
{

	public static boolean easy = false;
	public static boolean med = false;
	public static boolean hard = false;

	private MainCode board;
	
	private ImageIcon titleScreenImage;//image


	//All buttons

	//Image button
	public JButton 	titleScButton;

	//Main Buttons
	private JFrame help;
	private JFrame credits;

	private boolean soundPlaying = true;

	public void init () 
	{		

		try
		{
			playMusicMain();
		}
		catch(Exception err)
		{
			
		}


		//Adds the image and creates a button out of it
		titleScreenImage = new ImageIcon(this.getClass().getResource("EasyMediumHard.jpg"));//image	
		titleScButton = new JButton (titleScreenImage);//image button
		getContentPane().add(titleScButton);
		setSize(833,777);
		centerWindow();//centers the window

		
		//Based on where they click in easy medium or hard something happens
		titleScButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//System.out.println(e);
				//EASY

				if (e.getX() > 41 && e.getX() < 266 && e.getY() > 253 && e.getY() < 353)
				{
					//System.out.println("Easy");

					easy = true;
					Sound.audioClip.stop();
					soundPlaying = false;


					board = new MainCode();
					try
					{
						board.init();
					} catch (InterruptedException e1) 
					{
						//	System.out.println(e1);
					}

				}

				//MEDIUM
				//page.fillOval(112, 15, 75, 75);
				if (e.getX() > 312 && e.getX() < 536 && e.getY() > 253 && e.getY() < 353)
				{
					//System.out.println("Medium");

					med = true;

					Sound.audioClip.stop();
					soundPlaying = false;

					
					board = new MainCode();
					try
					{
						board.init();
					} catch (InterruptedException e1) 
					{
						System.out.println(e1);
					}
				}

				//HARD
				//page.fillOval(212, 15, 75, 75);
				if (e.getX() > 579 && e.getX() < 803 && e.getY() > 253 && e.getY() < 353)
				{
					//System.out.println("Hard");

					hard = true;

					Sound.audioClip.stop();
					soundPlaying = false;

					
					board = new MainCode();
					try
					{
					board.init();
					} catch (InterruptedException e1) 
					{
						//System.out.println(e1);
					}

				}

				//Music Toggle
				if (e.getX() > 0 && e.getX() < 27 && e.getY() > 745 && e.getY() < 769)
				{

					//System.out.println("Music toggle");
					if (soundPlaying)
					{
						Sound.audioClip.stop();
						soundPlaying = false;
					}
					else
					{
						Sound.audioClip.start();
						soundPlaying = true;
					}

				}
				//Help
				if (e.getX() > 806 && e.getX() < 830 && e.getY() > 752 && e.getY() < 775)
				{

					//	System.out.println("Help");
					JOptionPane.showMessageDialog(help,
							"Use the 9 num pad on the right side of the keyboard to whack the moles.");


				}

				//Credits
				if (e.getX() > 770 && e.getX() < 830 && e.getY() > 1 && e.getY() < 14)
				{

					//	System.out.println("Credits");
					JOptionPane.showMessageDialog(help,
							"Created by Zac Thamer and Saumya Shukla");


				}

			}

			public void mouseEntered(MouseEvent arg0) {

			}

			public void mouseExited(MouseEvent arg0) {

			}

			public void mousePressed(MouseEvent arg0) {

			}

			public void mouseReleased(MouseEvent arg0) {

			}
		});
	}




	public class CloseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	public void playMusic() throws InterruptedException
	{
		Sound.play("TitleScreenMusic.wav");
	}

	public void playMusicMain() throws InterruptedException
	{
		playMusic();

	}

	//Centers the window
	public void centerWindow()
	{
		//gets top level window
		Window window;
		Container c = getParent();
		while (c.getParent()!=null) 
			c = c.getParent();

		// center window
		if (c instanceof Window)//if it is the top window...
		{
			//centers it
			window = (Window)c;
			window.pack();
			window.setLocationRelativeTo(null);					
		}
	}
}

