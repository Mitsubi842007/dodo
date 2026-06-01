import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;

    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
        {
            //climbOverFence();
            //turnLeft ();
            //turnLeft ();
            //walkAroundFenceArea();

        }
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();

        } else {
            showError( "I'm stuck!");
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( borderAhead() ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }

    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }

    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move(1);   // take a step
            nrStepsTaken++;                 // increment the counter
            System.out.print("    i moved" );
        }
    }

    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     *              Coordinates of each cell printed in the console.
     */

    public void walkToWorldEdgePrintingCoordinates() {
        while (!borderAhead()) {
            move(); 
            //walks till it arrives to the end of the map

        }
    }

    public void walkToWorldEdgeClimbingOverFences(){
        while( ! borderAhead()     && !onNest()){

            if (fenceAhead()){ 
                climbOverFence();
            } else {
                move();
            }
            if (onNest()){
                layEgg();

            }
        }
    }

    public void walkToWorldEdgeLayEgg() {
        while (!borderAhead()) {
            move();
            if (onNest()) {
                layEgg();

            }
        }  
    }

    public void pickUpGrainsAndPrintCoordinates(){
        while (!borderAhead()) {
            //picks up grain when walking straight and prints coordinates
            if (onGrain()) {
                System.out.println(getX() + ", " + getY());
                pickUpGrain();
            }

            move();
        }

        //picks up also the last 
        if (onGrain()) {
            System.out.println(getX() + ", " + getY());
            pickUpGrain();
        }
    }

    public void goBackToStartOfRowAndFaceBack () {
        turnLeft();
        turnLeft();
        walkToWorldEdgePrintingCoordinates();
        turnLeft();
        turnLeft();
        //walks backwards instead of walks to the front
    }

    public void stepOneCellBackwards() {
        turnRight();
        turnRight();

        move();

        turnRight();
        turnRight();
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
        if( onEgg() ){
            return false;
        }else{
            return true;
        }
    }  

    public void climbOverFence()
    // if theres a fench infront of the dodo then he will make a turn to get over the fence instead of going forward
    {
        turnLeft();
        move();
        turnRight();
        move();
        move();
        turnRight();
        move();
        turnLeft();

    }
    //it checks if theres grain on the ground 
    //and needs to move back again after it checks if theres grain
    public boolean grainAhead()
    {
        stepOneCellBackwards();
        move();

        if (isTouching(Grain.class))
        {
            turnRight();
            turnRight();
            move();
            turnRight();
            turnRight();
            return true;
        }
        else
        //if theres no grain it wont will return to its place because the grain aint there
        {
            System.out.println("There's no grain");
            return false;
        }
    }

    /**
     * Walks around fences while theres a fence ahead it turns till it follows around and reach the egg and stops
     */
    public void walkAroundFenceArea ()
    {
        //turn right
        //controlls if theres a fence if true then turn left 
        //go forward

        while(!onEgg()){ 
            turnRight();
            while(fenceAhead()){
                turnLeft();

            }
            move();
        }
    }

    /**
     * Walks and follow eggs and stops at a nest
     */
    public void eggTrailToNest() {
        while(!onNest()) {
            if(eggAhead() || nestAhead()) {
                move();
                turnLeft();
            } else {
                turnRight();
            }
        }
    }

    public void walkAroundMazeArea ()
    {
        //turn right
        //controlls if theres a fence if true then turn left 
        //go forward

        while(!onNest()){ 
            turnRight();
            while(fenceAhead()){
                turnLeft();

            }
            move();
        }
    }

    public void faceEast()
    {
        while(getDirection() != EAST){
            turnRight();
        }
    }

    public void faceWest()
    {
        while(getDirection() != WEST){
            turnRight();
        }
    }

    public void faceSouth()
    {
        while(getDirection() != SOUTH){
            turnRight();
        }
    }

    public void faceNorth()
    {
        while(getDirection() != NORTH){
            turnRight();
        }
    }

    public void faceDirection(int direction) {
        if (direction >= 0 && direction <= 3) {
            while (getDirection() != direction) {
                turnRight();
            }
        }
    }
}

