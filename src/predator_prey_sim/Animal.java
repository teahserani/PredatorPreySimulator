package predator_prey_sim;
import util.Helper;

import java.awt.*;


public abstract class Animal {
    int x;
    int y;
    int direction;
    Color color;

    public Animal(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = Helper.nextInt(4); // 4 different directions
    }

    public void newDirection() {
        direction = Helper.nextInt(4);
    }
    public void WhenToMove(World world) {
        move(world);
        while (true) {
            if (direction == 0) {//Down
                y++;
                if(y== PPSim.MAX_Y){
                    y--;
                }
                break;
            }if (direction == 1) {//UP
                y--;
                if(y==0){
                    y++;
                }
                break;
            }if (direction == 2) {//Right
                x++;
                if(x== PPSim.MAX_X){
                    x--;
                }
                break;
            }if (direction == 3) {//Left
                x--;
                if(x==0){
                    x++;
                }
                break;
            } else {
                this.newDirection();
            }
        }
    }

    //default method which overwrite each animal in its own classes
    public void move(World world) {}

    public void Create() {
        PPSim.dp.drawSquare(x, y, this.color);
    }
}
