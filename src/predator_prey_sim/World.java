package predator_prey_sim;

import util.Helper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class World  {
	private int width, height;
	public ArrayList<Animal> listAnimals = new ArrayList<>();
	private Color canavasColor;
	JFrame frame;
	/**
	 * 
	 * @param numPrey number of prey
	 * @param numPredator number of predators
	 **/
	public World(int w, int h,  int numPrey, int numPredator) {
		width = w;
		height = h;
		canavasColor = Helper.newRandColor();
		populate(numPrey, numPredator);
	}


	/**
	 * Generates numPrey random prey and numPredator random predators
	 * distributed throughout the world.
	 * Prey must not be placed outside canavas!
	 *
	 * @param numPrey the number of prey to generate
	 * @param numPredator the number of predators to generate
	 */
	private void populate(int numPrey, int numPredator) {
		double Reproduce= Helper.nextDouble();
		int x = 0;
		int y = 0;

		for (int i = 0; i < numPrey; i++) {
			x = Helper.nextInt(width);
			y = Helper.nextInt(height);

			Prey newPrey = new Prey(x, y);
			listAnimals.add(newPrey);
		}for (int i = 0; i < numPredator; i++) {
			x  = Helper.nextInt(width);
			y = Helper.nextInt(height);

			Predator newPredator = new Predator(x, y);
			listAnimals.add(newPredator);
		}
	}

	/**
	 * Updates the state of the world for a time step.
	 *@param listAnimals
	 */
	public void update() {
		for(int i = 0; i < listAnimals.size(); i++) {
			listAnimals.get(i).WhenToMove(this);
		}
	}

	/**
	 * Draw all the predators and prey.
	 */
	public void draw(){
		/* Clear the screen */
		PPSim.dp.clear(canavasColor);

		for(int i = 0; i < listAnimals.size(); i++) {
			listAnimals.get(i).Create();
		}
	}

}
