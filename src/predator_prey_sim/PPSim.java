package predator_prey_sim;
import util.DotPanel;
import util.Helper;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
/*
 * You must add a way to represent humans.  When there is not a vampire  apocalypse occurring, humans
 * should follow these simple rules:
 * 		if (1 in 10 chance):
 * 			turn to face a random direction (up/down/left/right)
 * 		Move in the current direction one space
 *
 * We will add additional rules for dealing with sighting or running into vampires later.
 */

public class PPSim<button, SOUTH> extends JFrame {

	private static final long serialVersionUID = -5176170979783243427L;

	/**
	 * The Dot Panel object you will draw to
	 */
	public static DotPanel dp;
	public static World ppworld;
	/* Define constants using static final variables */
	public static final int MAX_X = 100;
	public static final int MAX_Y = 100;
	public static final int DOT_SIZE = 6;
	private static int NUM_PREY = 10;
	private static final int NUM_PREDATORS = 5;

	/*
	 * This fills the frame with a "DotPanel", a type of drawing canvas that
	 * allows you to easily draw squares for predators and circles for prey
	 * to the screen.
	 */

	public PPSim() {
		this.setSize(MAX_X * DOT_SIZE, MAX_Y * DOT_SIZE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Predator Prey World");

		/* Create and set the size of the panel */
		dp = new DotPanel(MAX_X, MAX_Y, DOT_SIZE);

		/* Add the panel to the frame */
		Container cPane = this.getContentPane();
		cPane.add(dp);



		/* Initialize the DotPanel canvas:
		 * You CANNOT draw to the panel BEFORE this code is called.
		 * You CANNOT add new widgets to the frame AFTER this is called.
		 */
		/* Initialize the DotPanel canvas:
		 * You CANNOT draw to the panel BEFORE this code is called.
		 * You CANNOT add new widgets to the frame AFTER this is called.
		 */
		this.pack();
		dp.init();
		dp.clear();
		dp.setPenColor(Color.red);
		this.setVisible(true);

		/* Create our city */
		ppworld = new World(MAX_X, MAX_Y, NUM_PREY, NUM_PREDATORS);
		/* This is the Run Loop (aka "simulation loop" or "game loop")
		 * It will loop forever, first updating the state of the world
		 * (e.g., having predators take a single step) and then it will
		 * draw the newly updated simulation. Since we don't want
		 * the simulation to run too fast for us to see, it will sleep
		 * after repainting the screen. Currently it sleeps for
		 * 33 milliseconds, so the program will update at about 30 frames
		 * per second.
		 */

		MouseListener listener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent m) {
				ppworld = new World(MAX_X, MAX_Y, NUM_PREY, NUM_PREDATORS);
				double reproduce= Helper.nextDouble();
				if(reproduce <0.10) {
					for(int i=0; i<11; i++) {
						int x=Helper.nextInt(100);
						int y=Helper.nextInt(100);
						Prey prey = new Prey(x,y);
						ppworld.listAnimals.add(prey);
					}
				}if(reproduce<0.20){
					for(int i=0;i<3; i++){
						int x=Helper.nextInt(100);
						int y=Helper.nextInt(100);
						Predator predator= new Predator(x,y);
						ppworld.listAnimals.add(predator);
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent m) {}
			@Override
			public void mouseReleased(MouseEvent m) {}
			@Override
			public void mouseEntered(MouseEvent m) {}
			@Override
			public void mouseExited(MouseEvent m) {}
		};
		KeyListener PREY = new KeyListener()
		{
			public void keyPressed(KeyEvent addPrey) {
				if(addPrey.getKeyCode() == KeyEvent.VK_SPACE)
				{
					for(int i=0; i<11; i++) {
						int x=Helper.nextInt(100);
						int y=Helper.nextInt(100);
						Prey prey = new Prey(x,y);
						ppworld.listAnimals.add(prey);
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent addPrey) {}
			@Override
			public void keyReleased(KeyEvent addPrey) {}
		};

		this.addKeyListener(PREY);

		//Plays music
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("C:\\Users\\Teah\\IdeaProjects\\project2-teahserani\\src\\predator_prey_sim\\No_War.wav"));
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
				}
			});
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
			ex.printStackTrace();
		}
		this.addMouseListener(listener);
		while (true) {
			// Run update rules for world and everything in it
			ppworld.update();
			// Draw to screen and then refresh
			ppworld.draw();

			dp.repaintAndSleep(30);
		}
	}

	public static void main(String[] args) {
		/* Create a new GUI window  */
		new PPSim();

	}
}
