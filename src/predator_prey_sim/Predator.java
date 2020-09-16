package predator_prey_sim;

import util.Helper;

import java.awt.Color;
import java.util.ArrayList;

public class Predator extends Animal {

    public Predator(int x, int y)
    {
        super(x,y);
        this.color = Color.RED;
    }

    //changes direction
    public void move(World world)
    {
        if(!Hunt(world.listAnimals))
        {
            double newDirection = Helper.nextDouble();
            int curDirection = this.direction;

            if(newDirection <= .2)
            {
                while(this.direction == curDirection)
                {
                    this.newDirection();
                }
            }
        }
    }
    public void Eat(ArrayList<Animal> Food, ArrayList<Animal> listAnimal)
    {
        for(int i = 0; i<Food.size(); i++) {
            int tempX = Food.get(i).x;
            int tempY = Food.get(i).y;

            Predator predator = new Predator(tempX, tempY);
            listAnimal.add(predator);
            listAnimal.remove(Food.get(i));
        }
    }

    //converts Prey to Predator
    public boolean Hunt(ArrayList<Animal> listAnimal)
    {
        boolean eaten = false;
        ArrayList<Animal> Food = new ArrayList<>();

        for(int i = 0; i<listAnimal.size(); i++) {
            //only continue if it is prey
            if(!(listAnimal.get(i) instanceof Prey)) {
                continue;
            }

            int distanceofX = (listAnimal.get(i).x - this.x);
            int distanceofY = (listAnimal.get(i).y - this.y);

            //Continues to move in current direction since no prey is near
            if(Math.abs(distanceofX) > 10 || Math.abs(distanceofY) > 10){
                continue;
            }
            if(Math.abs(distanceofX) + Math.abs(distanceofY) <= 1 ) {
                Food.add(listAnimal.get(i));
            }
            //The Predator move toward a the prey
            if(distanceofX < 0) {
                direction = 3;
                eaten = true;
            }if(distanceofX > 0) {
                direction = 2;
                eaten = true;
            }if(distanceofY < 0) {
                direction = 1;
                eaten = true;
            }if(distanceofY > 0) {
                direction = 0;
                eaten = true;
            }
        }

        Eat(Food, listAnimal);
        return eaten;
    }
    public void Reproduce(ArrayList<Animal> listAnimal){
        Predator predator = new Predator(x, y);
        listAnimal.add(predator);
    }
}
