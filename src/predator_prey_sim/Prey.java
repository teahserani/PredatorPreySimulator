package predator_prey_sim;
import util.Helper;

import java.util.ArrayList;

public class Prey extends Animal {
    public Prey(int x, int y) {
        super(x,y);
    }
    public void move(World world) {
        double newDir = Helper.nextDouble();
        int currentDir = this.direction;

        //10% of a new direction
        if(newDir <= .1) {
            while(this.direction == currentDir) {
                this.newDirection();
            }
        }

    }
    //Prey are set to color WHITE
    public void Create() {
        PPSim.dp.drawCircle(x, y, Helper.newRandColor());
    }
    public void Reproduce(ArrayList<Animal> listAnimal){
        while(true) {
            Predator predator = new Predator(x, y);
            listAnimal.add(predator);
            System.out.println("And another one");
        }
    }
}
