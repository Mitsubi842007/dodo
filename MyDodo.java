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

    /**
     *looks at right (default) 
     */
    public void faceEast()
    {
        while(getDirection() != EAST){
            turnRight();
        }
    }

    /**
     *looks at left 
     */
    public void faceWest()
    {
        while(getDirection() != WEST){
            turnRight();
        }
    }

    /**
     *looks down
     */
    public void faceSouth()
    {
        while(getDirection() != SOUTH){
            turnRight();
        }
    }

    /**
     *looks up
     */
    public void faceNorth()
    {
        while(getDirection() != NORTH){
            turnRight();
        }
    }

    /**
     *you can type what direction u want to look at 
     */
    /**
     * Checks if Dodo has reached the given coordinates.
     */
    public boolean locationReached(int x, int y) {
        return getX() == x && getY() == y;
    }

    /**
     * Moves Dodo to the given coordinates.
     */
    public void goToLocation(int coordX, int coordY) {

        while (!locationReached(coordX, coordY)) {

            if (getX() < coordX) {
                setDirection(EAST);
                move();
            }
            else if (getX() > coordX) {
                setDirection(WEST);
                move();
            }
            else if (getY() < coordY) {
                setDirection(SOUTH);
                move();
            }
            else if (getY() > coordY) {
                setDirection(NORTH);
                move();
            }
        }
    }

    public void faceDirection(int direction) {
        if (direction >= 0 && direction <= 3)
        {
            while (getDirection() !=direction){
                turnRight();
            }
        }
    }

    /**
     * counts egg on the current row
     * after counting the eggs the dodo returns back to the first potsition
     * after counting and back on original place it then says how many eggs was at that row 
     */
    public int countEggsInRow() {
        int eggCount = 0;

        if (onEgg()) {
            eggCount++;
        }

        while (!borderAhead()) {
            move();

            if (onEgg()) {
                eggCount++;

            }
        }

        goBackToStartOfRowAndFaceBack();

        return eggCount;
    }

    /**
     *makes a trail of eggs
     */
    public void layTrailOfEggs (int numEgg)  {
        if (numEgg <= getWorld().getWidth() ){
            for (int index = 0; index < numEgg; index++) {
                if (!onEgg()) {
                    layEgg();
                }
                move();
                if (borderAhead()) {
                    index = numEgg;
                }
            }
            layEgg();
        } else {
            System.out.println ("error" );
        }

    }

    /**
     *goes to a row with the most eggs (will first count all eggs first from all row)
     */
    public void goToRowWithMostEggs() {
        int bestRow = 0;
        int mostEggs = 0;

        for (int row = 0; row < getWorld().getHeight(); row++) {

            int eggs = 0;

            for (int col = 0; col < getWorld().getWidth(); col++) {
                goToLocation(col, row);

                if (onEgg()) {
                    eggs++;
                }
            }

            if (eggs > mostEggs) {
                mostEggs = eggs;
                bestRow = row;
            }
        }

        System.out.println("Row with most eggs: " + bestRow);

        // Go to the row with the most eggs as end position
        goToLocation(0, bestRow);
    }

    /**
     *makes a stair made of eggs 
     */
    public void eggMonument() {
        int x = getX();
        int y = getY();
        int row = 0;
        while (y + row < getWorld().getHeight() && x + row < getWorld().getWidth()) {
            goToLocation(x, y + row);
            faceDirection(1);
            for (int i = 0; i <= row; i++) {
                layEgg();
                if (i < row) { 
                    move();
                }
            }
            row++;
        }
        if(borderAhead() ){
            int locationReached;
        }
    }

    /**
     *keeps multiplying eggs till it reached the last row
     */
    public void solidMonument() {
        int x = getX();
        int y = getY();
        int eggs = 1;
        int row = 0;
        while (y + row < getWorld().getHeight() && x + row <= getWorld().getWidth()) {
            goToLocation(x, y + row);
            faceDirection(1);
            for (int i = 0; i < eggs; i++) {
                layEgg();
                if (i < eggs - 1) { 
                    move(); 
                }
            }
            row++;
            eggs = eggs * 2;
        }
        if(borderAhead() ){
            int locationReached;
        }
    }

    /**
     *makes a pyramid made of eggs 
     */
    public void eggPyramid() {
        int x = getX();
        int y = getY();
        int numberOfRows = getWorld().getHeight() - y;

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = -row; col <= row; col++) {
                goToLocation(x + col, y + row);
                if (canLayEgg()) {
                    layEgg();

                }
            }
        }

    }

    /**
     *looks to the average eggs amount at each row
     */
    public double averageEggAmount() {
        int rowAmount = getWorld().getHeight();
        int totalEggs = 0;

        for (int row = 0; row < rowAmount; row++) {
            goToLocation(0, row);
            faceDirection(1);
            totalEggs += countEggsInRow();
        }

        double average = (double) totalEggs / rowAmount;
        System.out.println("Average amount of eggs per row: " + average);

        goToLocation(0, 0);
        faceDirection(1);
        return average;
    }

    /**
     *looks at the first row by going to the end of the border 
     *after looking at the end of the row mimi then goes back to original place
     *after going to original place then turns 90 degree (looking down)
     *move one collumn then 90 degree again but this time turn left (looking right)
     *then checks the whole row again and then another go back to original place
     *after that repeat the same step till last row.
     *
     * 
     *use countEggs, %, if, 
     *
     *if eggsInRow%2 == 1 then remember row, go to location under then look east.
     *getWorldHeight and getWorldWidth
     */
    public void algoritme ()
    {
        int findWrongEggRow = -1;
        goToLocation (0,0);
        faceEast();
        int eggsInRow=countEggsInRow();
        if (eggsInRow  %2 ==1)
        {
            findWrongEggRow = getY();
        }
       ;
       
    }

}
