package org.example.model;
import java.util.Random;

public class Die
{
    private final Random dieRand = new Random(System.currentTimeMillis());
    private int dieBound;
    private int dieNumber;

    public int rollDie()
    {
        dieNumber = dieRand.nextInt(dieBound * 6) + 1;
        return dieNumber;
    }

    public int getDieNumber()
    {
        return dieNumber;
    }

    public int getDieBound()
    {
        return dieBound;
    }

    public void setDieBound(int dieBound)
    {
        this.dieBound = dieBound;
    }
}
