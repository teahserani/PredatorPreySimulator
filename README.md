# Predator Prey Simulation

**Teah Serani** 
 - teahserani@gwu.edu
 - teahserani
 
### YOU NEED TO CHANGE THE FILE PATH IN PPSim FOR IT TO WORK.

## World 
```populate()```- add Predator and Prey

```update()```- makes all animals move

```Create()```- draws all animals
## PPSim
 Here the world is created using GUI. When the users clicks the world restarts.
## Animal
Animal is the parent class for Predator and Prey. Information about the functions are described below:

```NewDirection()```- generates a random number that is corresponds to a direction(Up, Down, Left, Right)

```WhenToMove()```- allows the animal to move

```move()```- default method

```Create()```- draws the animal
## Predator
Is a child class of Animal.

```move()```- creates a new random number to change direction 20% of the time

```Eat()```- converted Prey to Predator

```Hunt()```-Allows Predator to Follow Prey and convert to predator
## Prey 
Is a child class of Animal.

```move()```- creates a new random number to change direction 10% of the time

```Create()```- makes Prey a circle with a random color
## Extra Credit
For extra credit I added music to the background. YOU NEED TO CHANGE THE FILE PATH IN PPSim FOR IT TO WORK.

In addition when the user pressed the spacebar new prey appear. Both of these make the game more entertaining for the user.
## Demo
![Demo](demo.gif)
