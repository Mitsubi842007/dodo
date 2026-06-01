import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * 
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 01-01-2017
 */
public abstract class Egg extends Actor
{
    private int myValue;

    public Egg(int value)
    {
        myValue = value;
    }

    public int getValue()
    {
        return myValue;
    }

    public void setValue(int newValue)
    {
        myValue = newValue;
    }

    /**
     * swaps the value of two eggs (golden and blue ones)
     */
    public void swapValue(Egg otherEgg)
    {
        int tempValueEgg; 

        tempValueEgg = this.getValue();
        this.setValue(otherEgg.getValue());
        otherEgg.setValue(tempValueEgg);
    }

    public void setLocation(int x, int y)
    {
        if (Mauritius.checkCellContent(this, x, y, Egg.class, Fence.class))
        {
            super.setLocation(x, y);
        }
    }

}
